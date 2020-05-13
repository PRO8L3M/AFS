package com.afs.mobile.di

import com.afs.mobile.data.database.AppDatabase
import com.afs.mobile.data.repository.Repository
import com.afs.mobile.ui.tasks.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules : ModuleLoader() {
    override val modules: List<Module> =
        listOf(
            viewModelModule,
            repositoryModule,
            databaseModule
        )
}

private val viewModelModule = module {
    viewModel { TasksViewModel(get()) }
}

private val repositoryModule = module {
    single { Repository(get()) }
}

private val databaseModule = module {
    single { AppDatabase.buildDatabase(get()) }
}
