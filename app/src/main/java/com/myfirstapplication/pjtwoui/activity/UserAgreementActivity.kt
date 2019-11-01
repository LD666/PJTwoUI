package com.myfirstapplication.pjtwoui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myfirstapplication.pjtwoui.R
import kotlinx.android.synthetic.main.activity_user_agreement.*

class UserAgreementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_agreement)

        button_agr.setOnClickListener(View.OnClickListener {

            var agr = getSharedPreferences("agr", Context.MODE_PRIVATE)
            var editor = agr.edit()
            editor.putString("isAgr", "true")
            editor.commit()
            var myIntent = Intent(this, WelcomeMainActivity::class.java)
            startActivity(myIntent)
        })

        agree_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, WelcomeMainActivity::class.java)
            startActivity(myIntent)
        })

    }
}
