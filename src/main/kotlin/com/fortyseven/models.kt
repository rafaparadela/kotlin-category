package com.fortyseven

import arrow.core.Option
import arrow.core.Some

data class Color(val red: Int = 0, val green: Int = 0, val blue: Int = 0){
    companion object {}
}

data class Potato(val color: Color) {
    companion object {}
}

data class PizzaSlide(val name: String) {
    companion object {}
}

data class Beer(val name: String) {
    companion object {}
}

data class User(val id: String, val spotifyUsername: String) {
    companion object {}
}

data class Artist(val name: String) {
    companion object {}
}

data class Page(val content: String) {
    companion object {}
}

data class PDF(val content: String) {
    companion object {}
}

object UserRepository {
    fun getUser(id: String): Option<User> = Some(User(id, "rafaparadela"))
}

object Spotify {
    fun getFavouriteArtist(artist: String): Option<Artist> = Some(Artist("Lenny Kravitz"))
}

object Wikipedia {
    fun getArticle(title: String): Option<Page> = Some(Page("blabla"))
}

object Converter {
    fun toPDF(page: Page): Option<PDF> = Some(PDF(page.content))
}