package ru.otus.otusdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import java.io.Serializable

private const val TAG = "MainActivity"
private const val CODE = 100

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val takePictureContract = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        imageView.setImageBitmap(bitmap)
    }

    private val permissionsContract = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val isCameraGranted = permissions[Manifest.permission.CAMERA]
        val isLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION]

    }

    private val permissionCamera = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        when {
            granted -> {
                // Пермишен получен
                takePictureContract.launch(null)
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // пользователь нажам на "не спрашивать больше"
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    val uri =Uri.fromParts("package", packageName, null)
                    data = uri
                }
                startActivity(intent)
            }
            else -> {
                // Пермишен не получен, без "не спрашивать больше"
                Toast.makeText(this, "Ну сорянб не хочешь - не надо", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val getContentContract = registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
        imageView.setImageURI(result)
    }

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
    lateinit var buttonC: Button
    lateinit var imageView: ImageView
    lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button = findViewById(R.id.mainActivityButton)
        buttonS = findViewById(R.id.mainActivityButtonS)
        buttonC = findViewById(R.id.mainActivityButtonC)
        imageView = findViewById(R.id.mainActivityImage)
        textResult = findViewById(R.id.mainActivityTextView)
    }

    override fun onStart() {
        super.onStart()

        buttonC.setOnClickListener {

            //getContentContract.launch("image/*")

            //takePictureContract.launch(null)

            // не обязательно  checkSelfPermission
            val isGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            if(!isGranted) {
                permissionCamera.launch(Manifest.permission.CAMERA)
            }

            //permissionsContract.launch( listOf( Manifest.permission.CAMERA , Manifest.permission.ACCESS_FINE_LOCATION).toTypedArray())
        }

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