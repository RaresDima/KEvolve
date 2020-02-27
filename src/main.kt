import individual.Individual
import individual.MultiObjectiveIndividual
import population.PopulationFactory
import java.lang.IllegalStateException


class Ind: Individual()
class MOInd: MultiObjectiveIndividual()


fun main(args: Array<String>) {
    println(Bit(0).value)
    println(Bit(0f).value)

    val ind = Ind()
    try {
        println(ind.fitness)
    } catch (e: IllegalStateException) {
        println("ind.fitness threw $e")
    }

    val pf = PopulationFactory(::Ind)

    val pop = pf.spawn(50)
    println(pop[0].fitness.value())
}
