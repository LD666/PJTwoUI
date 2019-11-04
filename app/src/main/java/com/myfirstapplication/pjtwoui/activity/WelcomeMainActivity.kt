package com.myfirstapplication.pjtwoui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.fragments.MyMapFragment
import kotlinx.android.synthetic.main.activity_welcome_main.*
import kotlinx.android.synthetic.main.activity_welcome_main.view.*

class WelcomeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_main)


        var agr = getSharedPreferences("agr", Context.MODE_PRIVATE)

        if(agr.getString("isAgr", null) == null){
            button_to_agree.visibility = View.VISIBLE
        }else{
            button_to_register.visibility = View.VISIBLE
        }


        button_to_agree.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, UserAgreementActivity::class.java)
            startActivity(myIntent)

        })

        button_to_register.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)

        })


        button_to_login.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)

        })

    }
}
