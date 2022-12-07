package com.example.objectorientedprogramming3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.objectorientedprogramming3.databinding.FragmentSearchResultBinding
import com.google.firebase.firestore.FirebaseFirestore

//'운동 검색' 클릭시, 들어오는 운동 검색 첫 화면
class SearchResultFragment : Fragment() {

    private var binding : FragmentSearchResultBinding ? = null
    private var firestore : FirebaseFirestore? = null
    private var exercise: ArrayList<Exercise> = arrayListOf()

    //View를 생성해서 반환
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(layoutInflater)
        return binding?.root
    }

    //onCreateView에서 생성된 view가 인자로 전달(view가 완전히 생성된 상태)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        //recyclerGridView를 활용해서 이미지 보여주기
        binding?.recyclerGridView?.adapter = ListAdapterGrid(firestore!!)
        binding?.recyclerGridView?.layoutManager = GridLayoutManager(activity, 2)

        //검색 옵션 변수들
        var firstSearchOption = "id"
        var searchOption = "name"
        var subOption = ""

        //스피너 클릭에 따른 아이템 보여주기
        binding?.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //스피너의 클릭에 따른 subOption 변경(해당 subOption의 결과를 보여주기 위함)
                when (binding?.spinner?.getItemAtPosition(position)) {
                    "운동부위" -> {
                        subOption = "all"
                    }
                    "가슴" -> {
                        subOption = "가슴"
                    }
                    "등" -> {
                        subOption = "등"
                    }
                    "복근" -> {
                        subOption = "복근"
                    }
                    "어깨" -> {
                        subOption = "어깨"
                    }
                    "팔" -> {
                        subOption = "팔"
                    }
                    "하체" -> {
                        subOption = "하체"
                    }

                }
                (binding?.recyclerGridView?.adapter as ListAdapterGrid).firstSearch(firstSearchOption,subOption)
            }
        }

        //검색어에 따른 아이템 보여주기
        binding?.imageButton?.setOnClickListener{
            (binding?.recyclerGridView?.adapter as ListAdapterGrid).search(binding?.searchWord?.text.toString(),searchOption)
        }


    }

    //View Lifecycle, 클래스 인스턴스 정리를 위해 onDestroyView()
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}