package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.objectorientedprogramming3.databinding.FragmentSearchResultBinding
import com.google.firebase.firestore.FirebaseFirestore


class searchResultFragment : Fragment() {

    private var binding : FragmentSearchResultBinding ? = null
    private var firestore : FirebaseFirestore? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchResultBinding.inflate(layoutInflater)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = FirebaseFirestore.getInstance()
        binding?.recyclerGridView?.adapter = ListAdapterGrid(firestore!!)
        binding?.recyclerGridView?.layoutManager = GridLayoutManager(activity, 2)
        var searchOption = "id"
        binding?.imageButton?.setOnClickListener{
            (binding?.recyclerGridView?.adapter as ListAdapterGrid).search(binding?.searchWord?.text.toString(),searchOption)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}