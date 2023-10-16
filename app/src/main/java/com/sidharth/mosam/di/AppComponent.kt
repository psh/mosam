package com.sidharth.mosam.di

import com.sidharth.mosam.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}