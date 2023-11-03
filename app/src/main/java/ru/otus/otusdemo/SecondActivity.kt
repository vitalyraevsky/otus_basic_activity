package ru.otus.otusdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView

private const val TAG = "SecondActivity"

class SecondActivity : BaseActivity(R.layout.activity_second) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val key = intent.extras?.getString("KEY")
        val key2 = intent.extras?.getInt("KEY2")

        // Parcelable
        val data = intent.getParcelableExtra<PageData>("data")

        // Serializable
        val dataSerializable = intent.getSerializableExtra("dataSerializable") as PageData

        val person = intent.extras?.getBundle("Person")
        Log.d(TAG, "extra KEY: $key")
        Log.d(TAG, "extra KEY: $key2")
        Log.d(TAG, "extra data: $data")
        Log.d(TAG, "extra person: $person")

        data?.let {
            findViewById<TextView>(R.id.secondActivityTextView).text = it.title
            findViewById<TextView>(R.id.secondActivityTextView1).text = it.pageBody
        }

    }
}