package com.l0122147.salsarizki.explisitintentsenddata

import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.Button // Add this import statement
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnProfile: Button = findViewById(R.id.btn_profile)
        btnProfile.setOnClickListener(this) // jika error, alt+enter dan pilih yang "Let .."
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_profile -> {
                val profileIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                profileIntent.putExtra(ProfileActivity.EXTRA_NIM, "L0122147")
                profileIntent.putExtra(ProfileActivity.EXTRA_NAME, "Salsa Rizki Saputri")
                profileIntent.putExtra(ProfileActivity.EXTRA_BATCH, 2022)
                profileIntent.putExtra(ProfileActivity.EXTRA_GRADUATED, false)
                startActivity(profileIntent)
            }
        }
    }
}
