package com.mm.mclaire.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mm.mclaire.R
import com.mm.mclaire.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

        lateinit var binding:ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

       //NavController object lets you control the navigation between destinations displayed in the NavHost.
        val bottomNavigation=findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController=Navigation.findNavController(this, R.id.main_fragment)
        NavigationUI.setupWithNavController(bottomNavigation,navController)

            //setting actionBar using navigation component
          //  setupActionBarWithNavController(findNavController(R.id.main_fragment))
        }
}
/**
Navigation Component constituents:
1.Navigation Graph
2.NavHost
3.NavController

2. NavHost:to display destinations from a nav_graph within an activity.

-When you navigate between fragments, the destination shown in the NavHost is updated.

-You'll use a built-in implementation, called NavHostFragment, in your MainActivity.
(NavHostFragment-Fragment which you add to your layout as a window that swaps in $
out different fragment destinations that are included in the nav-graph)

3. NavController:
NavController object lets you control the navigation between destinations displayed in the NavHost.

-When working with intents, you had to call startActivity to navigate to a new screen.

-With the Navigation component, you can call the NavController's navigate() method
to swap the fragment that's displayed.

-The NavController also helps you handle common tasks like responding to the system "up"
button to navigate back to the previously displayed fragment i.e

          //Navigating from a fragment to another using back button on the action bar(from addFragment to listFragment )
 override fun onSupportNavigateUp(): Boolean {
 val navController=findNavController(R.id.fragment)
 return navController.navigateUp()||super.onSupportNavigateUp()
}
 */

//Safe Args:Gradle plugin that assist you with type safety when passing data between fragments.


/**NAVIGATION COMPONENT EXTENDED
 1.navHostFragment-Built in implementation of NavHost in MainActivity:used to display destinations from a nav_graph within an activity
 2.navController-It instructs how navigation occurs
- Navigation can be dictated either using ACTION or DESTINATION
- An action refers to a path which a user can take in your app

 findNavController().navigate(R.id.win_action)---->Navigating by action
 findNavController().navigate(R.id.favorite_fragment.xml)---->Navigating by destination

 :but an even better way to do this is use: SAFE ARGS PLUGIN (Add it to gradle 1st)

-Safe args plugin generates classes based off your nav_graph to ensure:
       type safe access to arguments for destinations$ actions
1. It will make 'direction classes' for any destination with 'actions'
2. It will make 'Args' classes for every destination with 'arguments'

EXAMPLE 1:
 val directions: NavDirections= GameFragmentDirections.winAction() //Direction classes
 findNavController().navigate(directions)

            (or)
EXAMPLE 2:
(a).
val directions: NavDirections= GameFragmentDirections.winAction(score=23)
//This destination has arguments,(score=23),prompting safe-args plugin to generate an Args class
findNavController().navigate(directions)

                    |
                    |
(b).
//Here, we are able to access the arguments that we passed by using the generated Args class                 |
 val args by NavArgs<WinFragmentArgs>()
 val score=args.score

- Navigation component includes additional navigation UI lib that has support for :Action Bar,
Bottom Navigation,Navigation Drawers,ToolBars,OptionMenus  e.t.c

 */