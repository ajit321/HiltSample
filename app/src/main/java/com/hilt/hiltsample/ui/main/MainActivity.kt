package com.hilt.hiltsample.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hilt.hiltsample.R
import com.hilt.hiltsample.databinding.ActivityMainBinding
import com.hilt.hiltsample.model.Category
import com.hilt.hiltsample.ui.base.BaseActivity
import com.hilt.hiltsample.ui.main.adapter.CategoriesListAdapter
import com.hilt.hiltsample.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    CategoriesListAdapter.OnItemClickListener {

    private val mAdapter: CategoriesListAdapter by lazy { CategoriesListAdapter(onItemClickListener = this@MainActivity) }

    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        // Initialize RecyclerView

        mViewBinding.postsRecyclerView.apply {
            //layoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            adapter = mAdapter
        }

        initPosts()

        handleNetworkChanges()
    }

    private fun initPosts() {
        mViewModel.postsLiveData.observe(this, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    /*if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        //
                    }*/
                    if (state.data != null) {
                        mAdapter.setData(state.data.categories)
                        showLoading(false)
                    }

                }
                is State.Error -> {
                    showToast(state.message)
                    showLoading(false)
                }
            }
        })

        mViewBinding.swipeRefreshLayout.setOnRefreshListener {
            getPosts()
        }

        // If State isn't `Success` then reload posts.
        if (mViewModel.postsLiveData.value !is State.Success) {
            getPosts()
        }
    }

    private fun getPosts() {
        mViewModel.getAllProducts()
    }

    private fun showLoading(isLoading: Boolean) {
        mViewBinding.swipeRefreshLayout.isRefreshing = isLoading
    }

    /**
     * Observe network changes i.e. Internet Connectivity
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this, Observer { isConnected ->
            if (!isConnected) {
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_no_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                }
            } else {
                if (mViewModel.postsLiveData.value is State.Error || mAdapter.itemCount == 0) {
                    getPosts()
                }
                mViewBinding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))

                    animate()
                        .alpha(1f)
                        .setStartDelay(ANIMATION_DURATION)
                        .setDuration(ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        })
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }

    override fun onItemClicked(post: Category) {

    }
}