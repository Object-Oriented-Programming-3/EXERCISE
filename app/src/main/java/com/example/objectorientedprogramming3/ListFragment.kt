package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.objectorientedprogramming3.databinding.FragmentListBinding
import com.google.firebase.firestore.FirebaseFirestore

//머신,프리웨이트,모두 버튼 중 클릭시 들어오는 운동 리스트 페이지
class ListFragment : Fragment() {

    var binding : FragmentListBinding? = null
    var firestore : FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = FirebaseFirestore.getInstance()

    }

    //View를 생성해서 반환
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding?.root
    }

    //onCreateView에서 생성된 view가 인자로 전달(view가 완전히 생성된 상태)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bundle로 가져온 method 변수에 담아주기
        var method = arguments?.getString("method")

        //recyclerGridView를 활용해서 아이템 보여주기
        binding?.recyclerGridView?.adapter = ListAdapterGridMethod(firestore!!,method!!)
        binding?.recyclerGridView?.layoutManager = GridLayoutManager(activity, 2)


        //bundle로 가져온 버튼명에 따른 text값 변경(페이지 구분을 위함)
        if (method == "전체"){
            binding?.txtMethodName?.text = "모든 운동을"
        }
        else{
            binding?.txtMethodName?.text = method
        }

        //'루틴 보기' 클릭시, 운동 루틴 페이지로 이동
        binding?.btnRoutine?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var option = method //method = 머신운동 & 프리웨이트 & 전체
            bundle.putString("option",option)
            findNavController().navigate(R.id.action_listFragment_to_routineFragment,bundle)
        }
    }

    //View Lifecycle, 클래스 인스턴스 정리를 위해 onDestroyView()
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}