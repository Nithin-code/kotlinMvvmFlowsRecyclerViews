package com.nithin.kotlinmvvm

import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nithin.kotlinmvvm.apiResponse.Response
import com.nithin.kotlinmvvm.callbacks.AlbumCallbacks
import com.nithin.kotlinmvvm.databinding.ActivityMainBinding
import com.nithin.kotlinmvvm.model.AlbumData
import com.nithin.kotlinmvvm.view.AlbumRecyclerViewAdapter
import com.nithin.kotlinmvvm.viewmodel.AlbumViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), AlbumCallbacks{

    private val viewModel by viewModels<AlbumViewModel>()

//    private val viewModel = AlbumViewModel()

    private lateinit var binding : ActivityMainBinding

    private var progressDialog : Dialog? = null

    private lateinit var mAdapter : AlbumRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initRecyclerView()
        initViews()
    }

    private fun initRecyclerView() {
        mAdapter = AlbumRecyclerViewAdapter(this)
        val mLayoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            binding.albumRv.context,
            mLayoutManager.orientation
        )
        binding.albumRv.apply {
            layoutManager = mLayoutManager
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            setDrawingCacheEnabled(true);
            adapter = mAdapter
        }
    }

    private fun initViews() {
        lifecycleScope.launch {
            viewModel._myApiResponse.collect{ response->
                when(response){
                    is Response.Failure -> {
                        hideDialog()
                        println(response.message)
                    }
                    is Response.Loading -> {
                        showDialog()
                        println("Loading")
                    }
                    is Response.Success -> {
                        hideDialog()
                        println("Success")
                        updateUI(response.responseData)
                    }
                }
            }
        }

    }

    private fun updateUI(responseData: List<AlbumData>?) {
        if (responseData == null){
            return
        }
        mAdapter.updateAllAlbumsList(responseData)
    }

    private fun showDialog() {
        if (progressDialog == null){
            progressDialog = Dialog(this)
        }
        progressDialog?.setContentView(R.layout.progress_dialog)
        progressDialog?.show()
    }

    private fun hideDialog(){
        progressDialog?.hide()
    }

    override fun onItemClick(position: Int) {
        viewModel.cardClick(position)
    }
}