package mutation

import exceptions.mutation.InvalidProbabilityException
import exceptions.mutation.InvalidStDevException
import exceptions.utils.InvalidDomainBoundsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.Bit
import java.lang.IllegalStateException
import java.util.stream.Stream
import kotlin.random.Random
import kotlin.test.assertEquals

internal class MutateCustomTest {

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
    fun `access getDna()`() {
        val mutate = MutateCustom { ind: MyIndividual, _ -> ind }
        val ind = MyIndividual(MutableList(10) { Random.nextDouble() })
        assertThrows<IllegalStateException> { mutate.getDna(ind) }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateCustom { ind: MyIndividual, _ -> ind }
        val ind = MyIndividual(mutableListOf())
        mutate(ind)
    }

    @ParameterizedTest
    @MethodSource("dnaValueProvider")
    fun `genes mutated successfully div 2`(ind: MyIndividual) {

        val mutate = MutateCustom("divisor" to 2.0) { ind_: MyIndividual, data ->
            ind_.dna.replaceAll { it / (data["divisor"] as Double) }
            ind
        }

        val originalDna = ind.dna.toMutableList()
        val mutant = mutate(ind)
        assertEquals(mutant.dna.map { it * 2.0 }, originalDna)
    }

}
