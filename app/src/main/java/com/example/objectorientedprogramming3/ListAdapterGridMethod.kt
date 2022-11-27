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

class ListAdapterGridMethod(private var firestore: FirebaseFirestore, var method: String): RecyclerView.Adapter<ListAdapterGridMethod.Holder>() {

    var exercise: ArrayList<Exercise> = arrayListOf()

    init {
        firestore.collection("Exercise")
            .addSnapshotListener { querySnapshot, _ ->
                // ArrayList 비워줌
                exercise.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString("method") == method){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                    if(method == "전체"){
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
        holder.bind(exercise[position])
    }

    override fun getItemCount(): Int {
        return exercise.size
    }

    class Holder(private val binding: ListExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.txtName.text = exercise.name
            Glide.with(binding.root)
                .load(exercise.imageUrl)
                .centerCrop()
                .apply(RequestOptions().override(300, 300))
                .into(binding.imageView)


            binding.imageView.setOnClickListener {
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
