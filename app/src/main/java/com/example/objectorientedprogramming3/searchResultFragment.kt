package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.objectorientedprogramming3.databinding.FragmentSearchResultBinding
import com.google.firebase.firestore.FirebaseFirestore


class searchResultFragment : Fragment() {

    private var binding : FragmentSearchResultBinding ? = null
    private var firestore : FirebaseFirestore? = null
    private var exercise: ArrayList<Exercise> = arrayListOf()


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

        var firstSearchOption = "id"
        var searchOption = "name"
        var subOption = ""

        binding?.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (binding?.spinner?.getItemAtPosition(position)) {
                    "운동부위" -> {
                        subOption = "all"
                    }
                    "가슴" -> {
                        subOption = "가슴"
                    }
                    "등" -> {
                        subOption = "등"
                    }
                    "복근" -> {
                        subOption = "복근"
                    }
                    "어깨" -> {
                        subOption = "어깨"
                    }
                    "팔" -> {
                        subOption = "팔"
                    }
                    "하체" -> {
                        subOption = "하체"
                    }

                }
                (binding?.recyclerGridView?.adapter as ListAdapterGrid).firstSearch(firstSearchOption,subOption)
            }
        }


        binding?.imageButton?.setOnClickListener{
            (binding?.recyclerGridView?.adapter as ListAdapterGrid).search(binding?.searchWord?.text.toString(),searchOption)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}