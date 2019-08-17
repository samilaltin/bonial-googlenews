package com.samilaltin.bonialnews

import android.app.Activity
import android.app.Application
import com.codemonkeylabs.fpslibrary.TinyDancer
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.samilaltin.bonialnews.di.component.DaggerAppComponent
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import javax.inject.Inject

/**
 * Created by samilaltin on 09/08/2019
 */
class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private var firebaseAnalytics: FirebaseAnalytics? = null

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        initFirebaseAnalytics()
        initTinyDancer()
        initLogger()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun initFirebaseAnalytics() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAnalytics!!.setAnalyticsCollectionEnabled(true)
    }

    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private fun initTinyDancer() {
        TinyDancer.create()
            .redFlagPercentage(.1f) // set red indicator for 10%....different from default
            .startingXPosition(200)
            .startingYPosition(600)
            .show(this)
    }
}
