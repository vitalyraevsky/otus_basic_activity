package ru.otus.otusdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import java.io.Serializable

private const val TAG = "MainActivity"

class MainActivity : BaseActivity(R.layout.activity_main) {

    lateinit var button: Button
    lateinit var buttonS: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button = findViewById(R.id.mainActivityButton)
        buttonS = findViewById(R.id.mainActivityButtonS)
    }

    override fun onStart() {
        super.onStart()

        buttonS.setOnClickListener {
/*
            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:8906777")
            )
            startActivity(intent)
*/
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("KEY", "string")
                putExtra("KEY2", "st")
/*
                putExtra("data",
                    PageData("title","body") as Parcelable
                )
*/
                // ТАК НЕ НАДО
                putExtra("data",
                    PageData("title","body") as Parcelable
                )
                putExtra("dataSerializable",
                    PageData("title","body") as Serializable
                )

                val bundleCat = Bundle().apply {
                    putInt("id", 1)
                    putString("name", "Category 1")
                }
                val bundle = Bundle().apply {
                    putString("name", "John")
                    putInt("age", 33)
                    putBundle("category", bundleCat)
                }
                putExtra("Person", bundle)
            }
            startActivity(intent)
        }

        button.setOnClickListener {
            Log.d(TAG, "button clicked")

            // WEB
/*
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://otus.ru")
            )
*/
            // Dial
/*
            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:8906777")
            )
*/
            // Mail
            val intent = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:android@otus.ru")
            )

            // MAP
/*
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=bar")
            ).setPackage("com.google.android.apps.maps")
*/
            // Activity
            //val intent = Intent("OpenSecondActivity")

            startActivity(intent)
        }
    }
}