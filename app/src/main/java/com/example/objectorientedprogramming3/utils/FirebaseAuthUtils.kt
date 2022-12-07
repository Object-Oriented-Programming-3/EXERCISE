package com.example.objectorientedprogramming3.utils

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthUtils {

    companion object{

        private lateinit var auth: FirebaseAuth

        fun getUid() : String{
            // 인스턴스 가져오기
            auth = FirebaseAuth.getInstance()
            // 현재 유저의 uid 반환
            return auth.currentUser?.uid.toString()
        }
    }
}