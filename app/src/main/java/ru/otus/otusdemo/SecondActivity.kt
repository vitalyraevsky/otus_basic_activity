package ru.otus.otusdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import ru.otus.otusdemo.MainActivity.Companion.NAME_KEY
import ru.otus.otusdemo.MainActivity.Companion.RESULT_KEY

private const val TAG = "SecondActivity"

class SecondActivity : BaseActivity(R.layout.activity_second) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.extras?.getString(NAME_KEY)

        findViewById<TextView>(R.id.secondActivityTextViewName).text = name
        findViewById<Button>(R.id.secondActivityApplyButton).setOnClickListener {
            val text = findViewById<EditText>(R.id.secondActivityInput).text.toString()
            intent = Intent().putExtra(RESULT_KEY, text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
}