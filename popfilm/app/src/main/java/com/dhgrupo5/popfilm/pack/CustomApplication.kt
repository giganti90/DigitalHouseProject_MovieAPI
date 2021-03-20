package com.dhgrupo5.popfilm.pack

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.dhgrupo5.popfilm.network.worker.CatchChannelDataWorker
import com.onesignal.OneSignal
import java.util.concurrent.TimeUnit

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        oneSignalInit()
        backgroundWork()
    }

    private fun backgroundWork(){
        val request = PeriodicWorkRequestBuilder<CatchChannelDataWorker>(
            CatchChannelDataWorker.REPEAT_INTERVAL,
            TimeUnit.HOURS
        ).build()

        WorkManager
            .getInstance( this )
            .enqueueUniquePeriodicWork(
                CatchChannelDataWorker.NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }

    private fun oneSignalInit(){
        OneSignal.startInit( this )
            .inFocusDisplaying( OneSignal.OSInFocusDisplayOption.Notification )
            .unsubscribeWhenNotificationsAreDisabled( true )
            .init()
    }
}