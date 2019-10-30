package com.myfirstapplication.pjtwoui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.mydataclass.Property
import com.myfirstapplication.pjtwoui.fragments.LandlordInfoFragment
import com.myfirstapplication.pjtwoui.fragments.MyMapFragment
import com.myfirstapplication.pjtwoui.recycleradapter.PropertyListAdapter
import com.myfirstapplication.pjtwoui.viewmodel.LandlordViewModel
import kotlinx.android.synthetic.main.activity_landlord.*

class LandlordActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord)

        val viewModel = ViewModelProviders.of(this).get(LandlordViewModel::class.java)


        landlord_list_recycler.layoutManager = LinearLayoutManager(this)
//        landlord_list_recycler.adapter = myListAdapter(this, listData)

        viewModel.list().observe(this, Observer {

            var listData = it.property
            landlord_list_recycler.adapter = PropertyListAdapter(listData, this)

        })









        var mySettingTask = findViewById<BottomNavigationView>(R.id.landlord_bottom_nva_landlord)

        mySettingTask.setOnNavigationItemSelectedListener {item ->

            when (item.itemId) {


                R.id.item_pro ->  {

                    var myIntent = Intent(this, AddRemoveActivity::class.java)
                    startActivity(myIntent)

                    true
                }

                R.id.item_ten -> {

                    var myIntent = Intent(this, LandlordTenantListActivity::class.java)
                    startActivity(myIntent)

                    true
                }

                R.id.item_map -> {

                    var mapFragment = MyMapFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, mapFragment).commit()

                    true
                }

                R.id.item_info -> {

                    var landlordInfoFragment = LandlordInfoFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, landlordInfoFragment).commit()

                    true
                }

                else -> false
            }

        }

    }
}
