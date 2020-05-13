package com.afs.mobile.ext

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(requireContext(), text, duration).show()

fun Fragment.navigateTo(@IdRes destination: Int) {
    findNavController().navigate(destination)
}