package com.example.appletv

import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.appletv.adapter.AppAdapter
import com.example.appletv.model.AppItem

class HomeActivity : AppCompatActivity() {

    private lateinit var appGrid: RecyclerView
    private lateinit var dockApps: Array<ImageButton>
    private lateinit var clockText: TextView
    private lateinit var dateText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Top bar
        clockText = findViewById(R.id.clock)
        dateText = findViewById(R.id.date)

        // Dock - исправлено на Array<ImageButton>
        dockApps = arrayOf(
            findViewById(R.id.dock_app1),
            findViewById(R.id.dock_app2),
            findViewById(R.id.dock_app3),
            findViewById(R.id.dock_app4)
        )

        // App grid
        appGrid = findViewById(R.id.app_grid)
        appGrid.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        appGrid.isNestedScrollingEnabled = false

        // Setup adapter with sample apps
        val apps = listOf(
            AppItem("YouTube", "com.google.android.youtube.tv", R.drawable.ic_youtube),
            AppItem("VK", "com.vk.tv", R.drawable.ic_vk),
            AppItem("VLC", "org.videolan.vlc", R.drawable.ic_vlc),
            AppItem("Netflix", "com.netflix.ninja", R.drawable.ic_netflix),
            AppItem("Xiaomi Видео", "com.mitv.videoplayer", R.drawable.ic_video),
            AppItem("Amazon", "com.amazon.amazonvideo.livingroom", R.drawable.ic_amazon),
            AppItem("Google Play", "com.android.vending", R.drawable.ic_play_store),
            AppItem("Google TV", "com.google.android.tvlauncher", R.drawable.ic_google_tv)
        )

        val adapter = AppAdapter(this, apps) { app ->
            // Handle app click - could start intent or show details
        }
        appGrid.adapter = adapter

        // Snap to center on focus - заменено на LinearSnapHelper (так как SnapHelper абстрактный)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(appGrid)
    }

    // Handle D-pad navigation
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                // From dock to top bar focused app
                true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                // From top bar to dock
                true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}
