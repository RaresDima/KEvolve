import individual.BaseIndividual


class Ind: BaseIndividual()



fun main(args: Array<String>) {
    println(Bit(0).value)
    println(Bit(0f).value)

    val ind: Ind = Ind()
    println(ind.fitness)

    
}
