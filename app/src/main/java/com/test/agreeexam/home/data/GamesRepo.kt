package com.test.agreeexam.home.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject

class GamesRepo @Inject constructor(val gamesAPI: GamesAPI) {
    var resTopRating = MutableLiveData<ArrayList<GamesModel>>()
    var resLatest = MutableLiveData<ArrayList<GamesModel>>()
    var resSearch = MutableLiveData<ArrayList<GamesModel>>()

    fun topRating(key : String , ordering : String ,page_size : Int ,pages :Int , platform :Int , context: Context ){
        gamesAPI.topRating(key ,page_size, ordering, platform, pages).enqueue(object : Callback<ResponseAPI>{
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val res = response.body()
                println(response.code())
                println(response.body()?.next)
                if (response.code() != 200){
                    Toast.makeText(
                        context,
                        "Failed request",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val resData = res?.results
                    resTopRating.value = resData

                }
            }


            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context,"Connection Failed ${t.printStackTrace()} ", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun latest(key : String , ordering : String ,page_size : Int ,pages :Int , platform :Int , dates : String, context: Context ){
        gamesAPI.latest(key, page_size, ordering, platform, dates, pages).enqueue(object :Callback<ResponseAPI>{
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val res = response.body()
                println(response.code())
                println(response.body()?.next)
                if (response.code() != 200){
                    Toast.makeText(
                        context,
                        "Failed request",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val resData = res?.results
                    resLatest.value = resData

                }
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context,"Connection Failed ${t.printStackTrace()} ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun search(key : String ,page_size : Int ,pages :Int , platforms :Int , search : String, context: Context){
        gamesAPI.search(key, page_size, search, platforms, pages).enqueue(object :Callback<ResponseAPI>{
            override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                val res = response.body()
                println(response.code())
                println(response.body()?.next)
                if (response.code() != 200){
                    Toast.makeText(
                        context,
                        "Failed request",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val resData = res?.results
                    resSearch.value = resData

                }
            }

            override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context,"Connection Failed ${t.printStackTrace()} ", Toast.LENGTH_SHORT).show()
            }

        })
    }


}