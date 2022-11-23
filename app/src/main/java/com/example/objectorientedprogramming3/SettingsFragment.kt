package com.example.objectorientedprogramming3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.objectorientedprogramming3.auth.UserDataModel
import com.example.objectorientedprogramming3.databinding.FragmentSettingsBinding
import com.example.objectorientedprogramming3.utils.FirebaseAuthUtils
import com.example.objectorientedprogramming3.utils.FirebaseRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SettingsFragment : Fragment() {
    var binding : FragmentSettingsBinding? = null
    private val TAG = "FragmentSetting"
    private val uid = FirebaseAuthUtils.getUid()
    private val usersDataList = mutableListOf<UserDataModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater)
        getMyData()

        return binding?.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnLogOut?.setOnClickListener {

            val auth = Firebase.auth
            auth.signOut()
            findNavController().navigate(R.id.action_settingsFragment_to_loginFragment2)
        }
    }


    private fun getMyData(){

        val myImage = binding?.ivProfile
        val myNickname = binding?.txtNickname

        val postListener = object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {

                Log.d(TAG, datasnapshot.toString())
                val data = datasnapshot.getValue(UserDataModel::class.java)

                myNickname?.text = data?.nickname

                val storageRef = Firebase.storage.reference.child(data?.uid + "png")
                storageRef.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->

                    if(task.isSuccessful){
                        getActivity()?.let {
                            myImage?.let { it1 ->
                                Glide.with(it)
                                    .load(task.result)
                                    .into(it1)
                            }
                        }
                    }
                })


            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRef.userInfoRef.child(uid).addValueEventListener(postListener)
    }

}