package com.example.lesson6.di

import android.app.Application
import android.content.Context
import com.example.lesson6.MyApplication
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: MyApplication): Context {
        return app.applicationContext
    }
}