//package com.example.objectorientedprogramming3
//
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//
//class ListAdapterScroll(private var firestore: FirebaseFirestore): RecyclerView.Adapter<ListAdapterGrid.Holder>() {
//    var exercise: ArrayList<Exercise> = arrayListOf()
//
//    init {
//        firestore.collection("Exercise")
//            .addSnapshotListener { querySnapshot, _ ->
//                // ArrayList 비워줌
//                exercise.clear()
//
//                for (snapshot in querySnapshot!!.documents) {
//                    var item = snapshot.toObject(Exercise::class.java)
//                    exercise.add(item!!)
//                }
//                notifyDataSetChanged()
//            }
//    }
//
//
//    fun search( option : String) {
//        firestore.collection("Exercise")
//            .addSnapshotListener{ querySnapshot, _ ->
//                exercise.clear()
//
//
//                for(snapshot in querySnapshot!!.documents) {
//                    if(snapshot.getString(option)){
//                        var item = snapshot.toObject(Exercise::class.java)
//                        exercise.add(item!!)
//                    }
////                    if(snapshot.getString(option)!!.contains(searchWord)){
////                        var item = snapshot.toObject(Exercise::class.java)
////                        exercise.add(item!!)
////                    }
//                }
//                notifyDataSetChanged()
//            }
//    }
//}