package com.myfirstapplication.pjtwoui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.databinding.ActivityRegisterBinding
import com.myfirstapplication.pjtwoui.myinterface.RegisterInterface
import com.myfirstapplication.pjtwoui.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

const val RC_SIGN_IN = 123

class RegisterActivity : AppCompatActivity(), RegisterInterface {
    override fun onStarted() {
        Toast.makeText(this, "Register Start", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(registerResponse: LiveData<String>) {
//        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
        registerResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            if(it == "successfully registered"){
                var myIntent = Intent(this, LoginActivity::class.java)
                startActivity(myIntent)

                var agr = getSharedPreferences("agr", Context.MODE_PRIVATE)
                var editor = agr.edit()
                editor.clear()

            }

        })
    }

    override fun onEmailWrong() {
        Toast.makeText(this, "Email is wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onFalse() {
        Toast.makeText(this, "Register False Email not Valid", Toast.LENGTH_SHORT).show()
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)



        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        val viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding.registerdata = viewModel

        viewModel.registerInterface = this


        var agr = getSharedPreferences("agr", Context.MODE_PRIVATE)

        if(agr.getString("isAgr", null) == null){
            button_register.visibility = View.GONE
            button_Agreemnt.visibility = View.VISIBLE
        }else{
            button_register.visibility = View.VISIBLE
            button_Agreemnt.visibility = View.GONE
        }



        signin_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)

        })

        checkBox.visibility = View.GONE

        button_Agreemnt.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, UserAgreementActivity::class.java)
            startActivity(myIntent)

        })




        //////////////////////////////////////////////////////////////////////////////////////////////
        //        GOOGLE SIGN IN


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

            var myIntent = Intent(this, TennantActivity::class.java)
            startActivity(myIntent)

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

}
