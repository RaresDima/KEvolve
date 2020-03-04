package mutation

import exceptions.mutation.InvalidProbabilityException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.Bit
import java.util.stream.Stream

internal class MutateFlipBitsTest {

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
    fun `bitPb is 0,0`() {
        assertThrows<InvalidProbabilityException> {
            MutateFlipBits(bitPb = 0.0) { ind: MyIndividual -> ind.bits }
        }
    }

    @Test
    fun `bitPb is 1,0`() {
        MutateFlipBits(bitPb = 1.0) { ind: MyIndividual -> ind.bits }
    }

    @Test
    fun `bitPb is 1,1`() {
        assertThrows<InvalidProbabilityException> {
            MutateFlipBits(bitPb = 1.1) { ind: MyIndividual -> ind.bits }
        }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateFlipBits(bitPb = 0.1) { ind: MyIndividual -> ind.bits }
        val ind = MyIndividual(mutableListOf())
        mutate(ind)
    }

    @ParameterizedTest
    @MethodSource("bitStringValueProvider")
    fun `bits mutated successfully`(ind: MyIndividual) {
        val mutate = MutateFlipBits(bitPb = 1.0 / (ind.bits.size * 2.0)) { ind_: MyIndividual -> ind_.bits }
        mutate(ind)
    }

}
