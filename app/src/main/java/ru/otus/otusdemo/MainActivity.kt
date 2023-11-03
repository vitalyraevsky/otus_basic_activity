package ru.otus.otusdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button

private const val TAG = "MainActivity"

class MainActivity : BaseActivity(R.layout.activity_main) {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = (applicationContext as App).id
        Log.d(TAG, "id = $id")
        //finish()
        //finishAffinity()
        button = findViewById(R.id.mainActivityButton)
    }

    override fun onStart() {
        super.onStart()
        button.setOnClickListener {
            Log.d(TAG, "button clicked")
            val intent = Intent(this, SecondActivity::class.java).apply {
                //flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }
}