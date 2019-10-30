package com.myfirstapplication.pjtwoui.recycleradapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.mydataclass.Property
import kotlinx.android.synthetic.main.landlord_property_list_text.view.*


class RemovePropertyListHandler(view: View): RecyclerView.ViewHolder(view){

    var address = view.text_view_property_address
    var city = view.text_view_property_city
    var state = view.text_view_property_state
    var country = view.text_view_property_country
    var status = view.text_view_property_status
    var purchaseprice = view.text_view_property_purchaseprice
    var mortageinfo = view.text_view_property_mortageinfo

    var img = view.imageView3

}

class RemovePropertyListAdapter(var items: List<Property>, var context: Context?): RecyclerView.Adapter<RemovePropertyListHandler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemovePropertyListHandler {
        var res = RemovePropertyListHandler(LayoutInflater.from(context).inflate(R.layout.landlord_property_list_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: RemovePropertyListHandler, position: Int) {
        holder.address.text = items[position].propertyaddress
        holder.city.text = items[position].propertycity
        holder.state.text = items[position].propertystate
        holder.country.text = items[position].propertycountry
        holder.status.text = items[position].propertystatus
        holder.purchaseprice.text = items[position].propertypurchaseprice
        holder.mortageinfo.text = items[position].propertymortageinfo


        holder.img.setOnClickListener(View.OnClickListener {

            var bundle = Bundle()

        })

    }
}