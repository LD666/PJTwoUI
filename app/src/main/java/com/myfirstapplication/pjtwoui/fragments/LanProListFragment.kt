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
import com.myfirstapplication.pjtwoui.recycleradapter.MyProAdapter
import com.myfirstapplication.pjtwoui.viewmodel.LandlordViewModel
import kotlinx.android.synthetic.main.fragment_lan_pro_list.view.*

class LanProListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_lan_pro_list, container, false)
        var viewModel = ViewModelProviders.of(this).get(LandlordViewModel::class.java)

        view.get_the_landlord_list_recycler.layoutManager = LinearLayoutManager(context)

        viewModel.list().observe(this, Observer {

            var theProList = it.property

            view.get_the_landlord_list_recycler.adapter = MyProAdapter(theProList, context)

        })

        return view
    }
}