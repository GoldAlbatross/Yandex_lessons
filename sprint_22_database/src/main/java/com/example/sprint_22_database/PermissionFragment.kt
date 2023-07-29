package com.example.sprint_22_database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.Manifest
import android.content.pm.PackageManager
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.sprint_22_database.App.Companion.requester
import com.example.sprint_22_database.databinding.FragmentPermissionBinding
import com.markodevcic.peko.PermissionResult
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.coroutines.launch

class PermissionFragment : Fragment() {

    private val binding by lazy { FragmentPermissionBinding.inflate(layoutInflater) }
    private val rxPermissions = RxPermissions(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.permissionRequestFrame.setOnClickListener {
            lifecycleScope.launch {
                requester.request(Manifest.permission.CAMERA)
                    .collect { result ->
                        when (result) {
                            is PermissionResult.Denied.NeedsRationale -> {/* Необходимо показать разрешение*/ }
                            is PermissionResult.Denied.DeniedPermanently -> {/* Запрещено навсегда, перезапрашивать нет смысла, предлагаем пройти в настройки*/ }
                            is PermissionResult.Granted -> {/* Пользователь дал разрешение, можно продолжать работу */ }
                            is PermissionResult.Denied -> {/* Пользователь отказал в предоставлении разрешения*/ }
                            is PermissionResult.Cancelled -> {/* Запрос на разрешение отменён*/ }
                        }
                    }
            }
        }


        // Fo RxJavaPermissions
//        binding.permissionRequestFrame.setOnClickListener {
//            rxPermissions
//                .request(Manifest.permission.CAMERA)
//                .subscribe { granted: Boolean ->
//                    if (granted) {
//                        // Пользователь дал разрешение, можно продолжать работу
//                        binding.permissionRequestFrame.visibility = View.GONE
//                        binding.permissionGranted.visibility = View.VISIBLE
//                    } else {
//                        // Пользователь отказал в предоставлении разрешения
//                        binding.permissionRequestFrame.visibility = View.VISIBLE
//                        binding.permissionGranted.visibility = View.GONE
//                    }
//                }
//        }

        checkPermission()

        binding.permissionRequestFrame.setOnClickListener {
            // one permission
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                Toast.makeText(requireContext(), "Разрешение на использование геолокации необходимо для доступа к Bluetooth-устройствам", Toast.LENGTH_LONG).show()
            }

            // many permission
//            requestMultiPermissionLauncher.launch(
//                arrayOf(
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                )
//            )
        }

    }

    private fun checkPermission() {
        val permissionProvided = ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.CAMERA
        )
        if (permissionProvided == PackageManager.PERMISSION_GRANTED) {
            binding.permissionRequestFrame.visibility = View.GONE
            binding.permissionGranted.visibility = View.VISIBLE
        } else if (permissionProvided == PackageManager.PERMISSION_DENIED) {
            binding.permissionRequestFrame.visibility = View.VISIBLE
            binding.permissionGranted.visibility = View.GONE
        }
    }

    // one permission
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Пользователь дал разрешение, можно продолжать работу
                binding.permissionRequestFrame.visibility = View.GONE
                binding.permissionGranted.visibility = View.VISIBLE
            } else {
                // Пользователь отказал в предоставлении разрешения
                binding.permissionRequestFrame.visibility = View.VISIBLE
                binding.permissionGranted.visibility = View.GONE
            }
        }

    // many permission
    private val requestMultiPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultMap ->
        val cameraPermissionGranted = resultMap[Manifest.permission.CAMERA]
        if (cameraPermissionGranted != null && cameraPermissionGranted) {
            // Пользователь дал разрешение, можно продолжать работу
            binding.permissionRequestFrame.visibility = View.GONE
            binding.permissionGranted.visibility = View.VISIBLE
        } else {
            // Пользователь отказал в предоставлении разрешения
            binding.permissionRequestFrame.visibility = View.VISIBLE
            binding.permissionGranted.visibility = View.GONE
        }
    }


}