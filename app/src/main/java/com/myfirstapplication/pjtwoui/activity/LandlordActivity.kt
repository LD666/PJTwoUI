package com.myfirstapplication.pjtwoui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.fragments.*
import com.myfirstapplication.pjtwoui.myinterface.PassTenInfoInterface
import com.myfirstapplication.pjtwoui.myinterface.SharedToInterface
import kotlinx.android.synthetic.main.activity_landlord.*
import kotlinx.android.synthetic.main.landlord_property_list_text.*

class LandlordActivity : AppCompatActivity(), PassTenInfoInterface, SharedToInterface{

    override fun go() {
        val intent = Intent()
        intent.action = Intent.ACTION_SENDTO
        intent.putExtra(Intent.EXTRA_TEXT, "Shared it with....")

        startActivity(Intent.createChooser(intent, "Share to"))
    }

    override fun passTen(bundle: Bundle) {

    }


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord)

//        val viewModel = ViewModelProviders.of(this).get(LandlordViewModel::class.java)
//
//
//        landlord_list_recycler.layoutManager = LinearLayoutManager(this)
//        landlord_list_recycler.adapter = myListAdapter(this, listData)
//
//        viewModel.list().observe(this, Observer {
//
//            var listData = it.property
//            landlord_list_recycler.adapter = PropertyListAdapter(listData, this)
//
//        })
//
//
//
//
        float_button_to_add.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, AddRemoveActivity::class.java)
            startActivity(myIntent)

        })

        float_button_to_add_ten.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, LandlordTenantListActivity::class.java)
            startActivity(myIntent)

        })

        float_button_to_add.visibility = View.VISIBLE

//        var landlordFragment= LandlordFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.landlord_main, landlordFragment).commit()

        var lanProListFragment = LanProListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.landlord_main, lanProListFragment).commit()


        var mySettingTask = findViewById<BottomNavigationView>(R.id.landlord_bottom_nva_landlord)

        mySettingTask.setOnNavigationItemSelectedListener {item ->

            when (item.itemId) {

                R.id.item_lan_pro ->  {

                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, lanProListFragment).commit()

                    float_button_to_add.visibility = View.VISIBLE
                    float_button_to_add_ten.visibility = View.GONE

                    true
                }


                R.id.item_add_ten ->  {

//                    var myIntent = Intent(this, LandlordActivity::class.java)
//                    startActivity(myIntent)

                    var landlordFragment= LandlordFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, landlordFragment).commit()
                    float_button_to_add.visibility = View.GONE
                    float_button_to_add_ten.visibility = View.GONE
                    true
                }

                R.id.item_ten -> {

//                    var myIntent = Intent(this, LandlordTenantListActivity::class.java)
//                    startActivity(myIntent)
                    var landlordTenantsListFragment = LandlordTenantsListFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, landlordTenantsListFragment).commit()
                    float_button_to_add.visibility = View.GONE
//                    float_button_to_add_ten.visibility = View.VISIBLE
                    float_button_to_add_ten.visibility = View.GONE

                    true
                }

                R.id.item_map -> {

                    var mapFragment = MyMapFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, mapFragment).commit()
                    float_button_to_add.visibility = View.GONE
                    float_button_to_add_ten.visibility = View.GONE

                    true
                }

                R.id.item_info -> {

                    var landlordInfoFragment = LandlordInfoFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.landlord_main, landlordInfoFragment).commit()
                    float_button_to_add.visibility = View.GONE
                    float_button_to_add_ten.visibility = View.GONE

                    true
                }

                else -> false
            }

        }

    }
}
