package com.example.objectorientedprogramming3

//firebase와 연동해 가지고 오는 exercise관련 DB
open class Exercise(
    val name : String = "", //운동명
    val id : String = "", //운동부위명
    val method : String = "", //운동 메소드(프리웨이트, 머신운동, 전체)
    val imageUrl : String = "", //이미지url
    val set : String = "", //운동 set수
    val cnt : String = "", //운동 회수
    val info : String = "", //운동 한줄소개
    val info1 : String = "", //운동 설명 1
    val info2 : String = "", //운동 설명 2
    val info3 : String = "", //운동 설명 3
    val info4 : String = "", //운동 설명 4
    val infoNote1 : String = "", //운동 주의점
    val level : String = "") //운동 레벨