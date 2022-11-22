package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.objectorientedprogramming3.databinding.FragmentEntryBinding
import com.example.objectorientedprogramming3.utils.FirebaseAuthUtils


class EntryFragment : Fragment() {

    var binding: FragmentEntryBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uid = FirebaseAuthUtils.getUid()
        if (uid == "null"){
            findNavController().navigate(R.id.action_entryFragment_to_loginFragment2)
        }
        binding = FragmentEntryBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.btnSearch?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_searchResultFragment)
        }
    }
}