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


class RoutineFragment : Fragment() {

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

        var option = arguments?.getString("option")

        if (option == "전체"){
            binding?.txtRoutine?.text = "전체 운동 루틴"
        }
        else{
            binding?.txtRoutine?.text = option + " 루틴"
        }

        //머신운동 & 프리웨이트
        binding?.recyclerRoutine?.adapter = ListAdapterGridMethod(firestore!!,option!!)
        (binding?.recyclerRoutine?.adapter as ListAdapterGridMethod).level_1(option!!)
        binding?.recyclerRoutine?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding?.recyclerRoutine2?.adapter = ListAdapterGridMethod(firestore!!,option!!)
        (binding?.recyclerRoutine2?.adapter as ListAdapterGridMethod).level_2(option!!)
        binding?.recyclerRoutine2?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding?.recyclerRoutine3?.adapter = ListAdapterGridMethod(firestore!!,option!!)
        (binding?.recyclerRoutine3?.adapter as ListAdapterGridMethod).level_3(option!!)
        binding?.recyclerRoutine3?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //전체운동
        binding?.recyclerRoutine?.adapter = ListAdapterGrid(firestore!!)
        (binding?.recyclerRoutine?.adapter as ListAdapterGrid).level_1()
        binding?.recyclerRoutine?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding?.recyclerRoutine2?.adapter = ListAdapterGrid(firestore!!)
        (binding?.recyclerRoutine2?.adapter as ListAdapterGrid).level_2()
        binding?.recyclerRoutine2?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding?.recyclerRoutine3?.adapter = ListAdapterGrid(firestore!!)
        (binding?.recyclerRoutine3?.adapter as ListAdapterGrid).level_3()
        binding?.recyclerRoutine3?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //(binding?.recyclerRoutine?.adapter as ListAdapterScroll).search(option)


    }

}


