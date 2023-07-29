package hu.jozsefbarat.dogsshowcase.ext

import org.junit.Test

class StringExtKtTest{

    @Test
    fun `when empty value received then return it`() {
        val result = "".capitalize()

        assert(result == "")
    }

    @Test
    fun `when non-empty value received then return it capitalized`() {
        val result = "corgi".capitalize()

        assert(result == "Corgi")
    }
}