package com.faviansa.submissionandroidpemula

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Musician(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
