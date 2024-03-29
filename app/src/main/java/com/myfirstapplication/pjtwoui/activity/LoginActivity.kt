package com.myfirstapplication.pjtwoui.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.data.network.UserApi
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication
import com.myfirstapplication.pjtwoui.data.repositories.MyApplication.Companion.context
import com.myfirstapplication.pjtwoui.databinding.ActivityLoginBinding
import com.myfirstapplication.pjtwoui.myinterface.LoginInterface
import com.myfirstapplication.pjtwoui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.sign_in_button
import kotlinx.android.synthetic.main.activity_register.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity(), LoginInterface {
    override fun onSuccess(loginResponse: LiveData<String>) {
        //var show = loginResponse.value!!.get("msg").asString
        loginResponse.observe(this, Observer {

            var msg = it
//            myTask().execute()


            var userInfo = MyApplication.context.getSharedPreferences("saveUserInfo", Context.MODE_PRIVATE)
            var usertype = userInfo.getString("utype", null)
            Log.i("showMeWTF", userInfo.getString("utype", null).toString())
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            if(msg == "landlord"){
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                var myIntent = Intent(this, LandlordActivity::class.java)
                startActivity(myIntent)
            }else if(msg == "tenant"){
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                var myIntent = Intent(this, TennantActivity::class.java)
                startActivity(myIntent)
            }else{
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onFalse() {
        Toast.makeText(this, "Login False", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        viewModel.loginInterface = this

        button_Reg.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)

        })

        button_login_goto_forgot.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(myIntent)

        })

//        login_back.setOnClickListener(View.OnClickListener {
//
//            var myIntent = Intent(this, WelcomeMainActivity::class.java)
//            startActivity(myIntent)
//
//        })


        //////////////////////////////////////////////////////////////////////////////////////
        /// google login

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        sign_in_button.setOnClickListener(View.OnClickListener {

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        })

    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
            showSettingDialoge()

//            var myIntent = Intent(this, TennantActivity::class.java)
//            startActivity(myIntent)

        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
        }

    }


    private fun showSettingDialoge(){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Tenant or Landlord")
        builder.setMessage("LOGIN as Tenant or Landlord")
        builder.setPositiveButton("Login as tenant", object: DialogInterface.OnClickListener{
            override fun onClick(dialoge: DialogInterface?, p1: Int) {

                var myIntent = Intent(this@LoginActivity, TennantActivity::class.java)
                startActivity(myIntent)
            }

        })
        builder.setNegativeButton("Login as Landlord", object: DialogInterface.OnClickListener{
            override fun onClick(dialoge: DialogInterface?, p1: Int) {

                var fak = getSharedPreferences("fakLog", Context.MODE_PRIVATE)
                var editor = fak.edit()
                editor.putString("fake", "fakeIt")
                editor.commit()

                Log.i("showFake", fak.getString("fake", null))


                var myIntent = Intent(this@LoginActivity, LandlordActivity::class.java)
                startActivity(myIntent)

            }

        })

        builder.show()
    }

}
