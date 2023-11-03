package ru.otus.otusdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button

private const val TAG = "SecondActivity"

class SecondActivity : BaseActivity(R.layout.activity_second) {

    private val button by lazy {
        findViewById<Button>(R.id.secondActivityButtonC)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Task Id: ${taskId}")

        findViewById<Button?>(R.id.secondActivityButtonA).setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            finishAffinity()
        }
    }
}