import benchmark.FitnessFunctionCustom
import benchmark.continuous.singleobjective.Rastrigin
import individual.Individual
import mutation.MutateCustom
import mutation.MutateFlipBits
import population.PopulationFactory
import selection.SelectCustom
import selection.SelectTournament
import utils.BinaryRepresentation
import utils.Bit
import kotlin.math.pow
import kotlin.random.Random

fun main(args: Array<String>) {

    // Params

    val MIN = -5.12
    val MAX = +5.12
    val DIGITS = 3

    val DIMS = 3
    val POP_SIZE = 100

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

    val select = SelectTournament(tournSize = 3)

    // TODO val crossover = CrossoverOnePoint { it.bits }

    val mutate = MutateFlipBits(bitPb = 0.1) { ind: RastriginIndividual -> ind.bits }


    val fitnessCustom = FitnessFunctionCustom { ind: RastriginIndividual, _ ->
        ind.bits.sumBy { it.value }.toDouble().pow(1.0 / ind.bits.size)
    }

    val selectCustom = SelectCustom { pop: List<RastriginIndividual>, k, data ->
        (1..k).map { pop.random() }.toMutableList()
    }

    val mutateCustom = MutateCustom { ind: RastriginIndividual, _ ->
        mutate(ind)
        mutate(ind)
        ind
    }



    // GA

    val pop = popFactory.spawn(POP_SIZE)  // Create the population.

    selectCustom(pop, 5)

    for (ind in pop)
        ind.fitness.value = fitness(repr.splitToReal(ind.bits))  // Evaluate the population

    for (g in 1..100) {

        var offspring = select(pop, POP_SIZE)  // Selection

        offspring = popFactory.clone(offspring) // Clone individuals since an individual can be selected
                                                // multiple times but it is still one object with multiple
                                                // references to it.

        for ((child1, child2) in offspring.chunked(2)) {    // Crossover
            if (Random.nextDouble(1.0) < CX_PB) {
                // TODO crossover(child1, child2)
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
