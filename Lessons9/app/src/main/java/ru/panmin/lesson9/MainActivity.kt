package ru.panmin.lesson9

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import ru.panmin.lesson9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                binding.textViewHelloWorld.text = "Granted"
            } else {
                binding.textViewHelloWorld.text = "Denied"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewHelloWorld.setOnClickListener { askNotificationPermission() }
        binding.textViewToken.setOnClickListener { getFCMToken() }
        binding.imageViewGoToMap.setOnClickListener { startActivity(MapActivity.createStartIntent(this)) }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                binding.textViewHelloWorld.text = "Granted"
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
                alertDialogBuilder
                    .setTitle("Пу пу пу")
                    .setMessage("Нам честно нужно это разрешение.")
                    .setNegativeButton("Не, не надо") { dialog, which ->
                        binding.textViewHelloWorld.text = "Denied"
                    }
                    .setPositiveButton("Ну ладно, давай") { dialog, which ->
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                val dialog: AlertDialog = alertDialogBuilder.create()
                dialog.show()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    binding.textViewToken.text = "Fetching FCM registration token failed"
                    return@OnCompleteListener
                }

                val token = task.result

                binding.textViewToken.text = token

            }
        )
    }
}
