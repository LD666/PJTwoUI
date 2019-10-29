package com.myfirstapplication.pjtwoui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.fragments.MyMapFragment
import kotlinx.android.synthetic.main.activity_welcome_main.*

class WelcomeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_main)


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
