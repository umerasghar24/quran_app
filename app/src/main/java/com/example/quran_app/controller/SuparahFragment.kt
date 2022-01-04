package com.example.quran_app.controller

//import com.example.quran_app.adapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.StateSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran_app.MainActivity
import com.example.quran_app.R
import com.example.quran_app.adapter.ParahRecyclerAdapter
import com.example.quran_app.models.ParahNames
import com.example.quran_app.util.onQueryTextChanged
import com.example.quran_app.services.DataServices
import com.example.quran_app.viewModel.ParahViewModel
import com.example.quran_app.viewModel.SearchViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import java.util.Observer

const val EXTRA_PARAH = ""

@SuppressLint("StaticFieldLeak")
//lateinit var adapter: ParahRecyclerAdapter
class SuparahFragment : Fragment(R.layout.fragment_suparah) {

    val mViewModel: SearchViewModel by activityViewModels()
    var mAdapter :ParahRecyclerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_suparah, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.parahsListView)
        mAdapter = context?.let {
            ParahRecyclerAdapter(it, DataServices.parahs) { i ->
                val pdfintent = Intent(activity, PdfViewActivity::class.java)
                pdfintent.putExtra(getString(R.string.parah_key), i)
                startActivity(pdfintent)
            }
        }!!
//        val model: SearchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        mViewModel.parahList.observe(viewLifecycleOwner, {

        })
        mViewModel.searchQuery.observe(viewLifecycleOwner, { q ->
            q?.let {
                Log.e("searchQuery", "searchQuery in ${this.javaClass::getName} is = $q")
                mAdapter?.filter?.filter(q)

            }
        })




        recyclerView.adapter = mAdapter
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.hasFixedSize()
        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
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

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)

        val searchItem = menu.findItem(R.id.search_home)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        searchView.onQueryTextChanged {
            adapter.filter.filter(newText)

//            ParahViewModel.searchQuery.value = it
            SearchViewModel().parahList


        }

    }*/

}
