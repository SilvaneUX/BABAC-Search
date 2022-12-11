package com.smart.smartbalibackpaker.dashboard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class SearchPlace(
    val titlequerry : String ="",
    val alamatquerry : String ="",
    val photoquerry : String ="",
    ) : Parcelable
