package com.myfirstapplication.pjtwoui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.fragments.AddTenantFragment
import com.myfirstapplication.pjtwoui.myinterface.GoFragIntent
import com.myfirstapplication.pjtwoui.recycleradapter.AddTenAdapter
import com.myfirstapplication.pjtwoui.viewmodel.LandlordAddTenViewModel
import kotlinx.android.synthetic.main.activity_landlord_tennant_list.*



class LandlordTenantListActivity : AppCompatActivity(), GoFragIntent {
    override fun onPressed(bundle: Bundle) {
        bundle.getInt("WTF")
        Log.i("WTFFFF", bundle.getInt("WTF").toString())
        var addTenantFragment = AddTenantFragment()
        supportFragmentManager.beginTransaction().add(R.id.rep_this_id, addTenantFragment).commit()
    }










    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord_tennant_list)


        var viewModel = ViewModelProviders.of(this).get(LandlordAddTenViewModel::class.java)

        add_ten_pl_recycler.layoutManager = LinearLayoutManager(this)

        viewModel.onCallList().observe(this, Observer {

            val addList = it.property
            add_ten_pl_recycler.adapter = AddTenAdapter(addList, this)
        })


        add_ten_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, LandlordActivity::class.java)
            startActivity(myIntent)
        })





//        var mySettingTask = findViewById<BottomNavigationView>(R.id.landlord_tenants_bottom_nva)
//
//        mySettingTask.setOnNavigationItemSelectedListener {item ->
//
//            when (item.itemId) {
//
//
//                R.id.item_ten_list ->  {
//
//                    supportFragmentManager.beginTransaction().replace(R.id.landlord_tenants_container, landlordTenantsListFragment).commit()
//
//                    true
//                }
//
//                R.id.item_add_ten -> {
//
//                    var propertyListForAddTenFragment = PropertyListForAddTenFragment()
//                    supportFragmentManager.beginTransaction().replace(R.id.landlord_tenants_container, propertyListForAddTenFragment).commit()
//
//                    true
//                }
//
//
//                else -> false
//            }
//
//        }

    }
}
