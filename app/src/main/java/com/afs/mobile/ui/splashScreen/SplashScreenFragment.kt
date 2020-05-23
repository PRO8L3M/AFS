package com.afs.mobile.ui.splashScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.afs.mobile.R
import com.afs.mobile.ext.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.splashScreenFinished.observe(viewLifecycleOwner, Observer(::exitSplashScreen))
        viewModel.delaySplashScreen()
    }

    private fun exitSplashScreen(isSplashScreenFinished: Boolean) {
        if (isSplashScreenFinished) navigateTo(R.id.action_splashScreenFragment_to_startFragment)
    }
}
