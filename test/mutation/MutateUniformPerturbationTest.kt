package mutation

import exceptions.mutation.InvalidProbabilityException
import exceptions.utils.InvalidDomainBoundsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.random.Random

internal class MutateUniformPerturbationTest {

    companion object {

        class MyIndividual(val dna: MutableList<Double>)

        @JvmStatic
        fun dnaValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(MyIndividual(MutableList(1) { Random.nextDouble() })),
            Arguments.of(MyIndividual(MutableList(10) { Random.nextDouble() })),
            Arguments.of(MyIndividual(MutableList(100) { Random.nextDouble() })),
            Arguments.of(MyIndividual(MutableList(1000) { Random.nextDouble() }))
        )

    }


    @Test
    fun `genePb is 0,0`() {
        assertThrows<InvalidProbabilityException> {
            MutateUniformPerturbation(genePb = 0.0, min = 0.0, max = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `genePb is 1,0`() {
        MutateUniformPerturbation(genePb = 1.0, min = 0.0, max = 1.0) { ind: MyIndividual -> ind.dna }
    }

    @Test
    fun `genePb is 1,1`() {
        assertThrows<InvalidProbabilityException> {
            MutateUniformPerturbation(genePb = 1.1, min = 0.0, max = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `min gt max`() {
        assertThrows<InvalidDomainBoundsException> {
            MutateUniformPerturbation(genePb = 0.5, min = 2.0, max = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `min eq max`() {
        assertThrows<InvalidDomainBoundsException> {
            MutateUniformPerturbation(genePb = 0.5, min = 1.0, max = 1.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateUniformPerturbation(genePb = 0.5, min = 0.0, max = 1.0) { ind: MyIndividual -> ind.dna }
        val ind = MyIndividual(mutableListOf())
        mutate(ind)
    }

    @ParameterizedTest
    @MethodSource("dnaValueProvider")
    fun `genes mutated successfully`(ind: MyIndividual) {
        val mutate = MutateUniformPerturbation(genePb = 0.5, min = 0.0, max = 1.0) { ind_: MyIndividual -> ind_.dna }
        mutate(ind)
    }

}
