package com.example.valeeeu.logic.formatters

import android.content.Context
import com.example.valeeeu.R

fun formatRatingText(rating: Float): String {
    return "%.1f".format(rating).replace(".", ",")
}

fun formatDistanceText(distance: Float): String {
    return when (distance) {
        in 1000f..Float.MAX_VALUE -> "%.1f".format((distance / 1000)).replace(".", ",") + " km"
        else -> "%.0f".format(distance).replace(".", ",") + " m"
    }
}

fun formatPriceText(price: Float, context: Context, summarized: Boolean = false): String {
    if (price <= 0) return context.getString(R.string.budget)

    val formattedPrice = "%.2f".format(price).replace(".", ",")

    if (summarized) {
        return "R$ $formattedPrice+"
    }

    val fromText = context.getString(R.string.from_price)

    return "$fromText R$ $formattedPrice"
}