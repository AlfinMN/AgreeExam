package com.test.agreeexam.config

import com.test.agreeexam.home.ui.HomeActivity
import com.test.agreeexam.home.ui.SearchActivity
import dagger.Component

@Component(modules = [NetworkModul::class])
interface AppComponent {
   fun inject(homeActivity: HomeActivity)
   fun inject(searchActivity: SearchActivity)
}