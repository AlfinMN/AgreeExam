package com.test.agreeexam.home.ui

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.agreeexam.config.AgreeApp
import com.test.agreeexam.databinding.ActivityHomeBinding
import com.test.agreeexam.home.data.GamesViewModel
import com.test.agreeexam.home.data.LatestAdapter
import com.test.agreeexam.home.data.TopRatingAdapter
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var gamesViewModel: GamesViewModel
    lateinit var topRatingAdapter: TopRatingAdapter
    lateinit var latestAdapter: LatestAdapter
    private lateinit var binding: ActivityHomeBinding
    val apiKey = "4e6154beee0f4b31af68f25ebcdbff7d"
    val pageSize = 10
    val platform = 4
    val pages = 1
    val dates = "2021-12-01,2021-12-31"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (this.applicationContext as AgreeApp).appComponent.inject(this)
        binding.apply {
            etSearch.setOnKeyListener { view, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER ){
                    searching()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
            btnSearch.setOnClickListener{
                searching()
            }

            rvToprating.layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.HORIZONTAL,false)
            gamesViewModel.resTopRating.observe(this@HomeActivity, Observer {
                if (it != null ){
                    topRatingAdapter = TopRatingAdapter(it,this@HomeActivity)
                    rvToprating.adapter = topRatingAdapter
                } else {
                    Toast.makeText(
                        this@HomeActivity,
                        "Not Found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
            gamesViewModel.topRating(apiKey,"-rating",pageSize,pages,platform,this@HomeActivity)

        rvLatest.layoutManager = LinearLayoutManager(this@HomeActivity,RecyclerView.VERTICAL,false)
        gamesViewModel.resLatest.observe(this@HomeActivity, Observer {
            if (it!= null ){
                latestAdapter = LatestAdapter(it,this@HomeActivity)
                rvLatest.adapter = latestAdapter
            } else {
                Toast.makeText(
                    this@HomeActivity,
                    "Not Found",
                    Toast.LENGTH_SHORT
                ).show()

            }        })
            gamesViewModel.latest(apiKey,"-released",pageSize,pages,platform,dates,this@HomeActivity)

        }
    }

    private fun searching() {
        val q = binding.etSearch.text.toString()
        if (q.isEmpty()) return
        val toSearch = Intent(this,SearchActivity::class.java)
        toSearch.putExtra(SearchActivity.EXTRA_KEYWORDS,q)
        toSearch.putExtra(SearchActivity.EXTRA_APIKEY,apiKey)
        toSearch.putExtra(SearchActivity.EXTRA_PAGESIZE,pageSize)
        toSearch.putExtra(SearchActivity.EXTRA_PLATFORM,platform)
        toSearch.putExtra(SearchActivity.EXTRA_PAGES,pages)
        this.startActivity(toSearch)

    }
}