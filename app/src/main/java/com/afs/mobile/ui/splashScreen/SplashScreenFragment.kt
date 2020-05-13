package com.afs.mobile.ui.splashScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afs.mobile.R
import com.afs.mobile.common.BaseFragment
import com.afs.mobile.common.SPLASH_SCREEN_DURATION
import com.afs.mobile.ext.navigateTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewScope.launch {
            delay(SPLASH_SCREEN_DURATION)
            navigateTo(R.id.action_splashScreenFragment_to_startFragment)
        }
    }
}
