package ru.otus.otusdemo

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

class ContractSecondActivity : ActivityResultContract<String, String?>() {

    override fun createIntent(context: Context, input: String): Intent {
        return  Intent(context, SecondActivity::class.java).apply {
            putExtra(MainActivity.NAME_KEY, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (intent == null) return null
        if (resultCode != AppCompatActivity.RESULT_OK) return null
        return intent.extras?.getString(MainActivity.RESULT_KEY)
    }
}