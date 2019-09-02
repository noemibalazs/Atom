package com.example.atom.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.atom.R
import com.example.atom.fragment.HottestFragment
import com.example.atom.fragment.UpcomingFragment
import com.example.atom.utils.ZERO

class TabsAdapter(val context: Context, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            ZERO -> {
                HottestFragment()
            }
            else -> {
                UpcomingFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            ZERO -> {context.getString(R.string.hottest_fragment_title)}
            else -> {context.getString(R.string.upcoming_fragment_title)}
        }
    }
}