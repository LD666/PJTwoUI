package com.myfirstapplication.pjtwoui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.databinding.ActivityRegisterBinding
import com.myfirstapplication.pjtwoui.myinterface.RegisterInterface
import com.myfirstapplication.pjtwoui.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterInterface {
    override fun onStarted() {
        Toast.makeText(this, "Register Start", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(registerResponse: LiveData<String>) {
//        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
        registerResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onEmailWrong() {
        Toast.makeText(this, "Email is wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onFalse() {
        Toast.makeText(this, "Register false", Toast.LENGTH_SHORT).show()
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)


        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        val viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding.registerdata = viewModel

        viewModel.registerInterface = this

        signin_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, WelcomeMainActivity::class.java)
            startActivity(myIntent)

        })


    }
}
