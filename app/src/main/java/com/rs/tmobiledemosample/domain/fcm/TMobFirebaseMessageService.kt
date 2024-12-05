package com.rs.tmobiledemosample.domain.fcm

import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Intent
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.rs.tmobiledemosample.datastore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.rs.tmobiledemosample.MainActivity
import com.rs.tmobiledemosample.R
import kotlin.random.Random

class TMobFirebaseMessageService :  FirebaseMessagingService() {

    private val random = Random
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let { message ->
            sendNotification(message)
        }
    }

    private fun sendNotification(message: RemoteMessage.Notification) {
        // If you want the notifications to appear when your app is in foreground

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, FLAG_IMMUTABLE
        )

        val channelId = this.getString(R.string.default_notification_channel_id)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(message.title)
            .setContentText(message.body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, CHANNEL_NAME, IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        manager.notify(random.nextInt(), notificationBuilder.build())
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("FCM","New token: $token")
        GlobalScope.launch {
            saveFCMToken(token)
        }
    }
    //save fcm token in shared preference , we can use this token to send back to server
    private suspend fun saveFCMToken(token: String) {
        val fcmTokenKey = stringPreferencesKey("fcm_token")
        baseContext.datastore.edit { pref ->
            pref[fcmTokenKey] = token
        }
    }

    companion object {
        const val CHANNEL_NAME = "FCM notification channel"
    }

}