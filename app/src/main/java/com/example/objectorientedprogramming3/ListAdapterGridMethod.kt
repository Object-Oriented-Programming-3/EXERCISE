package com.example.objectorientedprogramming3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.objectorientedprogramming3.databinding.ListExerciseBinding
import com.google.firebase.firestore.FirebaseFirestore

//'운동 리스트' 관련 Adapter
class ListAdapterGridMethod(private var firestore: FirebaseFirestore, var method: String): RecyclerView.Adapter<ListAdapterGridMethod.Holder>() {

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
                    //파이어스토어의 필드 중 "method"가 클릭한 버튼 (method)과 같을 경우
                        // 해당 method들의 데이터만 가져옴
                    if(snapshot.getString("method") == method){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                    //클릭한 버튼이 "전체"일 경우 모든 데이터 가져옴
                    if(method == "전체"){
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
    class Holder(private val binding: ListExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            //운동명
            binding.txtName.text = exercise.name

            //이미지
            Glide.with(binding.root)
                .load(exercise.imageUrl)
                .centerCrop()
                .apply(RequestOptions().override(300, 300))
                .into(binding.imgExercise1)

            //이미지 클릭시, 해당 운동 디테일 페이지로 이동
            //이동시, 해당 운동의 데이터를 bundle에 담아서 이동
            binding.imgExercise1.setOnClickListener {
                var fragment: Fragment = SearchDetailFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("name", exercise.name)
                bundle.putString("imageUrl", exercise.imageUrl)
                bundle.putString("set", exercise.set)
                bundle.putString("cnt", exercise.cnt)
                bundle.putString("info", exercise.info)
                bundle.putString("info1", exercise.info1)
                bundle.putString("info2", exercise.info2)
                bundle.putString("info3", exercise.info3)
                bundle.putString("info4", exercise.info4)
                bundle.putString("infoNote1", exercise.infoNote1)

                fragment.arguments = bundle
                Navigation.findNavController(binding.root).navigate(R.id.action_listFragment_to_searchDetailFragment, bundle)

            }

        }
    }
}
