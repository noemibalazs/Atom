package com.example.atom.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.atom.R
import com.example.atom.adapter.TabsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pager.adapter = TabsAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(pager)
    }
}
