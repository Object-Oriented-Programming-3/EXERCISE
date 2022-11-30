package com.example.objectorientedprogramming3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.objectorientedprogramming3.auth.UserDataModel
import com.example.objectorientedprogramming3.databinding.FragmentEntryBinding
import com.example.objectorientedprogramming3.utils.FirebaseAuthUtils
import com.example.objectorientedprogramming3.utils.FirebaseRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class EntryFragment : Fragment() {

    var binding: FragmentEntryBinding?=null
    private val usersDataList = mutableListOf<UserDataModel>()
    private val uid = FirebaseAuthUtils.getUid()
    private val TAG = "EntryFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uid = FirebaseAuthUtils.getUid()
        if (uid == "null"){
            findNavController().navigate(R.id.action_entryFragment_to_loginFragment2)
        }
        binding = FragmentEntryBinding.inflate(inflater)
        getMyData()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnSettings?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_settingsFragment)
        }


        binding?.btnSearch?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_searchResultFragment)
        }
        binding?.btnStart?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_methodFragment)
        }
        binding?.btnRoutine?.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_timerFragment)
        }
    }
    private fun getMyData(){

        val myNickname = binding?.myNickname

        val postListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                val data = datasnapshot.getValue(UserDataModel::class.java)

                myNickname?.text = data?.nickname



            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)
    }

}