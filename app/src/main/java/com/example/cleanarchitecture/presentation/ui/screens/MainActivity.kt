package com.example.cleanarchitecture.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.core.utils.BaseActivity
import com.example.cleanarchitecture.presentation.ui.screens.UserAdapter
import com.example.cleanarchitecture.presentation.viewmodel.UserViewModel
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()
    val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setUpViews()
        setUpObserve()
    }

    private fun setUpViews() {
        with(binding) {
            binding.feedsRecyclerview.adapter = adapter

            feedsRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(
                    recyclerView: RecyclerView, newState: Int
                ) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    // Trigger the next fetch once the user reaches the bottom of the current list.
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.fetchUsers()
                    }
                }
            })

            swipeContainerFeed.setOnRefreshListener {
                // Refresh resets pagination in the ViewModel before requesting the first page again.
                viewModel.fetchUsers(true)
            }
        }
    }

    // Bind screen state to the ViewModel once and let LiveData drive future UI updates.
    fun setUpObserve() {
        with(viewModel) {
            fetchUsers()
            users.observe(this@MainActivity) {
                Log.e(TAG, "onCreate: $it")
                adapter.setData(it)
                binding.swipeContainerFeed.isRefreshing = false
            }

            error.observe(this@MainActivity) {
                binding.noDataFound.noRecordDescriptionTextView.text = "Error: $error"
            }

            loading.observe(this@MainActivity) {
                binding.roundProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}
