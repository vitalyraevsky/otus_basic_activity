package ru.otus.otusdemo

import android.os.Bundle
import android.widget.Button

private const val TAG = "ThirdActivity"

class ThirdActivity : BaseActivity(R.layout.activity_thrird) {

    private val button by lazy {
        findViewById<Button>(R.id.thirdActivityButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button.setOnClickListener {
            finishAffinity()
        }
    }
}