package utils

import exceptions.utils.InvalidDomainBoundsException
import exceptions.utils.InvalidPrecisionException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BinaryRepresentationTest {

    @Test
    fun `min lt max`() { BinaryRepresentation(1.0 , 2.0, 1) }

    @Test
    fun `min eq max`() { assertThrows<InvalidDomainBoundsException> { BinaryRepresentation(1.0 , 1.0, 1) } }

    @Test
    fun `min gt max`() { assertThrows<InvalidDomainBoundsException> { BinaryRepresentation(2.0 , 1.0, 1) } }


    @Test
    fun `digits gt 0`() { BinaryRepresentation(1.0 , 2.0, 1) }

    @Test
    fun `digits eq 0`() { BinaryRepresentation(1.0 , 2.0, 0) }

    @Test
    fun `digits lt 0`() { assertThrows<InvalidPrecisionException> { BinaryRepresentation(1.0 , 2.0, -1) } }

}
