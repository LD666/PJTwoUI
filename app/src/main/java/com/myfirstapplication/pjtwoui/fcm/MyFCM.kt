package com.myfirstapplication.pjtwoui.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.myfirstapplication.pjtwoui.R

class MyFCM: FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(ContentValues.TAG, "From: " + remoteMessage.from!!)

        // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.d(ContentValues.TAG, "Message data payload: " + remoteMessage.data)

//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob()
//            } else {
//                // Handle message within 10 seconds
//                handleNow()
//            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(ContentValues.TAG, "Message Notification Body: " + remoteMessage.notification!!.body!!)

            Log.d("Refreshed token: ", remoteMessage.notification!!.body!!.toString())

            var channelName = NotificationChannel("channelID", "myChannelName", NotificationManager.IMPORTANCE_DEFAULT)

            channelName.description = "myDescription"

            var notificationManger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManger.createNotificationChannel(channelName)

//            var myIntent = Intent(this, SecondActivity::class.java)

//            var pendingIntent = PendingIntent.getActivity(this, 321, myIntent, 0)

            var notificationBuilder = Notification.Builder(this, channelName.id)

            notificationBuilder.setChannelId(channelName.id)

            notificationBuilder.setContentTitle("myTitle")

            notificationBuilder.setContentText(remoteMessage.notification!!.body!!.toString())

            notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)

//            notificationBuilder.setContentIntent(pendingIntent)

            notificationBuilder.setAutoCancel(true)

            notificationManger.notify(1, notificationBuilder.build())

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

//    override fun onNewToken(token: String?) {
//        Log.d(ContentValues.TAG, "Refreshed token: " + token!!)
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // Instance ID token to your app server.
//        //sendRegistrationToServer(token)
//    }

}