import com.rits.cloning.Cloner
import individual.Individual
import individual.MultiObjectiveIndividual
import org.objenesis.ObjenesisStd
import population.PopulationFactory
import utils.Bit


class Ind: Individual()
class MOInd: MultiObjectiveIndividual()

class A(var l: MutableList<Int> = mutableListOf(1, 2, 3))
class B(val a: A)


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

    var a = A()
    var b = B(a)
    var bb = B(a)

    val cloner = Cloner()
    var bbb = cloner.deepClone(b)

    println("    a === a: ${a === a}")
    println("  b.a === a: ${b.a === a}")
    println(" bb.a === a: ${bb.a === a}")
    println("   bb === b: ${bb === b}")
    println("bbb.a === a: ${bbb.a === a}")


}
