package com.test.agreeexam.home.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesAPI {

    /** top rating*/
    @GET("games")
    fun topRating(@Query("key")key : String ,
                  @Query("page_size")page_size : Int ,
                  @Query("ordering")ordering : String ,
                  @Query("platform")platform : Int ,
                  @Query("pages")pages : Int ) : Call<ResponseAPI>

    /** latest*/
    @GET("games")
    fun latest(@Query("key")key : String ,
                  @Query("page_size")page_size : Int ,
                  @Query("ordering")ordering : String ,
                  @Query("platform")platform : Int ,
                  @Query("dates")dates : String ,
                  @Query("pages")pages : Int ) : Call<ResponseAPI>

    /** search*/
    @GET("games")
    fun search(@Query("key")key : String ,
               @Query("page_size")page_size : Int ,
               @Query("search")search : String ,
               @Query("platforms")platforms : Int ,
               @Query("pages")pages : Int ): Call<ResponseAPI>
}