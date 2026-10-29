package com.example.appletv.model

data class AppItem(
    val label: String,
    val packageName: String,
    val iconResId: Int
) {
    fun getPackageNameString() = packageName
}