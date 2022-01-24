package com.test.agreeexam.config

import android.app.Application

class AgreeApp : Application() {
    val appComponent : AppComponent = DaggerAppComponent.create()
}
