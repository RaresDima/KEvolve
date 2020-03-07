package crossover

import exceptions.operators.DnaTooSmallException
import exceptions.operators.InvalidNCuttingPointsException
import exceptions.operators.InvalidProbabilityException
import exceptions.operators.InvalidSbxEtaException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.random.Random

internal class CrossoverSimulatedBinaryTest {

    companion object {

        class MyIndividual(val dna: MutableList<Double>)

        @JvmStatic
        fun dnaValueProvider(): Stream<Arguments> = Stream.of(

            Arguments.of(
                MyIndividual(MutableList(4) { Random.nextDouble() }),
                MyIndividual(MutableList(4) { Random.nextDouble() })
            ),

            Arguments.of(
                MyIndividual(MutableList(10) { Random.nextDouble() }),
                MyIndividual(MutableList(10) { Random.nextDouble() })
            ),

            Arguments.of(
                MyIndividual(MutableList(100) { Random.nextDouble() }),
                MyIndividual(MutableList(100) { Random.nextDouble() })
            ),

            Arguments.of(
                MyIndividual(MutableList(1000) { Random.nextDouble() }),
                MyIndividual(MutableList(1000) { Random.nextDouble() })
            )
        )

    }

    @Test
    fun `initialize crossover`() { CrossoverSimulatedBinary(eta = 1.0, genePb = 1.0) { ind: MyIndividual -> ind.dna } }

    @Test
    fun `initialize crossover pb 0,0`() {
        assertThrows<InvalidProbabilityException> {
            CrossoverSimulatedBinary(eta = 1.0, genePb = 0.0) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `initialize crossover pb 1,1`() {
        assertThrows<InvalidProbabilityException> {
            CrossoverSimulatedBinary(eta = 1.0, genePb = 1.1) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `initialize crossover eta -1,0`() {
        assertThrows<InvalidSbxEtaException> {
            CrossoverSimulatedBinary(eta = -1.0, genePb = 0.9) { ind: MyIndividual -> ind.dna }
        }
    }

    @Test
    fun `dna size is 0`() {
        val crossover = CrossoverSimulatedBinary(eta = 1.0, genePb = 0.5) { ind: MyIndividual -> ind.dna }
        val ind1 = MyIndividual(mutableListOf())
        val ind2 = MyIndividual(mutableListOf())
        assertThrows<DnaTooSmallException> { crossover(ind1, ind2) }
    }

    @Test
    fun `dna size is 1`() {
        val crossover = CrossoverSimulatedBinary(eta = 1.0, genePb = 0.5) { ind: MyIndividual -> ind.dna }
        val ind1 = MyIndividual(mutableListOf(1.0))
        val ind2 = MyIndividual(mutableListOf(2.0))
        assertThrows<DnaTooSmallException> { crossover(ind1, ind2) }
    }

    @ParameterizedTest
    @MethodSource("dnaValueProvider")
    fun `genes crossover successfully`(ind1: MyIndividual, ind2: MyIndividual) {
        val crossover = CrossoverSimulatedBinary(eta = 1.0, genePb = 0.5) { ind: MyIndividual -> ind.dna }
        val initHash = ind1.dna.sum() + ind2.dna.sum()
        crossover(ind1, ind2)
        val endHash = ind1.dna.sum() + ind2.dna.sum()
        assert(initHash.minus(endHash).absoluteValue < 10.0.pow(-10.0))
    }

}
