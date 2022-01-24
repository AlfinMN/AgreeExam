package com.test.agreeexam.config

import com.test.agreeexam.home.ui.HomeActivity
import dagger.Component

@Component(modules = [NetworkModul::class])
interface AppComponent {
   fun inject(homeActivity: HomeActivity)
}