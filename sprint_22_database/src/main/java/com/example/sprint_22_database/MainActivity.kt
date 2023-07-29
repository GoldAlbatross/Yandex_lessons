package com.example.sprint_22_database

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat

const val CAMERA_REQUEST_CODE = 1
class MainActivity : AppCompatActivity() {

//    private val permissionRequestFrame: View by lazy { findViewById(R.id.permission_request_frame) }
//    private val permissionGranted: View by lazy { findViewById(R.id.permission_granted) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создаем экземпляр фрагмента
        val fragment = PermissionFragment()

        // Заменяем фрагмент в контейнере с id "containerView"
        supportFragmentManager.beginTransaction()
            .add(R.id.containerView, fragment)
            .commit()


//        val permissionProvided = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//        if (permissionProvided == PackageManager.PERMISSION_GRANTED) {
//            // Всё хорошо, разрешение предоставлено!
//            permissionRequestFrame.visibility = View.GONE
//            permissionGranted.visibility = View.VISIBLE
//        } else if (permissionProvided == PackageManager.PERMISSION_DENIED) {
//            // Запрашиваем разрешение
//            permissionRequestFrame.visibility = View.VISIBLE
//            permissionGranted.visibility = View.GONE
//        }
//
//        permissionRequestFrame.setOnClickListener {
//            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
//        }
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode != CAMERA_REQUEST_CODE) {
//            Log.e("PermissionActivity", "Пришел результат не с тем requestCode, который ожидался")
//            return
//        }
//        val cameraPermissionIndex = permissions.indexOf(Manifest.permission.CAMERA)
//        val permissionProvided = grantResults[cameraPermissionIndex]
//        if (permissionProvided == PackageManager.PERMISSION_GRANTED) {
//            // Пользователь дал разрешение, можно продолжать работу
//            permissionRequestFrame.visibility = View.GONE
//            permissionGranted.visibility = View.VISIBLE
//        } else if (permissionProvided == PackageManager.PERMISSION_DENIED) {
//            // Пользователь отказал в предоставлении разрешения
//            permissionRequestFrame.visibility = View.VISIBLE
//            permissionGranted.visibility = View.GONE
//
//            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                data = Uri.fromParts("package", this@MainActivity.packageName, null)
//                startActivity(this)
//            }
//        }
//    }
}