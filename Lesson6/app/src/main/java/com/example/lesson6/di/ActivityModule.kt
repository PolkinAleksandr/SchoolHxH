package com.example.lesson6.di

import com.example.lesson6.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity
}