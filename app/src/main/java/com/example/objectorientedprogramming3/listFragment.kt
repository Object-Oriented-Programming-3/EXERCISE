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

class listFragment : Fragment() {

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


        binding?.recyclerGridView?.adapter = ListAdapterGrid(firestore!!)
        binding?.recyclerGridView?.layoutManager = GridLayoutManager(activity, 2)

        binding?.btnRoutine?.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_routineFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}