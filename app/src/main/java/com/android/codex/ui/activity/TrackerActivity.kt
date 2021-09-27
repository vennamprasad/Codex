package com.android.codex.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.codex.others.Constants
import com.android.codex.R
import com.android.codex.databinding.ActivityTrackerBinding
import com.android.lib.SmoothBottomBar
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_codex.*

@AndroidEntryPoint
class TrackerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTrackerBinding
    private lateinit var bottomNavigationView: SmoothBottomBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragment(intent)
        setSupportActionBar(binding.appBarTracker.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        bottomNavigationView = binding.root.findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemReselectedListener {
            /*No-Op*/
        }
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.runFragment, R.id.statisticsFragment, R.id.settingsFragment,R.id.setupFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setupSmoothBottomMenu(navController)

        nav_host_fragment_content_main.findNavController().addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                        bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }
            }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragment(intent)
    }

    private fun setupSmoothBottomMenu(navController: NavController) {
        val view: View = this.bottomNavigationView
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.tracker_bottom_nav_menu)
        val menu = popupMenu.menu
        bottomNavigationView.setupWithNavController(menu, navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    private fun navigateToTrackingFragment(intent: Intent?){
        if (intent?.action== Constants.ACTION_SHOW_TRACKING_FRAGMENT){
            nav_host_fragment_content_main.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }
}