package com.example.quran_app.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quran_app.controller.BookMarkFragment
import com.example.quran_app.controller.SuparahFragment
import com.example.quran_app.controller.SurahFragment
import com.google.android.material.tabs.TabLayout

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SuparahFragment()
            }
            1 -> {
                SurahFragment()
            }
            2 -> {
                BookMarkFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}












































//    (supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//    private var mFragmentList= ArrayList<Fragment>()
//    private var mFragmentTitleList= ArrayList<String>()
//    override fun getCount(): Int {
//return mFragmentList.size
//    }
//
//    override fun getItem(position: Int): Fragment {
//        return mFragmentList[position]
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return mFragmentTitleList[position]
//    }
//    fun addfragment(fragment: Fragment,title:String){
//        mFragmentList.add(fragment)
//        mFragmentTitleList.add(title)
//    }
//
//}























// : FragmentStateAdapter(FragmentActivity()) {
//

//}

