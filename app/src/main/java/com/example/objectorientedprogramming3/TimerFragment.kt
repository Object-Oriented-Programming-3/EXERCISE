package com.example.objectorientedprogramming3

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.objectorientedprogramming3.databinding.FragmentTimerBinding
import java.util.*
import kotlin.concurrent.timer

class TimerFragment : Fragment() {


    var binding : FragmentTimerBinding?= null
    private var time = 0
    private var timerTask: Timer?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTimerBinding.inflate(inflater)

        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnStarttime?.setOnClickListener{
            startTimer()
        }
        binding?.btnStoptime?.setOnClickListener{
            stopTimer()
        }
        binding?.btnResettime?.setOnClickListener{
            resetTimer()
        }

    }



    private fun startTimer(){
        timerTask = timer(period = 1000){
            time++

            val sec = time % 60
            val min = (time / 60) % 60
            val hour =(time/(60*60))%24





            activity?.runOnUiThread(Runnable {
                binding?.txtTime?.text = "${hour} : ${min} : ${sec}"
            })
        }
    }

    private fun stopTimer(){
        timerTask?.cancel()
    }

    private fun resetTimer(){
        timerTask?.cancel()

        time = 0
        binding?.txtTime?.text = "00 : 00 : 00"
    }





}
