package com.example.chesstimer.common.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.chesstimer.R
import javax.inject.Inject


class TimerNavigation @Inject constructor(activity: AppCompatActivity) : TimerNavigator {

    private val navController: NavController = Navigation.findNavController(activity, R.id.nav_host_fragment)

    override fun navigateToSettings() {
 //       navController.navigate(R.id.action_timerFragment_to_settingFragment)
    }

    override fun navigateToCreator() {
        navController.navigate(R.id.action_timerFragment_to_creatorFragment)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }
}