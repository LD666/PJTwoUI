package com.myfirstapplication.pjtwoui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.fragments.MyMapFragment

class LandlordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord)

        var mySettingTask = findViewById<BottomNavigationView>(R.id.landlord_bottom_nva)


        mySettingTask.setOnNavigationItemSelectedListener {item ->

            when (item.itemId) {

                R.id.item_map -> {


                    var mapFragment = MyMapFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, mapFragment).commit()

                    true
                }

                else -> false
            }

        }

    }
}
