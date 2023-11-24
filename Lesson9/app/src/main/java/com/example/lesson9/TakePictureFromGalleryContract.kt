package com.tviztv.tviz2x45.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import com.tviztv.tviz2x45.R
import timber.log.Timber

/** Для выбора изображения из стороннего приложения **/
class TakePictureFromGalleryContract : ActivityResultContract<Unit, Uri?>() {
    companion object {
        private const val CONTENT_TYPE = "image/*"
    }

    override fun createIntent(context: Context, input: Unit): Intent {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = CONTENT_TYPE
        }

        return Intent.createChooser(intent, context.getString(R.string.edit_profile_choice_app))
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        Timber.tag("AttachImage").d("parseResult")
        return (resultCode == Activity.RESULT_OK).let {
            intent?.data
        }
    }
}
