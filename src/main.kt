import extensions.delegates.AssignOnce
import fitness.Fitness


class Ind {
    var fitness: Fitness by AssignOnce()
}



fun main(args: Array<String>) {
    println(Bit(0).value)
    println(Bit(0f).value)

    val ind: Ind = Ind()
    println(ind.fitness)

    
}
