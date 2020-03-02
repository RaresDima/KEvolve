package utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class BinaryRepresentationTest {

    @Test
    fun `min lt max`() { BinaryRepresentation(1.0 , 2.0, 1) }

    @Test
    fun `min eq max`() { assertThrows<IllegalStateException> { BinaryRepresentation(1.0 , 1.0, 1) } }

    @Test
    fun `min gt max`() { assertThrows<IllegalStateException> { BinaryRepresentation(2.0 , 1.0, 1) } }


    @Test
    fun `digits gt 0`() { BinaryRepresentation(1.0 , 2.0, 1) }

    @Test
    fun `digits eq 0`() { BinaryRepresentation(1.0 , 2.0, 0) }

    @Test
    fun `digits lt 0`() { assertThrows<IllegalStateException> { BinaryRepresentation(1.0 , 2.0, -1) } }

}
