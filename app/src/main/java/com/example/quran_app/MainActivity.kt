package com.example.quran_app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.quran_app.adapter.ViewPagerAdapter
import com.example.quran_app.controller.PdfViewActivity
import com.example.quran_app.controller.UserActivity
import com.example.quran_app.viewModel.SearchViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable
import free.translate.languagetranslator.cameratranslation.voicetranslator.TinyDB

//lateinit var adapter1: ParahRecyclerAdapter
//lateinit var countryrv: RecyclerView
//lateinit var suparahFragment:SuparahFragment
open class MainActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButtonExpandable
    lateinit var tinyDB:TinyDB
    val mViewModel: SearchViewModel by viewModels()

    open fun expand(){
        fab.expand()
    }

    open fun collapse(){fab.collapse()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//         suparahFragment= SuparahFragment()
        tinyDB= TinyDB(this)

        fab=findViewById(R.id.fab)
//        var fragmentTransaction:FragmentTransaction=supportFragmentManager.beginTransaction()
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.tool)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
toolbar.setNavigationOnClickListener {
    val userIntent=Intent(this,UserActivity::class.java)
    startActivity(userIntent)
}



        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
//        setupActionBarWithNavController(findNavController(R.id.tool))

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "By Juzz"
                }
                1 -> {
                    tab.text = "By Surah"
                }
                2 -> {
                    tab.text = "BookMarks"
                }
            }
        }.attach()
        fab.setOnClickListener {
//fab.toggle(true)

            val intent=Intent(this,PdfViewActivity::class.java)
            startActivity(intent)//setting value of fab in pdf Activity
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val search: MenuItem? = menu?.findItem(R.id.search_home)
        val searchView: SearchView? = search?.actionView as? SearchView
        searchView?.queryHint = "Search"


        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mViewModel.searchQuery.postValue(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                /*adapter1.filter.filter(newText)*/
                mViewModel.searchQuery.postValue(newText)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
//    override fun onResume() {
//        super.onResume()
////         wantedAdapter: ParahRecyclerAdapter? =
//        adapter1= suparahFragment.getListAdapter()
//    }

}