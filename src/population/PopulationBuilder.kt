package population

import fitness.Fitness
import individual.BaseIndividual

class PopulationBuilder<out INDIVIDUAL: BaseIndividual>(val createIndividual: () -> INDIVIDUAL) {
    fun spawn(n: Int): List<INDIVIDUAL> =
        List(n) {
            createIndividual().apply {
                fitness = Fitness()
            }
        }
}
