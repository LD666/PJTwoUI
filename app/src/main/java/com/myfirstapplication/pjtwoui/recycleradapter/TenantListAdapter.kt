package com.myfirstapplication.pjtwoui.recycleradapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.activity.LandlordActivity
import com.myfirstapplication.pjtwoui.data.mydataclass.FakeImage
import com.myfirstapplication.pjtwoui.data.mydataclass.Tenant
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.fragments.ListTenantFragment
import com.myfirstapplication.pjtwoui.myinterface.PassTenInfoInterface
import kotlinx.android.synthetic.main.tenants_list_text.view.*

class TenantHandler(view: View): RecyclerView.ViewHolder(view){

    var tName = view.text_view_t_name
    var tEmail = view.text_view_t_email
    var tAddress = view.text_view_t_address
    var tMobile = view.text_view_t_mobile
    var img = view.ten_img

}

class TenantListAdapter(var item: List<Tenant>, var context: Context?) : RecyclerView.Adapter<TenantHandler>(){

    var tenImg = FakeImage.tenImgList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantHandler {
        var res = TenantHandler(LayoutInflater.from(context).inflate(R.layout.tenants_list_text, parent, false))
        return res
    }


    override fun getItemCount(): Int {
        return item.size
    }


    override fun onBindViewHolder(holder: TenantHandler, position: Int) {
        holder.tName.text = "Name: " + item[position].tenantame
        holder.tEmail.text = "Email: " + item[position].tenantemail
        holder.tAddress.text = "Address: " + item[position].tenantaddress
        holder.tMobile.text = "Tel: " + item[position].tenantmobile

        this.context?.let { Glide.with(it).load(tenImg[position%tenImg.size]).into(holder.img) }

        holder.img.setOnClickListener(View.OnClickListener {

            var passData = this.context as PassTenInfoInterface
            var bundle = Bundle()

            var nm = item[position].tenantame
            var em = item[position].tenantemail
            var ad = item[position].tenantaddress
            var mb = item[position].tenantmobile

            var ig = tenImg[position%tenImg.size]


            Log.i("showPass", nm)
            Log.i("showPass", em)
            Log.i("showPass", ad)
            Log.i("showPass", mb)

            bundle.putString("tName", nm)
            bundle.putString("tEmail", em)
            bundle.putString("tAddress", ad)
            bundle.putString("tMobile", mb)
            bundle.putString("tImg", ig)

//            Log.i("showPass", bundle.getStringArrayList("tName").toString())
//            Log.i("showPass", bundle.getStringArrayList("tEmail").toString())
//            Log.i("showPass", bundle.getStringArrayList("tAddress").toString())
//            Log.i("showPass", bundle.getStringArrayList("tMobile").toString())
//            Log.i("showPass", bundle.getStringArrayList("tImg").toString())

            var saveTenDit = MyApplication.context.getSharedPreferences("saveTenDit", Context.MODE_PRIVATE)
            var editor = saveTenDit.edit()
            editor.putString("tName", nm)
            editor.putString("tEmail", em)
            editor.putString("tAddress", ad)
            editor.putString("tMobile", mb)
            editor.putString("tImg", ig)
            editor.commit()


            Log.i("showPass", saveTenDit.getString("tName", null))
            Log.i("showPass", saveTenDit.getString("tEmail", null))
            Log.i("showPass", saveTenDit.getString("tAddress", null))
            Log.i("showPass", saveTenDit.getString("tMobile", null))
            Log.i("showPass", saveTenDit.getString("tImg", null))


            var listTenantFragment = ListTenantFragment()
            var com = this.context as LandlordActivity
            com.supportFragmentManager.beginTransaction().replace(R.id.landlord_main, listTenantFragment).commit()

            passData.passTen(bundle)
        })

    }
}