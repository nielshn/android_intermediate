package com.dicoding.ticketingapps

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.ticketingapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val seatView = binding.seatsView
        val button = binding.finishButton
        button.setOnClickListener {
            seatView.seat?.let {
                Toast.makeText(this, "Kursi Anda nomor ${it.name}", Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(this, "Silahkan pilih kursi terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}