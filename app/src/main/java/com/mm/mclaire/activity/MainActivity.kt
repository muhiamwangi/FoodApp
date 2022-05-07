package com.mm.mclaire.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mm.mclaire.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    /**
    Navigation Component constituents:
        1.Navigation Graph
        2.NavHost
        3.NavController

    2. NavHost:to display destinations from a nav_graph within an activity.

    -When you navigate between fragments, the destination shown in the NavHost is updated.

    -You'll use a built-in implementation, called NavHostFragment, in your MainActivity.

    3. NavController:
         NavController object lets you control the navigation between destinations displayed in the NavHost.

    -When working with intents, you had to call startActivity to navigate to a new screen.

    -With the Navigation component, you can call the NavController's navigate() method
    to swap the fragment that's displayed.

    -The NavController also helps you handle common tasks like responding to the system "up"
    button to navigate back to the previously displayed fragment*/

    //Safe Args:Gradle plugin that assist you with type safety when passing data between fragments.

       //using a navigation controller:
       //NavController object lets you control the navigation between destinations displayed in the NavHost.
        val bottomNavigation=findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController=Navigation.findNavController(this, R.id.main_fragment)
        NavigationUI.setupWithNavController(bottomNavigation,navController)

            }
}