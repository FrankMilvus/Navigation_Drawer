package com.example.navigationdrawer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.example.navigationdrawer.fragments.ChatFragment
import com.example.navigationdrawer.fragments.MessageFragment
import com.example.navigationdrawer.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawer: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        drawer = ActivityMainBinding.inflate(layoutInflater)
        setContentView(drawer.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(drawer.toolbar)

        val toggle = ActionBarDrawerToggle(
            this@MainActivity, drawer.root, drawer.toolbar,
            R.string.open_navigation_drawer, R.string.close_navigation_drawer
        )

        drawer.root.addDrawerListener(toggle)
        toggle.syncState()


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, MessageFragment())
            .commit()

        drawer.navView.setCheckedItem(R.id.nav_message)

        drawer.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                    R.id.nav_message -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, MessageFragment())
                            .commit()
                    }

                    R.id.nav_chat -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, ChatFragment())
                            .commit()
                    }

                    R.id.nav_profile->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, ProfileFragment())
                            .commit()
                    }

                    R.id.nav_send ->{
                        Toast.makeText(this@MainActivity,"Send clicked", Toast.LENGTH_SHORT).show()
                    }

                    R.id.nav_share ->{
                        Toast.makeText(this@MainActivity,"Share clicked", Toast.LENGTH_SHORT).show()
                    }
                }
                drawer.root.closeDrawer(GravityCompat.START)
                true
            }
        }


    }
