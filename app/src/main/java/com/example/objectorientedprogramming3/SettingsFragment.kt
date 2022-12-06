package com.example.objectorientedprogramming3

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.objectorientedprogramming3.alarm.AlarmActivity
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
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class SettingsFragment : Fragment() {
    var binding : FragmentSettingsBinding? = null
    private val TAG = "FragmentSetting"
    private val uid = FirebaseAuthUtils.getUid()
    private val usersDataList = mutableListOf<UserDataModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        val getAction = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri ->
                binding?.ivProfile?.setImageURI(uri)
            }
        )

        binding?.ivProfile?.setOnClickListener {

            getAction.launch("image/*")

        }
        binding?.btnSave?.setOnClickListener {

            uploadImage(uid)
        }
        binding?.btnAlarm?.setOnClickListener {
            val intent = Intent(context, AlarmActivity::class.java)
            startActivity(intent)
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

                val ref = FirebaseStorage.getInstance().getReference(uid+".png")
                ref.downloadUrl
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if(task.isSuccessful){

                            binding?.ivProfile?.let { it1 ->
                                getActivity()?.let {
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
    private fun uploadImage(uid : String){ //업로드 이미지

        val storage = Firebase.storage
        val storageRef = storage.reference.child(uid + ".png")

        binding?.ivProfile?.isDrawingCacheEnabled = true
        binding?.ivProfile?.buildDrawingCache()
        val bitmap = (binding?.ivProfile?.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = storageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->

        }

    }

}