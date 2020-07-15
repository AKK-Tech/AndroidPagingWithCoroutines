package ke.co.appslab.androidpagingwithcoroutines

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import ke.co.appslab.androidpagingwithcoroutines.adapters.RedditLoadPostsAdapter
import ke.co.appslab.androidpagingwithcoroutines.adapters.RedditPostsAdapter
import ke.co.appslab.androidpagingwithcoroutines.adapters.UnsplashAdapter
import ke.co.appslab.androidpagingwithcoroutines.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val redditPostsAdapter = RedditPostsAdapter()
    private val unsplashAdapter = UnsplashAdapter()
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initializeList()
        observeLiveData()
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
//        lifecycleScope.launch {
//            mainViewModel.flow.collectLatest { pagingData ->
//                redditPostsAdapter.submitData(pagingData)
//            }
//        }
        lifecycleScope.launch {
            mainViewModel.unsplashFlow.collectLatest { pagingData ->
                unsplashAdapter.submitData(pagingData)
            }
        }

    }

    private fun initializeList() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = unsplashAdapter
        list.adapter = unsplashAdapter.withLoadStateHeaderAndFooter(
            header = RedditLoadPostsAdapter { unsplashAdapter::retry },
            footer = RedditLoadPostsAdapter { unsplashAdapter::retry }
        )


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
