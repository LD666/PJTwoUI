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
import com.myfirstapplication.pjtwoui.fragments.ShowProDetaFragment
import com.myfirstapplication.pjtwoui.myinterface.SharedToInterface
import kotlinx.android.synthetic.main.pro_text.view.*

class ViewHandler(view: View): RecyclerView.ViewHolder(view){

    var adr = view.text_view_pro_add
    var ci = view.text_view_pro_ci
    var st = view.text_view_pro_st
    var cn = view.text_view_pro_cn

    var img = view.imageView_pro
    var but = view.pro_shared
}

class MyProAdapter(var item: List<Property>, var context: Context?): RecyclerView.Adapter<ViewHandler>() {

    var tenImg = FakeImage.houseList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHandler {
        var res = ViewHandler(LayoutInflater.from(context).inflate(R.layout.pro_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {
        return item.size
    }


    override fun onBindViewHolder(holder: ViewHandler, position: Int) {

        holder.but.visibility = View.VISIBLE


        holder.adr.text = "Address: " + item[position].propertyaddress
        holder.ci.text = "City: " + item[position].propertycity
        holder.st.text = "state: " + item[position].propertystate
        holder.cn.text = "Country: " + item[position].propertycountry

        this.context?.let { Glide.with(it).load(tenImg[position%tenImg.size]).into(holder.img) }

        holder.img.setOnClickListener(View.OnClickListener {

            var ad = item[position].propertyaddress
            var ci = item[position].propertycity
            var st = item[position].propertystate
            var cn = item[position].propertycountry
            var sta = item[position].propertystatus
            var prz = item[position].propertypurchaseprice
            var mor = item[position].propertymortageinfo
            var img = tenImg[position%tenImg.size]

            var savePro = MyApplication.context.getSharedPreferences("thePro", Context.MODE_PRIVATE)
            var editor = savePro.edit()

            editor.putString("theImg", img)
            editor.putString("theAd", ad)
            editor.putString("theCi", ci)
            editor.putString("theSt", st)
            editor.putString("theCn", cn)
            editor.putString("theSta", sta)
            editor.putString("thePrz", prz)
            editor.putString("theMor", mor)
            editor.commit()


            Log.i("showEdi", savePro.getString("theAd", null))
            Log.i("showEdi", savePro.getString("theCi", null))
            Log.i("showEdi", savePro.getString("theSt", null))
            Log.i("showEdi", savePro.getString("theCn", null))
            Log.i("showEdi", savePro.getString("theSta", null))
            Log.i("showEdi", savePro.getString("thePrz", null))
            Log.i("showEdi", savePro.getString("theMor", null))

            var com = context as LandlordActivity
            var ShowProDetaFragment = ShowProDetaFragment()
            com.supportFragmentManager.beginTransaction().replace(R.id.landlord_main, ShowProDetaFragment).commit()

        })



        holder.but.setOnClickListener(View.OnClickListener {

            var goto = context as SharedToInterface
            goto.go()

        })


    }


}