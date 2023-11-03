package ru.otus.otusdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button

private const val TAG = "ThirdActivity"

class ThirdActivity : BaseActivity(R.layout.activity_thrird) {

    private val button by lazy {
        findViewById<Button>(R.id.thirdActivityButton)
    }

    private val buttonC by lazy {
        findViewById<Button>(R.id.thirdActivityButtonC)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Task Id: ${taskId}")

        button.setOnClickListener {
            finishAffinity()
        }

        buttonC.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }
}