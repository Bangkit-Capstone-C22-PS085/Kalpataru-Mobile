package com.akhmadkhasan68.kalpataru.ui.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}