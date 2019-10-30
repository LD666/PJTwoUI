package com.myfirstapplication.pjtwoui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.databinding.FragmentLandlordAddBinding
import com.myfirstapplication.pjtwoui.fragments.LandlordAddFragment
import com.myfirstapplication.pjtwoui.fragments.LandlordInfoFragment
import com.myfirstapplication.pjtwoui.fragments.LandlordRemoveFragment
import com.myfirstapplication.pjtwoui.fragments.MyMapFragment
import com.myfirstapplication.pjtwoui.viewmodel.AddPropertyViewModel

class AddRemoveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remove)


        var landlordAddFragment = LandlordAddFragment()
        supportFragmentManager.beginTransaction().replace(R.id.add_remove_container, landlordAddFragment).commit()


        var mySettingTask = findViewById<BottomNavigationView>(R.id.landlord_bottom_nva_add_remove)

        mySettingTask.setOnNavigationItemSelectedListener {item ->

            when (item.itemId) {


                R.id.item_add_pro ->  {

                    supportFragmentManager.beginTransaction().replace(R.id.add_remove_container, landlordAddFragment).commit()

                    true
                }

                R.id.item_remove_pro -> {

                    var landlordRemoveFragment = LandlordRemoveFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.add_remove_container, landlordRemoveFragment).commit()

                    true
                }


                else -> false
            }

        }


    }
}
