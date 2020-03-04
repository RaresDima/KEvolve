package mutation

import exceptions.mutation.InvalidProbabilityException
import exceptions.mutation.InvalidStDevException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.Bit
import java.util.stream.Stream
import kotlin.random.Random

internal class MutateGaussianReplaceTest {

    companion object {

        class MyIndividual(val dna: MutableList<Number>)

        @JvmStatic
        fun bitStringValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(MyIndividual(MutableList(1) { Random.nextDouble() })),
            Arguments.of(MyIndividual(MutableList(10) { Random.nextFloat() })),
            Arguments.of(MyIndividual(MutableList(100) { Random.nextLong() })),
            Arguments.of(MyIndividual(MutableList(1000) { Random.nextInt() }))
        )

    }


    @Test
    fun `bitPb is 0,0`() {
        assertThrows<InvalidProbabilityException> {
            MutateGaussianReplace(genePb = 0.0, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `bitPb is 1,0`() {
        MutateGaussianReplace(genePb = 1.0, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
    }

    @Test
    fun `bitPb is 1,1`() {
        assertThrows<InvalidProbabilityException> {
            MutateGaussianReplace(genePb = 1.1, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `std dev is 0,0`() {
        assertThrows<InvalidStDevException> {
            MutateGaussianReplace(genePb = 0.5, mean = 0.0, std = 0.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateGaussianReplace(genePb = 0.5, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
        val ind = MyIndividual(mutableListOf())
        mutate(ind)
    }

    @ParameterizedTest
    @MethodSource("bitStringValueProvider")
    fun `correct number of bits mutated`(ind: MyIndividual) {
        val mutate = MutateGaussianReplace(genePb = 0.5, mean = 0.0, std = 1.0) { ind_: MyIndividual -> ind_.dna }
        mutate(ind)
    }

}
