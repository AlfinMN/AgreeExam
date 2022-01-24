package com.test.agreeexam.home.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class GamesViewModel @Inject constructor(var gamesRepo: GamesRepo)  {
    val resTopRating : MutableLiveData<ArrayList<GamesModel>> = gamesRepo.resTopRating
    val resLatest : MutableLiveData<ArrayList<GamesModel>> = gamesRepo.resLatest
    val resSeaarch : MutableLiveData<ArrayList<GamesModel>> = gamesRepo.resSearch

    fun topRating(key : String , ordering : String ,page_size : Int ,pages :Int , platform :Int , context: Context){
        gamesRepo.topRating(key, ordering, page_size, pages, platform, context)
    }

    fun latest(key : String , ordering : String ,page_size : Int ,pages :Int , platform :Int ,dates:String , context: Context){
        gamesRepo.latest(key, ordering, page_size, pages, platform, dates, context)
    }

    fun search(key : String ,page_size : Int ,pages :Int , platforms :Int , search : String, context: Context){
        gamesRepo.search(key, page_size, pages, platforms, search, context)
    }
}