package com.example.objectorientedprogramming3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.objectorientedprogramming3.databinding.FragmentSearchDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

//운동 이미지 클릭시, 들어오는 운동 디테일 페이지
class SearchDetailFragment : Fragment() {

    var binding : FragmentSearchDetailBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //View를 생성해서 반환
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchDetailBinding.inflate(layoutInflater)
        return binding?.root
    }



    //onCreateView에서 생성된 view가 인자로 전달(view가 완전히 생성된 상태)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bundle로 담아온 데이터들을 해당 페이지 변수에 넣어주기
        val name = arguments?.getString("name")
        val imageUrl = arguments?.getString("imageUrl")
        val set = arguments?.getString("set")
        val cnt = arguments?.getString("cnt")
        val info = arguments?.getString("info")
        val info1 = arguments?.getString("info1")
        val info2 = arguments?.getString("info2")
        val info3 = arguments?.getString("info3")
        val info4 = arguments?.getString("info4")
        val infoNote1 = arguments?.getString("infoNote1")

        //변수를 binding의 text값에 넣어주기
        binding?.txtExerciseName?.text = name
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .apply(RequestOptions().override(300,300))
            .into(binding!!.imgExercise)
        binding?.txtSet?.text = set
        binding?.txtCnt?.text = cnt
        binding?.txtInfo?.text = info
        binding?.txtInfo1?.text = info1
        binding?.txtInfo2?.text = info2
        binding?.txtInfo3?.text = info3
        binding?.txtInfo4?.text = info4
        binding?.txtInfoNote1?.text = infoNote1

    }

    //View Lifecycle, 클래스 인스턴스 정리를 위해 onDestroyView()
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}