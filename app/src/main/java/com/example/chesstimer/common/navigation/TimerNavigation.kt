package com.example.chesstimer.common.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.chesstimer.MainActivity
import com.example.chesstimer.R
import javax.inject.Inject


class TimerNavigation @Inject constructor(val activity: MainActivity) : TimerNavigator {

    val navController: NavController by lazy { Navigation.findNavController(activity , R.id.nav_host_fragment) }

    override fun navigateToSettingsList() {
        navController.navigate(R.id.action_creatorFragment_to_listFragment)
    }

    override fun navigateToCreator() {
        navController.navigate(R.id.action_timerFragment_to_creatorFragment)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }
}