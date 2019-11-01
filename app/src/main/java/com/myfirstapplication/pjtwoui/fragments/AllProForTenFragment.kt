package com.myfirstapplication.pjtwoui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.recycleradapter.AllProListAdapter
import com.myfirstapplication.pjtwoui.viewmodel.Tenant.TenantActViewModel
import kotlinx.android.synthetic.main.fragment_all_pro_for_ten.view.*

class AllProForTenFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_all_pro_for_ten, container, false)

        var viewModel = ViewModelProviders.of(this).get(TenantActViewModel::class.java)

        view.all_pro_list_recycler.layoutManager = LinearLayoutManager(context)

        viewModel.getTenList().observe(this, Observer {

            var lst = it.allProForTen
            Log.i("showList", lst.toString())

            view.all_pro_list_recycler.adapter = AllProListAdapter(lst, context)

        })

        return view
    }

}