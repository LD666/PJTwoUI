package com.myfirstapplication.pjtwoui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myfirstapplication.pjtwoui.R
import kotlinx.android.synthetic.main.activity_show_pass.*

class ShowPassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_pass)



        var passInfo = getSharedPreferences("passUserInfo", Context.MODE_PRIVATE)

        var checkE = passInfo.getString("theEmail", null)
        var checkP = passInfo.getString("thePass", null)


        yv_show_email.text = checkE
        text_view_show_pass.text = checkP


        back_to_log.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)

        })

    }
}
