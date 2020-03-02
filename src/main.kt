import benchmark.rastrigin
import individual.Individual
import population.PopulationFactory
import utils.BinaryRepresentation
import utils.Bit


fun main(args: Array<String>) {

    val dims = 3

    // Single number representation

    val min = -5.12
    val max = +5.12
    val digits = 3
    val repr = BinaryRepresentation(min, max, digits)

    // Fitness

    val fitness = ::rastrigin

    // Individual and Population

    class RastriginIndividual(val bits: List<Bit>): Individual()
    val popFactory = PopulationFactory {
        RastriginIndividual(
            List(dims * repr.nBits) { Bit.random() }
        )
    }

    // Selection

    // Crossover

    // Mutation


}
