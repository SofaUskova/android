package com.example.android.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.android.OnFragmentInteractionListener
import com.example.android.R
import com.example.android.StartFragment
import com.example.android.fragments.*
import com.example.android.utils.onCreateDialogAbout
import com.example.android.utils.onCreateDialogAboutMe
import com.example.android.utils.onCreateDialogExit

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    OnFragmentInteractionListener, StartFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = ContentFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, firstFragment).commit()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun startFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CreateTextFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onFragmentInteraction(fileName: String) {
//        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container, TextRefactorFragment().sendData(fileName))
//                .addToBackStack(null)
//                .commit()
//        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TextRefactorFragment().sendData(fileName))
                .addToBackStack(null)
                .commit()
//        }
    }

    override fun onFragmentInteraction(buttonId: Int) {
        when (buttonId) {
            R.id.buttonAbout ->
                onCreateDialogAbout(this).show()
            R.id.buttonAboutMe ->
                onCreateDialogAboutMe(this).show()
            R.id.buttonExit ->
                onCreateDialogExit(this).show()
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ContentFragment())
                    .commit()
            }
            R.id.nav_text_refactor -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ListTextFragment())
                    .commit()
            }
            R.id.nav_calculator -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CalculatorFragment())
                    .commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
