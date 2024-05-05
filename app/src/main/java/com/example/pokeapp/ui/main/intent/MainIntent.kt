package com.example.pokeapp.ui.main.intent

sealed interface MainIntent {

    data class OnLoadListIntent(
        val hasNext: Boolean,
        val hasPrevious: Boolean
    ):MainIntent

    data class onNextClicked(val next: String):MainIntent
    data class onPreviousClicked(val previous: String):MainIntent
}