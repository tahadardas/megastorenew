package com.leximegastore.app

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import io.flutter.embedding.android.FlutterFragmentActivity

class MainActivity : FlutterFragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Prevent screenshots and App Switcher preview from capturing app content.
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }
}
