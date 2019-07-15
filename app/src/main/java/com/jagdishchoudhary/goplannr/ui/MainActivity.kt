package com.jagdishchoudhary.goplannr.ui

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.navigation.NavigationView
import com.jagdishchoudhary.goplannr.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener, PlanFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {

    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
