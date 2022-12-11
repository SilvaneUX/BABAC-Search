package com.smart.smartbalibackpaker.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smart.smartbalibackpaker.MainActivity
import com.smart.smartbalibackpaker.R
import com.smart.smartbalibackpaker.core.viewmodel.ViewModelFactory
import com.smart.smartbalibackpaker.databinding.ActivitySearchViewBinding
import kotlinx.android.synthetic.main.activity_list_vacation.*

class SearchView : AppCompatActivity() {
    private val binding by lazy { ActivitySearchViewBinding.inflate(layoutInflater) }
    private lateinit var searchAdapter:SearchAdapterView

    private val placeViewModel by lazy {
        ViewModelProvider(
            this, ViewModelFactory.getInstance(this)
        ).get(PlaceViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showRecyclerView(

        )

        binding.backButtonToDashboard.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showRecyclerView() {

        binding.edtSearchUser.requestFocus()

        binding.edtSearchUser.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                placeViewModel.searchPlace(query ?: "").observe(this@SearchView) {
                    searchAdapter = SearchAdapterView()
                    searchAdapter.submitList(it.data)

                    binding.recyclerViewResult.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@SearchView)
                        adapter = searchAdapter

                    }
                    searchAdapter.notifyDataSetChanged()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


//        val dummy = ArrayList<SearchPlace>()


    }
}