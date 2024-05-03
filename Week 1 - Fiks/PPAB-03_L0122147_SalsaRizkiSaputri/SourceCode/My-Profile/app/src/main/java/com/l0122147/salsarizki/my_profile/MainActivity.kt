package com.l0122147.salsarizki.my_profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detail_button: Button = findViewById(R.id.detail_button)
        detail_button.setOnClickListener(this)

        val instagramButton: ImageButton = findViewById(R.id.instagramIcon)
        val githubButton: ImageButton = findViewById(R.id.githubIcon)
        val whatsappButton: ImageButton = findViewById(R.id.whatsappIcon)

        instagramButton.setOnClickListener(this)
        githubButton.setOnClickListener(this)
        whatsappButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.detail_button -> {
                val detail_Activity = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(detail_Activity)
            }
            R.id.instagramIcon -> {
                val uri = Uri.parse("http://instagram.com/_u/salsaa.rs")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.instagram.android")
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // Instagram app is not installed, handle appropriately
                }
            }
            R.id.githubIcon -> {
                val uri = Uri.parse("https://github.com/salsarizkii")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // GitHub app or browser is not installed, handle appropriately
                }
            }
            R.id.whatsappIcon -> {
                val uri = Uri.parse("https://wa.me/6285743863245")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // WhatsApp app or browser is not installed, handle appropriately
                }
            }
        }
    }
}