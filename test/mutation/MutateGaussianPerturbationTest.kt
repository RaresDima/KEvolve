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

internal class MutateGaussianPerturbationTest {

    companion object {

        class MyIndividual(val dna: MutableList<Number>)

        @JvmStatic
        fun dnaValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(MyIndividual(MutableList(1) { Random.nextDouble() })),
            Arguments.of(MyIndividual(MutableList(10) { Random.nextFloat() })),
            Arguments.of(MyIndividual(MutableList(100) { Random.nextLong() })),
            Arguments.of(MyIndividual(MutableList(1000) { Random.nextInt() }))
        )

    }


    @Test
    fun `genePb is 0,0`() {
        assertThrows<InvalidProbabilityException> {
            MutateGaussianPerturbation(genePb = 0.0, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `genePb is 1,0`() {
        MutateGaussianPerturbation(genePb = 1.0, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
    }

    @Test
    fun `genePb is 1,1`() {
        assertThrows<InvalidProbabilityException> {
            MutateGaussianPerturbation(genePb = 1.1, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `std dev is -0,1`() {
        assertThrows<InvalidStDevException> {
            MutateGaussianPerturbation(genePb = 0.5, mean = 0.0, std = -0.1) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateGaussianPerturbation(genePb = 0.5, mean = 0.0, std = 1.0) { ind: MyIndividual -> ind.dna }
        val ind = MyIndividual(mutableListOf())
        mutate(ind)
    }

    @ParameterizedTest
    @MethodSource("dnaValueProvider")
    fun `genes mutated successfully`(ind: MyIndividual) {
        val mutate = MutateGaussianPerturbation(genePb = 0.5, mean = 0.0, std = 1.0) { ind_: MyIndividual -> ind_.dna }
        mutate(ind)
    }

}
