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
        // Sign 버튼을 클릭시 회원가입 프래그먼트로 이동
        binding?.btnSignUp?.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment2_to_signUpFragment2)
        }
        // FirebaseAuth 의 인스턴스를 선언
        auth = Firebase.auth
        binding?.btnLogIn?.setOnClickListener {

            // xml에서 사용한 id ed_Id와 ed_Pw의 변수명을 아래와 같이 선언
            val email = binding?.edId
            val password = binding?.edPw
            // 사용자가 앱에 로그인할 때 사용자의 이메일 주소와 비밀번호를 signInWithEmailAndPasswor에 전달
            getActivity()?.let { it1 ->
                auth.signInWithEmailAndPassword(email?.text.toString(), password?.text.toString())
                    .addOnCompleteListener(it1) { task ->
                        if (task.isSuccessful) {    // 성공하면 화면을 entryFragment로 전달
                            findNavController().navigate(R.id.action_loginFragment2_to_entryFragment)
                        } else {
                            Toast.makeText(getActivity(), "실패", Toast.LENGTH_LONG).show()

                        }
                    }
            }
        }


    }


}