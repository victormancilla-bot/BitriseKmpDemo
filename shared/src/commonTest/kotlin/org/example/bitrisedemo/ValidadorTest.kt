package org.example.bitrisedemo

import kotlin.test.Test
import kotlin.test.assertEquals

class ValidadorTest {
    @Test
    fun verificarDescuentoCorrecto() {
        val resultado = ValidadorCiCd.calcularDescuento(100.0, 15.0)
        assertEquals(85.0, resultado, "El descuento del 15% sobre 100 debe ser 85.0")
    }
}