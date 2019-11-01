package com.myfirstapplication.pjtwoui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.recycleradapter.PropertyListAdapter
import com.myfirstapplication.pjtwoui.viewmodel.LandlordViewModel
import kotlinx.android.synthetic.main.activity_landlord.view.*

class LandlordFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.activity_landlord, container, false)


        val viewModel = ViewModelProviders.of(this).get(LandlordViewModel::class.java)


        view.landlord_list_recycler.layoutManager = LinearLayoutManager(view.context)
//        landlord_list_recycler.adapter = myListAdapter(this, listData)

        viewModel.list().observe(this, Observer {

            var listData = it.property
            view.landlord_list_recycler.adapter = PropertyListAdapter(listData, view.context)

        })




//        view.float_button_to_add.setOnClickListener(View.OnClickListener {
//
//
//            var myIntent = Intent(view.context, AddRemoveActivity::class.java)
//            startActivity(myIntent)
//
//        })

        return view
    }

}