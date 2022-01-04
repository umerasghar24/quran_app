package com.example.quran_app.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.StateSet
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran_app.MainActivity
import com.example.quran_app.R
import com.example.quran_app.adapter.BookmarksAdapter
import com.example.quran_app.viewModel.ParahViewModel
import com.example.quran_app.viewModel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_book_mark.view.*

class BookMarkFragment : Fragment(R.layout.fragment_book_mark) {
val mViewModel:SearchViewModel by activityViewModels()
    private lateinit var mUserViewModel: ParahViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_book_mark, container, true)
//        val adapter = context?.let { BookmarksAdapter(this, it) }
        val adapter = context?.let { BookmarksAdapter(this,ViewModelProvider(this), it) }

        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mUserViewModel = ViewModelProvider(this).get(ParahViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { parahBookMark ->
            if (adapter != null) {
                adapter.setData(parahBookMark)
//                adapter.filter.filter(parahBookMark.toString())
            }
        })
        mViewModel.searchQuery.observe(viewLifecycleOwner,{q->
            adapter?.filter?.filter(q)
        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                Log.e(StateSet.TAG, "onScrolled: " )
                if (dy>0){
                    (activity as MainActivity).collapse()
                }else{
                    (activity as MainActivity).expand()
                }
            }
        })


        return view
    }
    }

