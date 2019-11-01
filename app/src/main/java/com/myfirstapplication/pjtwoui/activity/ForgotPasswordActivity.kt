package com.myfirstapplication.pjtwoui.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.myfirstapplication.pjtwoui.R
import com.myfirstapplication.pjtwoui.databinding.ActivityForgotPasswordBinding
import com.myfirstapplication.pjtwoui.myinterface.ForgotPasswordInterface
import com.myfirstapplication.pjtwoui.viewmodel.ForgetPasswordViewModel
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity(), ForgotPasswordInterface {
    override fun onEmailsuccess(forgotPasswordResponse: LiveData<JsonObject>) {

        forgotPasswordResponse.observe(this, Observer {

            var msg = it.get("msg").asString
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


            if(msg != "Email is not register"){
                var channelName = NotificationChannel("channelID", "myChannelName", NotificationManager.IMPORTANCE_DEFAULT)
                channelName.description = "myDescription"
                var notificationManger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManger.createNotificationChannel(channelName)


                var notificationBuilder = Notification.Builder(this@ForgotPasswordActivity, channelName.id)

                notificationBuilder.setChannelId(channelName.id)

                notificationBuilder.setContentTitle("Your password reminder")
                var pass = it.get("userpassword").asString
                notificationBuilder.setContentText("your password is: $pass")

                notificationBuilder.setSmallIcon(R.drawable.ic_notifications_active_black_24dp)

                notificationBuilder.setAutoCancel(true)

                notificationManger.notify(1, notificationBuilder.build())
            }


            try {
                var pass = it.get("userpassword").asString
                text_view_show_password.setText(pass)
            }catch (e: IllegalStateException){
                text_view_show_password.setText("Email is not Valid")
            }

        })

    }

    override fun onIsEmpty() {
        Toast.makeText(this, "Empty Email", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val binding: ActivityForgotPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        val viewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel::class.java)
        binding.forgotViewModel = viewModel

        viewModel.forgotPasswordInterface = this

        forgot_back.setOnClickListener(View.OnClickListener {

            var myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)

        })

    }
}
