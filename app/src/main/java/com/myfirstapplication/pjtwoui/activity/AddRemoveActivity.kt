package com.myfirstapplication.pjtwoui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.databinding.FragmentLandlordAddBinding
import com.myfirstapplication.pjtwoui.fragments.*
import com.myfirstapplication.pjtwoui.myinterface.RemovePassDataInterface
import com.myfirstapplication.pjtwoui.viewmodel.AddPropertyViewModel

class AddRemoveActivity : AppCompatActivity(), RemovePassDataInterface {

    override fun getRemoveID(bundle: Bundle) {

        var removeProFrag = RemoveProFragment()
        var id = bundle.getString("propertyID")
        var propertyaddress = bundle.getString("propertyaddress")
        var propertycity = bundle.getString("propertycity")
        var propertystate = bundle.getString("propertystate")
        var propertycountry = bundle.getString("propertycountry")
        var propertystatus = bundle.getString("propertystatus")
        var propertypurchaseprice = bundle.getString("propertypurchaseprice")
        var propertymortageinfo = bundle.getString("propertymortageinfo")

//        Log.i("InMain", id)
//        Log.i("InMain", propertyaddress)
//        Log.i("InMain", propertycity)
//        Log.i("InMain", propertystate)
//        Log.i("InMain", propertycountry)
//        Log.i("InMain", propertystatus)
//        Log.i("InMain", propertypurchaseprice)
//        Log.i("InMain", propertymortageinfo)

        bundle.putString("id", id)
        bundle.putString("propertyaddress", propertyaddress)
        bundle.putString("propertycity", propertycity)
        bundle.putString("propertystate", propertystate)
        bundle.putString("propertycountry", propertycountry)
        bundle.putString("propertystatus", propertystatus)
        bundle.putString("propertypurchaseprice", propertypurchaseprice)
        bundle.putString("propertymortageinfo", propertymortageinfo)
        removeProFrag.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.add_remove_container, removeProFrag).commit()
    }




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
