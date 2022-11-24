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

        var bundle1: Bundle = Bundle()
        var level1 = "초보자"
        bundle1.putString("level",level1)
        var bundle2: Bundle = Bundle()
        var level2 = "중급자"
        bundle2.putString("level",level2)
        var bundle3: Bundle = Bundle()
        var level3 = "숙련자"
        bundle3.putString("level",level3)

        //초보자
        binding?.recyclerRoutine?.adapter = ListAdapterGridScroll(firestore!!,option!!)
        (binding?.recyclerRoutine?.adapter as ListAdapterGridScroll).level(option!!,bundle1)
        binding?.recyclerRoutine?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //중급자
        binding?.recyclerRoutine2?.adapter = ListAdapterGridScroll(firestore!!,option!!)
        (binding?.recyclerRoutine2?.adapter as ListAdapterGridScroll).level(option!!,bundle2)
        binding?.recyclerRoutine2?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //숙련자
        binding?.recyclerRoutine3?.adapter = ListAdapterGridScroll(firestore!!,option!!)
        (binding?.recyclerRoutine3?.adapter as ListAdapterGridScroll).level(option!!,bundle3)
        binding?.recyclerRoutine3?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        if (option == "전체"){
            binding?.txtRoutine?.text = "전체 운동 루틴"
        }
        else{
            binding?.txtRoutine?.text = option + " 루틴"
        }
    }
}


