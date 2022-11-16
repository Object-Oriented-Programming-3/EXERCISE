package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.objectorientedprogramming3.databinding.FragmentSearchMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class SearchMainFragment : Fragment() {


    var binding: FragmentSearchMainBinding ?= null
    var firestore : FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchMainBinding.inflate(inflater)
        return binding?.root
    }


    fun search(searchWord: String, option : String){

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var searchOption = "id"

        binding?.imageButton?.setOnClickListener{
            findNavController().navigate(R.id.action_searchMainFragment_to_searchResultFragment)
        }

        binding?.butAbs?.setOnClickListener{
            findNavController().navigate(R.id.action_searchMainFragment_to_searchResultFragment)
        }
        binding?.butArm?.setOnClickListener{
            findNavController().navigate(R.id.action_searchMainFragment_to_searchResultFragment)
        }
        binding?.butBack?.setOnClickListener{
            findNavController().navigate(R.id.action_searchMainFragment_to_searchResultFragment)
        }
        binding?.butChest?.setOnClickListener{
            findNavController().navigate(R.id.action_searchMainFragment_to_searchResultFragment)
        }
        binding?.butLower?.setOnClickListener{
            findNavController().navigate(R.id.action_searchMainFragment_to_searchResultFragment)
        }
        binding?.butShoulder?.setOnClickListener{
            findNavController().navigate(R.id.action_searchMainFragment_to_searchResultFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}