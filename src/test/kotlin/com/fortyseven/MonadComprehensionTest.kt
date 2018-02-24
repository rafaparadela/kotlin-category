package com.fortyseven

import arrow.core.Option
import arrow.core.OptionOf
import arrow.core.Some
import arrow.core.monad
import arrow.typeclasses.binding
import org.junit.Assert.assertEquals
import org.junit.Test

class MonadComprehensionTest {

    @Test
    fun testMonadComprehension() {

        val pdf1: Option<PDF> = UserRepository.getUser("123").flatMap { user ->
            Spotify.getFavouriteArtist(user.spotifyUsername).flatMap { artist ->
                Wikipedia.getArticle(artist.name).flatMap { page ->
                    Converter.toPDF(page)
                }
            }
        }

        val pdf2: OptionOf<PDF> = Option.monad().binding {
            val user = UserRepository.getUser("123").bind()
            val artist = Spotify.getFavouriteArtist(user.spotifyUsername).bind()
            val page = Wikipedia.getArticle(artist.name).bind()
            Converter.toPDF(page).bind()
        }

        assertEquals("PDF1", Some(PDF("blabla")), pdf1)
        assertEquals("PDF1", Some(PDF("blabla")), pdf2)
    }
}