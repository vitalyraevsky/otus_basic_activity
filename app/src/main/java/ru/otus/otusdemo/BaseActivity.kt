package ru.otus.otusdemo

import android.app.ActivityManager
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "Activity"

open class BaseActivity(
    layout: Int
) : AppCompatActivity(layout) {

    private val activityManager by lazy {
        getSystemService(ACTIVITY_SERVICE) as ActivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printInfoAboutCalledMethod("onCreate")
    }

    override fun onStart() {
        super.onStart()
        printInfoAboutCalledMethod("onStart")
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
        printInfoAboutCalledMethod("onRestart")
        super.onRestart()
    }

    override fun onBackPressed() {
        printInfoAboutCalledMethod("onBackPressed")
        super.onBackPressed()
    }

    override fun onNewIntent(intent: Intent?) {
        printInfoAboutCalledMethod("onNewIntent")
        super.onNewIntent(intent)
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
        val tasks = activityManager.appTasks
        Log.i(TAG, "\tTasks count: ${tasks.size}")
        for (task in tasks) {
            Log.i(TAG, "Task info:")
            //Log.i(TAG, "\tCount: ${task.taskInfo.taskId}")
            Log.i(TAG, "\tCount: ${task.taskInfo.numActivities}")
            Log.i(TAG, "\tTop: ${task.taskInfo.topActivity?.shortClassName}")
            Log.i(TAG, "\tRoot: ${task.taskInfo.baseActivity?.shortClassName}")
        }
    }

}