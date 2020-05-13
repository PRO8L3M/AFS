package com.afs.mobile.ext

import android.content.Context
import android.view.LayoutInflater

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)