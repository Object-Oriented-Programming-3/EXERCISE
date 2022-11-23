package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.objectorientedprogramming3.databinding.FragmentRoutineBinding
import com.google.firebase.firestore.FirebaseFirestore


class routineFragment : Fragment() {



    var binding: FragmentRoutineBinding? = null
    var firestore : FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoutineBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var option = "method"

        binding?.recyclerRoutine?.adapter = ListAdapterGrid(firestore!!)
        binding?.recyclerRoutine?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding?.recyclerRoutine2?.adapter = ListAdapterGrid(firestore!!)
        binding?.recyclerRoutine2?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding?.recyclerRoutine3?.adapter = ListAdapterGrid(firestore!!)
        binding?.recyclerRoutine3?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //(binding?.recyclerRoutine?.adapter as ListAdapterScroll).search(option)


    }

}


