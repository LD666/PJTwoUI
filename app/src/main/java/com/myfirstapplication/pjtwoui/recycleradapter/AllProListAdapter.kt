package com.myfirstapplication.pjtwoui.recycleradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.mydataclass.FakeImage
import com.myfirstapplication.pjtwoui.data.mydataclass.GetAllProForTenants
import kotlinx.android.synthetic.main.landlord_add_ten_list_text.view.*

class AllProListHandler(view: View): RecyclerView.ViewHolder(view){

    var addr = view.text_add_ten_address
    var cit = view.text_view_add_ten_city
    var sta = view.text_view_add_ten_state
    var coun = view.text_view_add_ten_country

    var theImg = view.img_ten_add

}

class AllProListAdapter(var items: List<GetAllProForTenants>, var context: Context?): RecyclerView.Adapter<AllProListHandler>() {

    var myFackImgList = FakeImage.houseList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProListHandler {

        var res = AllProListHandler(LayoutInflater.from(context).inflate(R.layout.landlord_add_ten_list_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {

        return items.size
    }


    override fun onBindViewHolder(holder: AllProListHandler, position: Int) {

        holder.addr.text = "Address: " + items[position].propertyaddress
        holder.cit.text = "City: " + items[position].propertycity
        holder.sta.text = "State: " + items[position].propertystate
        holder.coun.text = "Country: " + items[position].propertycountry

        this.context?.let { Glide.with(it).load(myFackImgList[position%myFackImgList.size]).into(holder.theImg) }
    }
}