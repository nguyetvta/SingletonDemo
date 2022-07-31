package com.example.singletonshareprefs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.singletonshareprefs.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntroBinding.inflate(layoutInflater)

        val sharePrefs = SharePrefsSingleton.getInstance(applicationContext)
        val isInstalled = sharePrefs.isInstalled

        if (isInstalled){
            goToMainActivity()
        }
        else {
            binding.introNextBtn.setOnClickListener {
                sharePrefs.putInstallState(true)
                goToMainActivity()
            }
        }

        setContentView(binding.root)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}