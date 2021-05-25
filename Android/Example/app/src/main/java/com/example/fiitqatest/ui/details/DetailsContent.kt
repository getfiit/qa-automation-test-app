package com.example.fiitqatest.ui.details

sealed class DetailsContent {
    data class Hero(
        val name: String,
        val imageUrl: String
    ) : DetailsContent()

    data class Heading(
        val titleRes: Int
    ) : DetailsContent()

    data class Description(
        val description: String
    ) : DetailsContent()

    data class Attributes(
        val attributes: List<String>
    ) : DetailsContent()
}
