package com.example.objectorientedprogramming2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.objectorientedprogramming2.databinding.ListExerciseBinding
import com.google.firebase.firestore.FirebaseFirestore



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

    fun search(searchWord: String, option : String) {
        firestore.collection("Exercise")
            ?.addSnapshotListener{ querySnapshot, _ ->
                exercise.clear()

                for(snapshot in querySnapshot!!.documents) {
                    if(snapshot.getString(option)!!.contains(searchWord)){
                        var item = snapshot.toObject(Exercise::class.java)
                        exercise.add(item!!)
                    }
                }
                notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        var view =
//            LayoutInflater.from(parent.context).inflate(R.layout.list_exercise, parent, false)
//        //var binding = ListExerciseBinding.inflate(LayoutInflater.from(parent.context))
//
//        return ViewHolder(view)

        val binding = ListExerciseBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
//        var viewHolder = (holder as ViewHolder).itemView
//        viewHolder.txt_name.text = exercise[position].name
        holder.bind(exercise[position])

    }

    override fun getItemCount(): Int {
        return exercise.size
    }

    class Holder(private val binding : ListExerciseBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(exercise : Exercise) {
            binding.txtName.text = exercise.name

        }
    }







}


//class ListAdapterGrid(var list: ArrayList<String>): RecyclerView.Adapter<ListAdapterGrid.GridAdapter>() {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter {
//        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_exercise, parent, false)
//        //var binding = ListExerciseBinding.inflate(LayoutInflater.from(parent.context))
//
//        return GridAdapter(view)
//    }
//
//    override fun onBindViewHolder(holder: GridAdapter, position: Int) {
//        holder.layout.txt_name.text = list[position]
//        //holder.bind(list[position])
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//
//    class GridAdapter(val layout: View): RecyclerView.ViewHolder(layout)
//
//}




