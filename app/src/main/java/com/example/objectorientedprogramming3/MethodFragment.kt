package com.example.objectorientedprogramming3


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.objectorientedprogramming3.databinding.FragmentMethodBinding


class MethodFragment : Fragment() {

    var binding: FragmentMethodBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMethodBinding.inflate(inflater)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnFreeweight?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var method = "프리웨이트"
            bundle.putString("method",method)
            findNavController().navigate(R.id.action_methodFragment_to_listFragment,bundle)
        }
        binding?.btnMachine?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var method = "머신운동"
            bundle.putString("method",method)
            findNavController().navigate(R.id.action_methodFragment_to_listFragment,bundle)
        }
        binding?.btnAll?.setOnClickListener{
            var bundle: Bundle = Bundle()
            var method = "전체"
            bundle.putString("method",method)
            findNavController().navigate(R.id.action_methodFragment_to_listFragment,bundle)
        }


    }




}