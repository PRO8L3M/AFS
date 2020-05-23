package com.afs.mobile.di

import com.afs.mobile.data.localDataSource.LocalDataSource
import com.afs.mobile.data.remoteDataSource.RemoteDataSource
import com.afs.mobile.database.AppDatabase
import com.afs.mobile.repository.TaskRepositoryImpl
import com.afs.mobile.ui.splashScreen.SplashScreenViewModel
import com.afs.mobile.ui.tasks.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {
    val modules = listOf(
        viewModelModule,
        repositoryModule,
        databaseModule,
        dataSourceModule
    )
}

private val viewModelModule = module {
    viewModel { TasksViewModel(get()) }
    viewModel { SplashScreenViewModel() }
}

private val repositoryModule = module {
    single { TaskRepositoryImpl(get(), get()) }
}

private val dataSourceModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource() }
}

private val databaseModule = module {
    single { AppDatabase.buildDatabase(get()) }
}
