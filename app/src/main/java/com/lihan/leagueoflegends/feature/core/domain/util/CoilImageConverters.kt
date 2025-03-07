package com.lihan.leagueoflegends.feature.core.domain.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class CoilImageConverters(
    private val context: Context,
    private val imageLoader: ImageLoader
) {
    suspend fun toByteArray(
        imageUrl: String
    ): ByteArray? {
        return try {
            withContext(Dispatchers.IO) {
                val request = ImageRequest
                    .Builder(context)
                    .data(imageUrl)
                    .allowHardware(false)
                    .build()
                val result = (imageLoader.execute(request) as? SuccessResult)?.drawable as? BitmapDrawable
                val bitmap = result?.bitmap ?: return@withContext null
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.toByteArray()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
