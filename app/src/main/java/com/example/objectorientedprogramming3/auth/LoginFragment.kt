package com.example.objectorientedprogramming3.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.objectorientedprogramming3.R
import com.example.objectorientedprogramming3.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    var binding : FragmentLoginBinding? = null
    private lateinit var auth : FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentLoginBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnSignUp?.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment2_to_signUpFragment2)
        }

        auth = Firebase.auth
        binding?.btnLogIn?.setOnClickListener {

            val email = binding?.edId
            val password = binding?.edPw

            getActivity()?.let { it1 ->
                auth.signInWithEmailAndPassword(email?.text.toString(), password?.text.toString())
                    .addOnCompleteListener(it1) { task ->
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.action_loginFragment2_to_entryFragment)
                        } else {
                            Toast.makeText(getActivity(), "실패", Toast.LENGTH_LONG).show()

                        }
                    }
            }
        }


    }


}