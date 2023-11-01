package ru.otus.otusdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "SecondActivity"

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private val button by lazy {
        findViewById<Button>(R.id.secondActivityButtonC)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "call onCreate")

        findViewById<Button?>(R.id.secondActivityButtonA).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            finishAffinity()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "call onResume")
    }

    override fun onPause() {
        Log.d(TAG, "call onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "call onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "call onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "call onRestart")
    }

    override fun onBackPressed() {
        Log.d(TAG, "call onBackPressed")
        super.onBackPressed()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "call onNewIntent")
    }
}