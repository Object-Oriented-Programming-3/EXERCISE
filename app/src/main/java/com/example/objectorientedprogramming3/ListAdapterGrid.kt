package com.example.objectorientedprogramming3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.objectorientedprogramming3.databinding.ListExerciseBinding
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField

//'운동 검색' 관련 Adpater
class ListAdapterGrid(private var firestore: FirebaseFirestore): RecyclerView.Adapter<ListAdapterGrid.Holder>() {

    //Exercise 클래스 ArrayList 생성
    var exercise: ArrayList<Exercise> = arrayListOf()

    //초기화
    //파이어스토어의 Exercise 문서를 불러와 ArrayList 에 담기
    init {
        firestore.collection("Exercise")
            .addSnapshotListener { querySnapshot, _ ->
                // ArrayList 비워줌
                exercise.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(Exercise::class.java)
                    exercise.add(item!!)
                }
                notifyDataSetChanged()
            }
    }

    //파이어스토에서 데이터를 불러와서 스피너의 결과값에 따른 데이터가 있는지 확인하는 함수
    //option = "id" (운동 부위), sub = 클릭한 운동부위
    fun firstSearch(option: String, sub: String){
        firestore.collection("Exercise")
            .addSnapshotListener{ querySnapshot, _ ->
                exercise.clear()


                for(snapshot in querySnapshot!!.documents) {
                    //파이어스토어의 option("id"(운동부위))과 sub(운동부위)가 같을 경우
                    if(snapshot.getString(option) == sub){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                    //운동부위가 "전체"일 경우 모든 데이터 가져오기
                    if(sub == "all"){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
    }

    //파이어스토에서 데이터를 불러와서 검색어에 해당하는 데이터가 있는지 확인하는 함수
    //searchWord = 검색어, option = "name" (운동명)
    fun search(searchWord: String, option : String) {
        firestore.collection("Exercise")
            .addSnapshotListener{ querySnapshot, _ ->
                exercise.clear()

                //해당 검색어가 담길 경우, 데이터 가져오기
                for(snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString(option)!!.contains(searchWord)){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
    }

    //binding을 통한 Holder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding = ListExerciseBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    //onCreateViewHolder에서 만든 Holder와 연결
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(exercise[position])
    }

    //recyclerGridView의 아이템 총 개수 반환
    override fun getItemCount(): Int {
        return exercise.size
    }

    //페이지에 담길 내용들을 모아놓은 Holder
    class Holder(private val binding : ListExerciseBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(exercise : Exercise) {
            //운동명
            binding.txtName.text = exercise.name

            //이미지
            Glide.with(binding.root)
                .load(exercise.imageUrl)
                .centerCrop()
                .apply(RequestOptions().override(300,300))
                .into(binding.imgExercise1)

            //이미지 클릭시, 해당 운동 디테일 페이지로 이동
            //이동시, 해당 운동의 데이터를 bundle에 담아서 이동
            binding.imgExercise1.setOnClickListener{
                var fragment: Fragment = SearchDetailFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("name",exercise.name) //운동명
                bundle.putString("imageUrl",exercise.imageUrl) //이미지 url
                bundle.putString("set",exercise.set) //운동 세트수
                bundle.putString("cnt",exercise.cnt) //운동 회수
                bundle.putString("info",exercise.info) //운동 한줄소개
                bundle.putString("info1",exercise.info1) //운동 설명 1
                bundle.putString("info2",exercise.info2) //운동 설명 2
                bundle.putString("info3",exercise.info3) //운동 설명 3
                bundle.putString("info4",exercise.info4) //운동 설명 4
                bundle.putString("infoNote1",exercise.infoNote1) //운동 주의점

                fragment.arguments=bundle

                Navigation.findNavController(binding.root).navigate(R.id.action_searchResultFragment_to_searchDetailFragment,bundle)
            }
        }
    }
}