package com.example.objectorientedprogramming3

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.objectorientedprogramming3.databinding.FragmentTimerBinding
import java.util.*
import kotlin.concurrent.timer

//'타이머'를 클릭하면 들어오는 페이지
class TimerFragment : Fragment() {

    var binding : FragmentTimerBinding?= null
    private var time = 0
    private var timerTask: Timer?= null

    //뷰 바인딩
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimerBinding.inflate(inflater)
        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //타이머 시작버튼
        binding?.btnStarttime?.setOnClickListener{
            startTimer()
        }
        //타이머 멈춤버튼
        binding?.btnStoptime?.setOnClickListener{
            stopTimer()
        }
        //타이머 리셋버튼
        binding?.btnResettime?.setOnClickListener{
            resetTimer()
        }

    }



    //타이머 시작함수
    //타이머 메소드를 통해 일정주기로(period = 1000 = 1초) 반복해서 작업 수행
    private fun startTimer(){
        timerTask = timer(period = 1000){
            time++

            val sec = time % 60
            val min = (time / 60) % 60
            val hour =(time/(60*60))%24

            //ui에 적용
            //timer는 기본적으로 워커쓰레드 - 메인쓰레드 ui에 접근 불가능 - runonuithread 메소드로 접근 가능
            activity?.runOnUiThread(Runnable {
                binding?.txtTime?.text = String.format("%02d : %02d : %02d",hour, min, sec)

            })
        }
    }

    //타이머 멈춤함수
    private fun stopTimer(){
        timerTask?.cancel()
    }

    //타이머 리셋함수
    private fun resetTimer(){
        timerTask?.cancel()

        time = 0
        binding?.txtTime?.text = "00 : 00 : 00"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        //onDestroyView() 이후에 Fragment view는 종료되지만, Fragment는 살아있어서 메모리 누수 발생
        //따라서 binding 변수를 onDetsroyView() 이후에 null로 만들어 줘야함
    }

}
