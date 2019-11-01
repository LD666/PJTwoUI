package com.myfirstapplication.pjtwoui.recycleradapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LandlordActivity
import com.myfirstapplication.pjtwoui.data.mydataclass.FakeImage
import com.myfirstapplication.pjtwoui.data.mydataclass.Property
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.fragments.AddTenantFragment
import com.myfirstapplication.pjtwoui.myinterface.SharedToInterface
import kotlinx.android.synthetic.main.landlord_property_list_text.view.*
import kotlinx.android.synthetic.main.landlord_property_list_text.view.imageView_pro


class PropertyListHandler(view: View): RecyclerView.ViewHolder(view){

    var address = view.text_view_property_address
    var city = view.text_view_property_city
    var state = view.text_view_property_state
    var country = view.text_view_property_country
    var img = view.imageView_pro

    var but = view.shared

}

class PropertyListAdapter(var items: List<Property>, var context: Context): RecyclerView.Adapter<PropertyListHandler>() {

    var myImg = FakeImage.houseList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyListHandler {
        var res = PropertyListHandler(LayoutInflater.from(context).inflate(R.layout.landlord_property_list_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: PropertyListHandler, position: Int) {
        holder.address.text = "Address: " + items[position].propertyaddress
        holder.city.text = "City: " + items[position].propertycity
        holder.state.text = "State: " + items[position].propertystate
        holder.country.text = "Country: " + items[position].propertycountry

        holder.but.visibility = View.GONE

        this.context?.let { Glide.with(it).load(myImg[position%myImg.size]).into(holder.img) }

        holder.img.setOnClickListener(View.OnClickListener {

            Log.i("MMP", "mmp")

            var com = context as LandlordActivity
            var addTenantFragment = AddTenantFragment()
            com.supportFragmentManager.beginTransaction().replace(R.id.landlord_main, addTenantFragment).commit()

            var saveProId = MyApplication.context.getSharedPreferences("theProID", Context.MODE_PRIVATE)
            var editor = saveProId.edit()
            var theProID = items[position].id
            editor.putString("saveID", theProID)

            editor.commit()

            Log.i("getSaveID", theProID)
            Log.i("getSaveID", saveProId.getString("saveID", null))


        })

        holder.but.setOnClickListener(View.OnClickListener {

            var goto = context as SharedToInterface
            goto.go()

        })
    }
}