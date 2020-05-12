package com.afs.mobile.di

import com.afs.mobile.data.repository.Repository
import com.afs.mobile.ui.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules : ModuleLoader() {
    override val modules: List<Module> =
        listOf(
            viewModelModule,
            repositoryModule
        )
}

private val viewModelModule = module {
    viewModel { StartViewModel(get()) }
}

private val repositoryModule = module {
    single { Repository() }
}
