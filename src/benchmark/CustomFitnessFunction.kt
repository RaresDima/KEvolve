package benchmark

import mutation.BaseMutation
import mutation.MutateCustom
import java.lang.IllegalStateException
import kotlin.math.PI
import kotlin.math.cos

/**
 * A custom fitness function.
 *
 * @property data
 *  A [Map] mapping [String] to [Any]. This is intended to be used as a way to
 *  store any additional data you might need, regardless of the type. This [Map] is
 *  passed to [fitness] when it is called so it can be used to conveniently
 *  transfer data from outside the [CustomFitnessFunction] to inside the function
 *  used by [CustomFitnessFunction].
 *
 *  Since the values in the [Map] are stored as [Any] they need to be cast back to
 *  their original types when accessed. E.g:
 *
 *  `CustomFitnessFunction("num" to 6.0) { ind: MyIndividual, data ->`
 *
 *  `ind.someValue / (data["num"] as Double)`
 *
 *  `}`
 *
 * @constructor
 * @param data
 *  A [Map] mapping [String] to [Any]. This is intended to be used as a way to
 *  store any additional data you might need, regardless of the type. This [Map] is
 *  passed to [fitness] when it is called so it can be used to conveniently
 *  transfer data from outside the [CustomFitnessFunction] to inside the function
 *  used by [CustomFitnessFunction].
 * @param fitness
 *  A function that takes an Individual and a [Map] mapping [String] to [Any].
 *  It should return the fitness of that individual. This fitness will probably be
 *  either a [Double] or a [List] or [Double]s. It can either be a new individual
 *  or the same individual mutated in-place. The [Map] will contain any additional
 *  data placed into [data].
 */
class CustomFitnessFunction<INDIVIDUAL, FITNESS>(
    val data: Map<String, Any>,
    private val fitness: (INDIVIDUAL, Map<String, Any>) -> FITNESS) {

    /**
     * @param data
     *  [Pair]s of [String] to [Any]. These will be placed into a [Map] and will be
     *  used as a way to store any additional data you might need for the fitness,
     *  regardless of the type. This [Map] is passed to [fitness] when it is called so
     *  it can be used to conveniently transfer data from outside the
     *  [CustomFitnessFunction] to inside the function used by [CustomFitnessFunction].
     * @param fitness
     *  A function that takes an Individual and a [Map] mapping [String] to [Any].
     *  It should return the fitness of that individual. This fitness will probably be
     *  either a [Double] or a [List] or [Double]s. It can either be a new individual
     *  or the same individual mutated in-place. The [Map] will contain any additional
     *  data placed into [data].
     */
    constructor(vararg data: Pair<String, Any>,
                fitness: (INDIVIDUAL, Map<String, Any>) -> FITNESS): this(mapOf(*data), fitness)

    /**
     * Compute the fitness function.
     *
     * @param ind The individual to compute for.
     *
     * @return The individual's fitness.
     */
    operator fun invoke(ind: INDIVIDUAL): FITNESS = fitness(ind, data)

}
