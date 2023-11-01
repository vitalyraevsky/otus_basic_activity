package ru.otus.otusdemo

import android.app.ActivityManager
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var button: Button

    private val activityManager by lazy {
        getSystemService(ACTIVITY_SERVICE) as ActivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printInfoAboutCalledMethod("onCreate")

        val id = (applicationContext as App).id
        Log.d(TAG, "id = $id")
        //finish()
        //finishAffinity()
        button = findViewById(R.id.mainActivityButton)
        printTaskInfo()
    }

    override fun onStart() {
        super.onStart()
        printInfoAboutCalledMethod("onStart")
        button.setOnClickListener {
            Log.d(TAG, "button clicked")
            val intent = Intent(this, SecondActivity::class.java).apply {

                //flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        printInfoAboutCalledMethod("onResume")
    }

    override fun onPause() {
        printInfoAboutCalledMethod("onPause")
        super.onPause()
    }

    override fun onStop() {
        printInfoAboutCalledMethod("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        printInfoAboutCalledMethod("onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        printInfoAboutCalledMethod("onRestart")
    }

    override fun onBackPressed() {
        printInfoAboutCalledMethod("onBackPressed")
        super.onBackPressed()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        printInfoAboutCalledMethod("onNewIntent")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        printInfoAboutCalledMethod("onConfigurationChanged")
    }

    private fun printInfoAboutCalledMethod(methodName: String) {
        Log.i(TAG, "\n")
        Log.i(TAG, "${methodName} was called for ${localClassName}")
        printTaskInfo()
    }

    private fun printTaskInfo() {
        Log.i(TAG, "\n")
        for (task in activityManager.appTasks) {
            Log.i(TAG, "Task info:")
            Log.i(TAG, "\tCount: ${task.taskInfo.numActivities}")
            Log.i(TAG, "\tTop: ${task.taskInfo.topActivity?.shortClassName}")
            Log.i(TAG, "\tRoot: ${task.taskInfo.baseActivity?.shortClassName}")
        }
    }
}