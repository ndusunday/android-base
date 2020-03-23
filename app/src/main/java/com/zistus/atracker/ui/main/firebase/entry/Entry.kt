package com.zistus.atracker.ui.main.firebase.entry

import com.google.firebase.database.IgnoreExtraProperties

sealed class Entry {
    @IgnoreExtraProperties
    data class Item (
        var title: String? = "",
        var itemId: String? = "",
        val status: String? = "",
        val startLocation: String? = "",
        val destination: String? = "",
        val currentLocation: String? = ""
    ): Entry()

    @IgnoreExtraProperties
    data class Location (
        val longitude: String,
        val latitude: String,
        val address: String
    ): Entry()

    @IgnoreExtraProperties
    data class User (
        var username: String? = "",
        var email: String? = "",
        val phoneNumber: String? = "",
        val type: UserType? = UserType.CUSTOMER,
        val items: List<Item>
    ): Entry()

}