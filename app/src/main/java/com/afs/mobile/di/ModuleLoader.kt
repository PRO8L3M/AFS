package com.afs.mobile.di

import org.koin.core.module.Module

abstract class ModuleLoader {
    abstract val modules: List<Module>
}