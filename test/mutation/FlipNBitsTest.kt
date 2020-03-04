package mutation

import exceptions.mutation.InvalidNBitsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.Bit
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class FlipNBitsTest {

    companion object {

        @JvmStatic
        fun bitStringValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(MutableList(1) { Bit.random() }),
            Arguments.of(MutableList(10) { Bit.random() }),
            Arguments.of(MutableList(100) { Bit.random() }),
            Arguments.of(MutableList(1000) { Bit.random() })
        )

    }


    @Test
    fun `nBits is 0`() {
        assertThrows<InvalidNBitsException> {
            MutateFlipNBits(nBits = 0) { dna: MutableList<Bit> -> dna }
        }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateFlipNBits(nBits = 3) { dna: MutableList<Bit> -> dna }
        val ind = mutableListOf<Bit>()
        assertThrows<InvalidNBitsException> { mutate(ind) }
    }


//    @ParameterizedTest
//    @MethodSource("fitnessValueProvider")
//    fun `value initialized`(v: Double) = assertEquals(Fitness().apply { value = v }.value, v)

}
