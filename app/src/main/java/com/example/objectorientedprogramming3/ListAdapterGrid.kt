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

class ListAdapterGrid(private var firestore: FirebaseFirestore): RecyclerView.Adapter<ListAdapterGrid.Holder>() {

    var exercise: ArrayList<Exercise> = arrayListOf()


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

    fun firstSearch(option: String, sub: String){
        firestore.collection("Exercise")
            .addSnapshotListener{ querySnapshot, _ ->
                exercise.clear()

                for(snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString(option) == sub){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                    if(sub == "all"){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
    }


    fun search(searchWord: String, option : String) {
        firestore.collection("Exercise")
            .addSnapshotListener{ querySnapshot, _ ->
                exercise.clear()


                for(snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString(option)!!.contains(searchWord)){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
//                    if(snapshot.getString(option)!!.contains(searchWord)){
//                        var item = snapshot.toObject(Exercise::class.java)
//                        exercise.add(item!!)
//                    }
                }
                notifyDataSetChanged()
            }
    }

    fun level_1(){
        firestore.collection("Exercise")
            .addSnapshotListener { querySnapshot, _ ->
                // ArrayList 비워줌
                exercise.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString("level") == "초보자"){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
    }
    fun level_2(){
        firestore.collection("Exercise")
            .addSnapshotListener { querySnapshot, _ ->
                // ArrayList 비워줌
                exercise.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString("level") == "중급자"){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
    }
    fun level_3(){
        firestore.collection("Exercise")
            .addSnapshotListener { querySnapshot, _ ->
                // ArrayList 비워줌
                exercise.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString("level") == "숙련자"){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                }
                notifyDataSetChanged()
            }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding = ListExerciseBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
//        var viewHolder = (holder as ViewHolder).itemView
//        viewHolder.txt_name.text = exercise[position].name
        holder.bind(exercise[position])
//        }
    }

    override fun getItemCount(): Int {
        return exercise.size
    }

    class Holder(private val binding : ListExerciseBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(exercise : Exercise) {
            binding.txtName.text = exercise.name
            Glide.with(binding.root)
                .load(exercise.imageUrl)
                .fitCenter()
                .apply(RequestOptions().override(300,300))
                .into(binding.imageView)


            binding.imageView.setOnClickListener{
                var fragment: Fragment = SearchDetailFragment()
                var bundle: Bundle = Bundle()
                bundle.putString("name",exercise.name)
                bundle.putString("imageUrl",exercise.imageUrl)
                bundle.putString("set",exercise.set)
                bundle.putString("cnt",exercise.cnt)
                bundle.putString("info",exercise.info)
                bundle.putString("info1",exercise.info1)
                bundle.putString("info2",exercise.info2)
                bundle.putString("info3",exercise.info3)
                bundle.putString("info4",exercise.info4)
                bundle.putString("infoNote1",exercise.infoNote1)


                fragment.arguments=bundle

                Navigation.findNavController(binding.root).navigate(R.id.action_searchResultFragment_to_searchDetailFragment,bundle)
                //Navigation.findNavController(binding.root).navigate(R.id.action_listFragment_to_searchDetailFragment,bundle)
                //Navigation.findNavController(binding.root).navigate(R.id.action_routineFragment_to_searchDetailFragment,bundle)


            }


        }




    }


}