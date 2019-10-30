package com.myfirstapplication.pjtwoui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LandlordActivity
import com.myfirstapplication.pjtwoui.recycleradapter.RemovePropertyListAdapter
import com.myfirstapplication.pjtwoui.viewmodel.RemoveProViewModel
import kotlinx.android.synthetic.main.activity_landlord.*
import kotlinx.android.synthetic.main.fragment_landlord_remove.view.*

class LandlordRemoveFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_landlord_remove, container, false)

        var viewModel = ViewModelProviders.of(this).get(RemoveProViewModel::class.java)

        view.landlord_remove_list_recycler.layoutManager = LinearLayoutManager(context)

        viewModel.getList().observe(this, Observer {

            var listData = it.property
            view.landlord_remove_list_recycler.adapter = RemovePropertyListAdapter(listData, this.context)

        })

        view.remove_property_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this.context, LandlordActivity::class.java)
            startActivity(myIntent)

        })

        return view
    }

}