package com.myfirstapplication.pjtwoui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.fragments.*
import com.myfirstapplication.pjtwoui.viewmodel.Tenant.TenantActViewModel
import kotlinx.android.synthetic.main.activity_landlord.*

class TennantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant)


        var mySettingTask = findViewById<BottomNavigationView>(R.id.tenants_bottom_nva)

        var allProForTenFragment = AllProForTenFragment()
        supportFragmentManager.beginTransaction().replace(R.id.ten_main, allProForTenFragment).commit()

        mySettingTask.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {


                R.id.item_all_Pro -> {

//                    var allProForTenFragment = AllProForTenFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.ten_main, allProForTenFragment).commit()
                    true
                }

                R.id.item_ten_map -> {

                    var tenMapFragment = TenMapFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.ten_main, tenMapFragment).commit()
                    true
                }

                R.id.item_ten_info -> {

                    var tenantInfoFragment = TenantInfoFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.ten_main, tenantInfoFragment).commit()
                    true
                }


                else -> false
            }
        }

    }
}
