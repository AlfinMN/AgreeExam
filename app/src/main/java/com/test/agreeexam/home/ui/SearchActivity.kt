package com.test.agreeexam.home.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.agreeexam.config.AgreeApp
import com.test.agreeexam.databinding.ActivitySearchBinding
import com.test.agreeexam.home.data.GamesViewModel
import com.test.agreeexam.home.data.LatestAdapter
import com.test.agreeexam.home.data.TopRatingAdapter
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    @Inject
    lateinit var gamesViewModel: GamesViewModel
    lateinit var latestAdapter: LatestAdapter
    var keyword  : String? = null
    var apikey : String? = null
    var pagesize : Int = 0
    var platform : Int = 0
    var pages : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (this.applicationContext as AgreeApp).appComponent.inject(this)
        showLoad(true)
         keyword = intent.getStringExtra(EXTRA_KEYWORDS)
         apikey = intent.getStringExtra(EXTRA_APIKEY)
         pages =intent.getIntExtra(EXTRA_PAGES,1)
         pagesize = intent.getIntExtra(EXTRA_PAGESIZE,1)
         platform = intent.getIntExtra(EXTRA_PLATFORM,1)
        binding.apply {
           etSearch.setText(keyword)
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
            rvSearch.layoutManager = LinearLayoutManager(this@SearchActivity,
                RecyclerView.VERTICAL,false)
            gamesViewModel.resSeaarch.observe(this@SearchActivity, Observer {
                if (it!=null){

                    latestAdapter = LatestAdapter(it,this@SearchActivity)
                    rvSearch.adapter = latestAdapter
                    showLoad(false)
                    showNotfound(false)
                }   else {
                    showLoad(false)
                    showNotfound(true)
                    Toast.makeText(
                        this@SearchActivity,
                        "Not Found",
                        Toast.LENGTH_SHORT
                    ).show()
                }         })
            gamesViewModel.search(apikey.toString(),pagesize,pages,platform, keyword.toString(),this@SearchActivity)

        }
    }

    private fun searching() {
        val q = binding.etSearch.text.toString()
        if (q.isEmpty()) return
        showLoad(true)
        gamesViewModel.search(apikey.toString(),pagesize,pages,platform, q,this@SearchActivity)
    }
    private fun showLoad(state: Boolean) {
        if (state){
            binding.load.visibility = View.VISIBLE
        }else{
            binding.load.visibility = View.GONE
        }
    }
    private fun showNotfound(state: Boolean) {
        if (state){
            binding.notfound.visibility = View.VISIBLE
        }else{
            binding.notfound.visibility = View.GONE
        }
    }

    companion object {

        const val EXTRA_APIKEY = "apikey"
        const val EXTRA_PAGESIZE = "pagesize"
        const val EXTRA_PLATFORM = "platform"
        const val EXTRA_PAGES = "pages"
        const val EXTRA_DATES = "dates"
        const val EXTRA_KEYWORDS = "keyword"

    }
}