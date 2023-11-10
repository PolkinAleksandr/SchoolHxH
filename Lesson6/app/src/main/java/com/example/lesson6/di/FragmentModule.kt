package com.example.lesson6.di

import com.example.lesson6.presentation.ui.example.ExampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun exampleFragment(): ExampleFragment
}