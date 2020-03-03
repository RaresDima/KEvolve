import benchmark.continuous.singleobjective.Rastrigin
import individual.Individual
import population.PopulationFactory
import selection.SelectTournament
import utils.BinaryRepresentation
import utils.Bit


fun main(args: Array<String>) {

    // Params

    val MIN = -5.12
    val MAX = +5.12
    val DIGITS = 3

    val DIMS = 3
    val POP_SIZE = 100

    val TOURN_SIZE = 3

    // Single number representation

    val repr = BinaryRepresentation(MIN, MAX, DIGITS)

    // Fitness

    val fitness = Rastrigin()

    // Individual and Population

    class RastriginIndividual(val bits: MutableList<Bit>): Individual()

    val popFactory = PopulationFactory {
        RastriginIndividual(
            MutableList(DIMS * repr.nBits) { Bit.random() }
        )
    }

    val pop = popFactory.spawn(POP_SIZE)

    // Selection

    val select = SelectTournament(tournSize = TOURN_SIZE)

    // Crossover

    val crossover = CrossoverOnePoint { it.bits }

    // Mutation

    val mutate = MutateFlipBit(bitpb = 0.1)

    // GA

    for (ind in pop)
        ind.fitness.value = fitness(repr.splitToReal(ind.bits))

    for (g in 1..100) {

        val offspring = select(pop, POP_SIZE)


    }


}
