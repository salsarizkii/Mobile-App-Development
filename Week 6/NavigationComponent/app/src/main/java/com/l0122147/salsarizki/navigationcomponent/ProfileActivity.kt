package com.l0122147.salsarizki.navigationcomponent

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.l0122147.salsarizki.navigationcomponent.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        //   ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //      insets
        //   }
    }
}
