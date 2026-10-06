package com.example.opendevoptions

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val candidates = listOf(
            "com.android.settings.development.DevelopmentSettingsDashboardFragment",
            "com.android.settings.development.DevelopmentSettings",
            "com.android.settings.DevelopmentSettings"
        )

        val settingsIntent = Intent().apply {
            component = ComponentName("com.android.settings", "com.android.settings.SubSettings")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        var launched = false

        for (fragment in candidates) {
            try {
                val intent = Intent(settingsIntent).apply {
                    putExtra(":settings:show_fragment", fragment)
                }
                startActivity(intent)
                launched = true
                break
            } catch (e: Exception) {
                // next fragment
            }
        }

        if (!launched) {
            try {
                startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
            } catch (e: Exception) {
                Toast.makeText(this, "无法打开设置", Toast.LENGTH_SHORT).show()
            }
        }

        finish()
    }
}
