package com.rs.tmobiledemosample.core.navigation

/**
 * Created by shankar
 * Screen helps to define routes between screens
 */
sealed class Screen(val route: String) {

    object StartToLaunchScreen: Screen("start_to_launch_screen")
    object LaunchToAuthScreen: Screen("launch_to_auth_screen")
    object AuthToMainScreen: Screen("auth_to_main_screen")
    object MaintoStudentScreen: Screen("main_to_student_screen")
    object StudentScreenToMapScreen: Screen("student_to_map_screen")


}