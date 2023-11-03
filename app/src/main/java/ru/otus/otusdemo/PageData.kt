package ru.otus.otusdemo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PageData(
    val title: String,
    val pageBody: String,
    val drawableResId: Int? = null
) : Parcelable, Serializable