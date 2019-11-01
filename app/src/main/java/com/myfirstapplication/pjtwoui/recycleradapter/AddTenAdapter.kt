package com.myfirstapplication.pjtwoui.recycleradapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LandlordTenantListActivity
import com.myfirstapplication.pjtwoui.data.mydataclass.Property
import com.myfirstapplication.pjtwoui.fragments.AddTenantFragment
import kotlinx.android.synthetic.main.landlord_add_ten_list_text.view.*

class AddTenHandler(view: View): RecyclerView.ViewHolder(view){


    var addtenaddress = view.text_add_ten_address
    var addtencity = view.text_view_add_ten_city
    var addtenstate = view.text_view_add_ten_state
    var addtencountry = view.text_view_add_ten_country

    var addtenimg = view.img_ten_add

}

class AddTenAdapter(var items: List<Property>, var context: Context): RecyclerView.Adapter<AddTenHandler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTenHandler {
        var res = AddTenHandler(LayoutInflater.from(context).inflate(R.layout.landlord_add_ten_list_text, parent, false))
        return res
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: AddTenHandler, position: Int) {
        holder.addtenaddress.text = "Address: " + items[position].propertyaddress
        holder.addtencity.text = "City: " + items[position].propertycity
        holder.addtenstate.text = "State: " + items[position].propertystate
        holder.addtencountry.text = "Country: " + items[position].propertycountry

        holder.addtenimg.setOnClickListener(View.OnClickListener {

            Log.i("MMP", "mmp")

            var com = this.context as LandlordTenantListActivity
            var addTenantFragment = AddTenantFragment()
            com.supportFragmentManager.beginTransaction().replace(R.id.rep_this_id, addTenantFragment).commit()

        })

    }
}