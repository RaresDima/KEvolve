import benchmark.continuous.singleobjective.Rastrigin
import individual.Individual
import population.PopulationFactory
import selection.SelectTournament
import utils.BinaryRepresentation
import utils.Bit
import kotlin.random.Random



fun <T> List<T>.pairs() = slice(0 until lastIndex step 2).zip(slice(1 until lastIndex step 2))


fun main(args: Array<String>) {

    // Params

    val MIN = -5.12
    val MAX = +5.12
    val DIGITS = 3

    val DIMS = 3
    val POP_SIZE = 100

    val TOURN_SIZE = 3

    val CX_PB = 0.5
    val MUT_PB = 0.1

    val repr = BinaryRepresentation(MIN, MAX, DIGITS)  // Handles Conversions: Binary <-> Real

    class RastriginIndividual(val bits: MutableList<Bit>): Individual()  // Just inherit Individual()

    val popFactory = PopulationFactory {  // Keyword-like initialization definition.
        RastriginIndividual(
            MutableList(DIMS * repr.nBits) { Bit.random() }
        )
    }

    // Operators are instantiated and then used as functions.

    val fitness = Rastrigin()

    val select = SelectTournament(tournSize = TOURN_SIZE)

    val crossover = CrossoverOnePoint { it.bits }

    val mutate = MutateFlipBit(bitpb = 0.1) { it.bits }

    // GA

    val pop = popFactory.spawn(POP_SIZE)  // Create the population.

    for (ind in pop)
        ind.fitness.value = fitness(repr.splitToReal(ind.bits))  // Evaluate the population

    for (g in 1..100) {

        var offspring = select(pop, POP_SIZE)  // Selection

        offspring = popFactory.clone(offspring) // Clone individuals since an individual can be selected
                                                // multiple times but it is still one object with multiple
                                                // references to it.

        for ((child1, child2) in offspring.pairs()) {    // Crossover
            if (Random.nextDouble(1.0) < CX_PB) {  // offspring.pairs() -> (1,2), (3,4), (5,6), ...
                crossover(child1, child2)
                child1.fitness.invalidate()
                child2.fitness.invalidate()
            }
        }

        for (child in offspring) {
            if (Random.nextDouble(1.0) < MUT_PB) {  // Mutation
                mutate(child)
                child.fitness.invalidate()
            }
        }

        pop.clear()
        pop.addAll(offspring)

        for (ind in pop)
            if (!ind.fitness.valid)
                ind.fitness.value = fitness(repr.splitToReal(ind.bits))  // Evaluate only the individuals that need it.
    }

    val solution = pop.maxBy { it.fitness }

}
