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
import com.myfirstapplication.pjtwoui.recycleradapter.TenantListAdapter
import com.myfirstapplication.pjtwoui.viewmodel.TenantListViewModel
import kotlinx.android.synthetic.main.fragment_tenant_list.view.*

class LandlordTenantsListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_tenant_list, container, false)

        var viewModel = ViewModelProviders.of(this).get(TenantListViewModel::class.java)

        view.tenant_list_recycler.layoutManager = LinearLayoutManager(view.context)

        viewModel.onCall().observe(this, Observer {

            val tenantListData = it.tenants
            view.tenant_list_recycler.adapter = TenantListAdapter(tenantListData, context)
        })

        return view
    }

}