package com.faviansa.submissionandroidpemula

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class About(
    val name: String,
    val email: String,
    val photo: Int
) : Parcelable
