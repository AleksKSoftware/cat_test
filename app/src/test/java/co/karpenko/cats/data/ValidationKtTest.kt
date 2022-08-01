package co.karpenko.cats.data

import co.karpenko.cats.domain.isEmailValid
import co.karpenko.cats.domain.isUserPasswordValid
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */
internal class ValidationKtTest {

    @Test
    fun `invalid email`() {
        assertEquals(false,"".isEmailValid)
    }

    @Test
    fun `valid email`() {
        assertEquals(true,"java.karpenko@gmail.com".isEmailValid)
    }

    @Test
    fun `invalid password less 6 characters`() {
        assertEquals(false,"java".isUserPasswordValid)
    }

    @Test
    fun `password valid`() {
        assertEquals(true,"java11111111".isUserPasswordValid)
    }

}