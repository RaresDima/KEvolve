package mutation

import exceptions.mutation.DnaTooSmallException
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
import kotlin.test.assertEquals

internal class MutateShuffleIndexesTest {

    companion object {

        class MyIndividual(val dna: MutableList<Number>)

        @JvmStatic
        fun dnaValueProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(MyIndividual(MutableList(2) { Random.nextDouble() })),
            Arguments.of(MyIndividual(MutableList(10) { Random.nextFloat() })),
            Arguments.of(MyIndividual(MutableList(100) { Random.nextLong() })),
            Arguments.of(MyIndividual(MutableList(1000) { Random.nextInt() }))
        )

    }


    @Test
    fun `genePb is 0,0`() {
        assertThrows<InvalidProbabilityException> {
            MutateShuffleIndexes(genePb = 0.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `genePb is 1,0`() {
        MutateShuffleIndexes(genePb = 1.0) { ind: MyIndividual -> ind.dna }
    }

    @Test
    fun `genePb is 1,1`() {
        assertThrows<InvalidProbabilityException> {
            MutateShuffleIndexes(genePb = 1.1) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `dna size is 0`() {
        val mutate = MutateShuffleIndexes(genePb = 1.0) { ind: MyIndividual -> ind.dna }
        val ind = MyIndividual(mutableListOf())
        assertThrows<DnaTooSmallException> { mutate(ind) }
    }

    @ParameterizedTest
    @MethodSource("dnaValueProvider")
    fun `genes mutated successfully`(ind: MyIndividual) {
        val mutate = MutateShuffleIndexes(genePb = 0.5) { ind_: MyIndividual -> ind_.dna }
        val geneSet = ind.dna.toSet()
        mutate(ind)
        val mutatedGeneSet = ind.dna.toSet()
        assertEquals(geneSet, mutatedGeneSet)
    }

}
