package com.myfirstapplication.pjtwoui.infowindow

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.myfirstapplication.pjtwoui.R
import kotlinx.android.synthetic.main.info_window.view.*

class InfoWindow(val context: Fragment): GoogleMap.InfoWindowAdapter {

    private val contents = context.layoutInflater.inflate(R.layout.info_window, null)

    override fun getInfoContents(maker: Marker?): View {
        contents.textView_address_infow.text = maker?.title
//        Glide.with(context).load("https://recnikmedia.com/wp-content/uploads/2019/02/House-1-IMG_3117.jpg").into(contents.show_img_infow)
        return contents
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }




}