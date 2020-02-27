import individual.BaseIndividual
import population.PopulationBuilder


class Ind: BaseIndividual()



fun main(args: Array<String>) {
    println(Bit(0).value)
    println(Bit(0f).value)

    val ind = Ind()
    println(ind.fitness)

    val popb = PopulationBuilder(::Ind)

    popb.spawn(50)
}
