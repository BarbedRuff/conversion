package com.ok.conversion.di.component

import com.ok.conversion.di.module.NetworkModule
import com.ok.conversion.presentation.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}