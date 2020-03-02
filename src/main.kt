import benchmark.rastrigin
import individual.Individual
import population.PopulationFactory
import utils.BinaryRepresentation
import utils.Bit


fun main(args: Array<String>) {

    // Params

    val MIN = -5.12
    val MAX = +5.12
    val DIGITS = 3

    val DIMS = 3
    val POP_SIZE = 100

    // Single number representation

    val repr = BinaryRepresentation(MIN, MAX, DIGITS)

    // Fitness

    val fitness = ::rastrigin

    // Individual and Population

    class RastriginIndividual(val bits: List<Bit>): Individual()
    val popFactory = PopulationFactory {
        RastriginIndividual(
            List(DIMS * repr.nBits) { Bit.random() }
        )
    }

    var pop = popFactory.spawn(POP_SIZE)

    // Selection

    // Crossover

    // Mutation


}
