package com.example.objectorientedprogramming3.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.objectorientedprogramming3.R
import com.example.objectorientedprogramming3.databinding.FragmentSignUpBinding
import com.example.objectorientedprogramming3.utils.FirebaseRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    var binding : FragmentSignUpBinding? = null
    private val TAG = "SignUpFragment"
    private lateinit var auth: FirebaseAuth
    private var uid = ""
    private var name = ""
    private var nickname = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(inflater)
        auth = Firebase.auth
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnSignRegister?.setOnClickListener {
            val email = binding?.txtEmail?.text.toString()
            val password = binding?.txtPassword?.text.toString()
            val Check_password = binding?.txtPasswordCheck?.text.toString()

            name = binding?.name?.text.toString()
            nickname = binding?.txtNickname?.text.toString()

            if (email.isNullOrEmpty() || password.isNullOrEmpty() || Check_password.isNullOrEmpty() || nickname.isNullOrEmpty())
                Toast.makeText(getActivity(), "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
            if( password.isNotEmpty() && Check_password.isNotEmpty()&& password != Check_password)
                Toast.makeText(getActivity(), "비밀번호 재확인 해주세요", Toast.LENGTH_SHORT).show()

//            Log.d(TAG, email?.text.toString())
//            Log.d(TAG, password?.text.toString())
            else{
                getActivity()?.let { it1 ->
                    auth.createUserWithEmailAndPassword(
                        email.toString(),
                        password.toString()
                    )
                        .addOnCompleteListener(it1) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information

                                val user = auth.currentUser
                                uid = user?.uid.toString()

                                val userModel = UserDataModel(
                                    uid,
                                    nickname,
                                    name
                                )

                                FirebaseRef.userInfoRef.child(uid).setValue(userModel)



                                findNavController().navigate(R.id.action_signUpFragment2_to_entryFragment)

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)

                            }
                        }
                }
            }
        }
    }


}