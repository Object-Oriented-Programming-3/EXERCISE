package com.example.objectorientedprogramming2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.objectorientedprogramming2.databinding.ActivityMainBinding
//import com.example.objectorientedprogramming2.databinding.ActivityMainBinding
//import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.firestore.FirebaseFirestore


open class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding //activity_main.xml파일을 Binding해줌
    var firestore : FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()
        binding.recyclerGridView.adapter = ListAdapterGrid(firestore!!)
        binding.recyclerGridView.layoutManager = GridLayoutManager(this, 2)
    }



//        var list = arrayListOf("Title 1", "Title2", "Title3", "Title4")
//        var listManager = GridLayoutManager(this,2)
//        var listAdapter = ListAdapterGrid(list)
//
//
//        var recyclerList = recyclerGridView.apply{
//                setHasFixedSize(true)
//                layoutManager = listManager
//                adapter = listAdapter
//        }
    }
