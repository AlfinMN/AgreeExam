package com.test.agreeexam.home.data

class GamesModel (
    val slug : String,
    val name : String ,
    val released : String,
    val background_image : String ,
    val rating : Float,
    val rating_top : Int ,
//    val platform : ArrayList<Platfrom>,
//    val stores : ArrayList<Stores>,
//    val genres : ArrayList<Genres>
        ){
}
class Genres(
    val id : Int ,
    val name : String ,
    val slug : String
){

}

class Platfrom(
    val id : Int,
    val name : String ,
    val slug: String){}

class Stores (
    val id : Int,
    val name : String ,
    val slug: String
        ){}

class ResponseAPI(
  val count : Int,
  val next : String ,
  val previous : String ,
  val results : ArrayList<GamesModel>
){}


