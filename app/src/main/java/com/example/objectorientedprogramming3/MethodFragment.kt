package com.example.objectorientedprogramming3


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.objectorientedprogramming3.databinding.FragmentMethodBinding

//'운동 시작하기'클릭시, 들어오는 운동 시작 첫 화면 (운동 시작 페이지)
class MethodFragment : Fragment() {

    var binding: FragmentMethodBinding? = null

    //View를 생성해서 반환
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMethodBinding.inflate(inflater)
        return binding?.root
    }

    //onCreateView에서 생성된 view가 인자로 전달(view가 완전히 생성된 상태)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //'머신운동' 클릭시, 머신운동 추천 페이지로 이동
        //해당 버튼 이름을 bundle로 같이 이동
        binding?.btnMachine?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var method = "머신운동"
            bundle.putString("method",method)
            findNavController().navigate(R.id.action_methodFragment_to_listFragment,bundle)
        }

        //'프리웨이트' 클릭시, 프리웨이트 추천 페이지로 이동
        //해당 버튼 이름을 bundle로 같이 이동
        binding?.btnFreeweight?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var method = "프리웨이트"
            bundle.putString("method",method)
            findNavController().navigate(R.id.action_methodFragment_to_listFragment,bundle)
        }

        //'모두' 클릭시, 모든 운동이 나오는 페이지로 이동
        //해당 버튼 이름을 bundle로 같이 이동
        binding?.btnAll?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var method = "전체"
            bundle.putString("method",method)
            findNavController().navigate(R.id.action_methodFragment_to_listFragment,bundle)
        }


    }




}