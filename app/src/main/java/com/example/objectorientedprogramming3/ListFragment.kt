package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.objectorientedprogramming3.databinding.FragmentListBinding
import com.google.firebase.firestore.FirebaseFirestore

class ListFragment : Fragment() {

    var binding : FragmentListBinding? = null

    var firestore : FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var method = arguments?.getString("method")

        binding?.recyclerGridView?.adapter = ListAdapterGridMethod(firestore!!,method!!)
        binding?.recyclerGridView?.layoutManager = GridLayoutManager(activity, 2)

        if (method == "전체"){
            binding?.txtMethodName?.text = "모든 운동을"
        }
        else{
            binding?.txtMethodName?.text = method
        }

        binding?.btnRoutine?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var option = method //method = 머신운동 & 프리웨이트 & 전체
            bundle.putString("option",option)
            findNavController().navigate(R.id.action_listFragment_to_routineFragment,bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}