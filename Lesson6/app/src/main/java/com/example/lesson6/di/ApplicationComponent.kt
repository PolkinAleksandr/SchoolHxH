package com.example.lesson6.di

import com.example.lesson6.MyApplication
import com.example.lesson6.presentation.ui.example.ExampleFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: MyApplication): ApplicationComponent
    }
}