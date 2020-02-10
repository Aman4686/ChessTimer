package com.example.chesstimer.common.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.chesstimer.R
import javax.inject.Inject


class TimerNavigation @Inject constructor(activity: AppCompatActivity) : TimerNavigator {

    val navController: NavController = Navigation.findNavController(activity, R.id.nav_host_fragment)
}