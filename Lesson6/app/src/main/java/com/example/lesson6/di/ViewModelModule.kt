package com.example.lesson6.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lesson6.presentation.ui.example.ExampleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ExampleViewModel::class)
    abstract fun exampleViewModel(exampleViewModel: ExampleViewModel): ViewModel
}