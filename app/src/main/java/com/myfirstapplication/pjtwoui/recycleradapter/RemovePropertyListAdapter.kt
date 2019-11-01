package com.myfirstapplication.pjtwoui.recycleradapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.mydataclass.Property
import com.myfirstapplication.pjtwoui.myinterface.RemovePassDataInterface
import kotlinx.android.synthetic.main.landlord_property_list_text.view.*


class RemovePropertyListHandler(view: View): RecyclerView.ViewHolder(view){

    var address = view.text_view_property_address
    var city = view.text_view_property_city
    var state = view.text_view_property_state
    var country = view.text_view_property_country

    var img = view.imageView_pro

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
        holder.address.text = "Address: " + items[position].propertyaddress
        holder.city.text = "City: " + items[position].propertycity
        holder.state.text = "State: " + items[position].propertystate
        holder.country.text = "Country: " + items[position].propertycountry


        holder.img.setOnClickListener(View.OnClickListener {

            var passData = this.context as RemovePassDataInterface
            var bundle = Bundle()

            bundle.putString("propertyID", items[position].id)
            bundle.putString("propertyaddress", items[position].propertyaddress)
            bundle.putString("propertycity", items[position].propertycity)
            bundle.putString("propertystate", items[position].propertystate)
            bundle.putString("propertycountry", items[position].propertycountry)
            bundle.putString("propertystatus", items[position].propertystatus)
            bundle.putString("propertypurchaseprice", items[position].propertypurchaseprice)
            bundle.putString("propertymortageinfo", items[position].propertymortageinfo)
//            Log.i("InItemAdapter", bundle.getString("propertyID"))
//            Log.i("InItemAdapter", bundle.getString("propertyaddress"))
//            Log.i("InItemAdapter", bundle.getString("propertycity"))
//            Log.i("InItemAdapter", bundle.getString("propertystate"))
//            Log.i("InItemAdapter", bundle.getString("propertycountry"))
//            Log.i("InItemAdapter", bundle.getString("propertystatus"))
//            Log.i("InItemAdapter", bundle.getString("propertypurchaseprice"))
//            Log.i("InItemAdapter", bundle.getString("propertymortageinfo"))
            passData.getRemoveID(bundle)
        })

    }
}