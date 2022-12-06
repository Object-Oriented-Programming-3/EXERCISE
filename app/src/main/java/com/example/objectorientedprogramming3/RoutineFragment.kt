package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.objectorientedprogramming3.databinding.FragmentRoutineBinding
import com.google.firebase.firestore.FirebaseFirestore

//운동 리스트페이지에서 '루틴 보기' 클릭시 들어오는 운동 루틴 페이지
class RoutineFragment : Fragment() {

    var binding: FragmentRoutineBinding? = null
    var firestore : FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()}

    //View를 생성해서 반환
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoutineBinding.inflate(layoutInflater)
        return binding?.root

    }

    //onCreateView에서 생성된 view가 인자로 전달(view가 완전히 생성된 상태)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bundle로 가져온 option을 변수에 풀어주기
        //option = 머신운동 & 프리웨이트 & 전체 중 하나
        var option = arguments?.getString("option")

        //초보자용 bundle 생성
        var bundle1: Bundle = Bundle()
        var level1 = "초보자"
        bundle1.putString("level",level1)

        //중급자용 bundle 생성
        var bundle2: Bundle = Bundle()
        var level2 = "중급자"
        bundle2.putString("level",level2)

        //고급자용 bundle 생성
        var bundle3: Bundle = Bundle()
        var level3 = "숙련자"
        bundle3.putString("level",level3)

//recyclerView(Scroll)를 통해 아이템 보여주기

        //초보자
        binding?.recyclerRoutine?.adapter = ListAdapterGridScroll(firestore!!,option!!)
        (binding?.recyclerRoutine?.adapter as ListAdapterGridScroll).level(option!!,bundle1)
        binding?.recyclerRoutine?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //중급자
        binding?.recyclerRoutine2?.adapter = ListAdapterGridScroll(firestore!!,option!!)
        (binding?.recyclerRoutine2?.adapter as ListAdapterGridScroll).level(option!!,bundle2)
        binding?.recyclerRoutine2?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //숙련자
        binding?.recyclerRoutine3?.adapter = ListAdapterGridScroll(firestore!!,option!!)
        (binding?.recyclerRoutine3?.adapter as ListAdapterGridScroll).level(option!!,bundle3)
        binding?.recyclerRoutine3?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        //bundle로 가져온 운동메소드에 따른 text값 변경(페이지 구분을 위함)
        if (option == "전체"){
            binding?.txtRoutine?.text = "전체 운동 루틴"
        }
        else{
            binding?.txtRoutine?.text = option + " 루틴"
        }
    }
}


