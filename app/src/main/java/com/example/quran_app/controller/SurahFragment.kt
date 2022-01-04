package com.example.quran_app.controller

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran_app.MainActivity
import com.example.quran_app.R
import com.example.quran_app.adapter.SurahRecyclerAdapter
import com.example.quran_app.services.DataServices
import com.example.quran_app.viewModel.SearchViewModel
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable
import kotlinx.android.synthetic.main.fragment_surah.*
import java.util.*

const val EXTRA_SURAH="SURAH_KEY"
class SurahFragment : Fragment(R.layout.fragment_surah) {

    val mViewModel:SearchViewModel by activityViewModels()
    var madapter:SurahRecyclerAdapter?=null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_surah, container, true)
        val recyclerView2:RecyclerView=view.findViewById(R.id.parahsListView)
        madapter= context?.let { SurahRecyclerAdapter(it, DataServices.surah){ i ->
            val pdfintent= Intent(activity,PdfViewActivity::class.java)
            pdfintent.putExtra(getString(R.string.surah_key),i)
            Log.e("SurahFragment","default value$i")
//            pdfintent.putExtra(EXTRA_SURAH,surahNames)
            startActivity(pdfintent)
        } }
        mViewModel.searchQuery.observe(viewLifecycleOwner,{q->
            q.let {
                madapter?.filter?.filter(q)

            }
        })



        recyclerView2.adapter =madapter
        val layoutManager= LinearLayoutManager(context)
        recyclerView2.layoutManager =layoutManager
        recyclerView2.hasFixedSize()
//        val vieww:View=layoutInflater.inflate(R.layout.activity_main,container,false)
//        val fab=vieww.findViewById<FloatingActionButtonExpandable>(R.id.fab)
        recyclerView2.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                Log.e(TAG, "onScrolled: " )
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