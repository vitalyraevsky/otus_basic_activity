package ru.otus.otusdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.Serializable

private const val TAG = "MainActivity"
private const val CODE = 100

class MainActivity : BaseActivity(R.layout.activity_main) {

    // Новый способ получать данные из результата
    private val resultContract = registerForActivityResult(ContractSecondActivity()) { result ->
        textResult.text = result
        //method(result)
    }

    private fun method(result: String?): String? {
        TODO("Not yet implemented")
    }

    lateinit var button: Button
    lateinit var buttonS: Button
    lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button = findViewById(R.id.mainActivityButton)
        buttonS = findViewById(R.id.mainActivityButtonS)
        textResult = findViewById(R.id.mainActivityTextView)
    }

    override fun onStart() {
        super.onStart()

        // Новый способ отправлять данные для результата
        buttonS.setOnClickListener {
            resultContract.launch("test")
        }

        // Старый способ отправки данных для результата
        button.setOnClickListener {
            Log.d(TAG, "button clicked")
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(NAME_KEY, "test")
            }
            startActivityForResult(intent, CODE)
        }
    }

    // Старый способ получения результатов
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult")
        when(requestCode) {
            CODE -> {
                if (resultCode == RESULT_OK && data != null) {
                    textResult.text =  data.extras?.getString(RESULT_KEY)
                } else if (resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "Отмена действий" ,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        val NAME_KEY = "name"
        val RESULT_KEY = "result"
    }
}