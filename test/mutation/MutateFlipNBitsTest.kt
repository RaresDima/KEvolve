package mutation

import exceptions.mutation.InvalidNBitsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.Bit
import utils.extensions.hammingDistance
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class MutateFlipNBitsTest {

    companion object {

        class MyIndividual(val bits: MutableList<Bit>)

        @JvmStatic
        fun bitStringValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(MyIndividual(MutableList(1) { Bit.random() })),
            Arguments.of(MyIndividual(MutableList(10) { Bit.random() })),
            Arguments.of(MyIndividual(MutableList(100) { Bit.random() })),
            Arguments.of(MyIndividual(MutableList(1000) { Bit.random() }))
        )

    }


    @Test
    fun `nBits is 0`() {
        assertThrows<InvalidNBitsException> {
            MutateFlipNBits(nBits = 0) { ind: MyIndividual -> ind.bits }
        }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateFlipNBits(nBits = 3) { ind: MyIndividual -> ind.bits }
        val ind = MyIndividual(mutableListOf())
        assertThrows<InvalidNBitsException> { mutate(ind) }
    }

    @ParameterizedTest
    @MethodSource("bitStringValueProvider")
    fun `correct number of bits mutated`(ind: MyIndividual) {
        val mutate = MutateFlipNBits(nBits = 1 + ind.bits.size.div(10)) { ind_: MyIndividual -> ind_.bits }
        assertEquals(
            mutate(MyIndividual(ind.bits.map { it.copy() }.toMutableList())).bits
                    hammingDistance
                    ind.bits,
            1 + ind.bits.size.div(10)
        )
    }

}
