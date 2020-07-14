package com.clean.app.data.entity


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rohit.anvekar on 14/7/20.
 */
data class Row(
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("imageHref")
    @Expose
    val imageHref: String
)