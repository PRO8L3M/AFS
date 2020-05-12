package com.afs.mobile.ui.start

import androidx.lifecycle.ViewModel
import com.afs.mobile.data.repository.Repository

class StartViewModel(private val repository: Repository) : ViewModel() {

    fun getText() = repository.text
}