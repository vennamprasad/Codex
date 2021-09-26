package com.android.codex

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.codex.databinding.ActivityMainBinding
import com.android.lib.SmoothBottomBar
import com.google.android.material.navigation.NavigationView
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.EasyPermissions.onRequestPermissionsResult
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: SmoothBottomBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        bottomNavigationView = binding.root.findViewById(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_libraries, R.id.nav_updates
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setupSmoothBottomMenu(navController)
    }

    private fun setupSmoothBottomMenu(navController: NavController) {
        val view:View=this.bottomNavigationView
        val popupMenu = PopupMenu(this,view )
        popupMenu.inflate(R.menu.bottom_navigation_menu)
        val menu = popupMenu.menu
        bottomNavigationView.setupWithNavController(menu, navController)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // EasyPermissions handles the request result.
        onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(1)
    private fun checkPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_SMS)) {
            // Have permission, do the thing!
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(
                this, getString(R.string.app_name),
                1, Manifest.permission.READ_SMS
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Timber.d("onPermissionsGranted:" + requestCode + ":" + perms.size)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Timber.d("onPermissionsGranted:" + requestCode + ":" + perms.size)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}