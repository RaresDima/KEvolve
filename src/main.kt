import individual.Individual
import individual.MultiObjectiveIndividual
import population.PopulationFactory
import utils.Bit


class Ind: Individual()
class MOInd: MultiObjectiveIndividual()


fun main(args: Array<String>) {
    println(Bit(0).value)
    println(Bit(0f).value)

    val ind = Ind()
    try {
        println(ind.fitness)
    } catch (e: UninitializedPropertyAccessException) {
        println("ind.fitness threw $e")
    }

    val pf = PopulationFactory(::Ind)
    val pop = pf.spawn(50)
    println(pop[0].fitness.value)

    val mopf = PopulationFactory(::MOInd)
    val mopop = mopf.spawn(50)
    println(mopop[0].fitness.values)
}
