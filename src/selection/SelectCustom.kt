package selection

import individual.BaseIndividual

/**
 * Select individuals in the population using a custom function.
 *
 * This class is intended to be used to implement highly customized logic without
 * the need to create a new class inheriting from [BaseSelection].
 *
 * - NOTE: [SelectCustom] provides a quick way to test more complex mutation logic
 * without creating a new class yet. A class inheriting [BaseSelection] will very
 * likely be more powerful and lead to cleaner code than a [SelectCustom] though.
 *
 * @property data
 *  A [Map] mapping [String] to [Any]. This is intended to be used as a way to
 *  store any additional data you might need, regardless of the type. This [Map] is
 *  passed to [select] when it is called so it can be used to conveniently transfer
 *  data from outside the [SelectCustom] to inside the function used by
 *  [SelectCustom].
 *
 *  Since the values in the [Map] are stored as [Any] they need to be cast back to
 *  their original types when accessed. E.g:
 *
 *  `SelectCustom("n" to 6.0) { pop: List<MyIndividual>, k, data ->`
 *
 *  `(1..k).map { i -> pop[i * data["n"] as Double % pop.size] }`
 *
 *  `}`
 *
 * @constructor
 * @param data
 *  A [Map] mapping [String] to [Any]. This is intended to be used as a way to
 *  store any additional data you might need, regardless of the type. This [Map] is
 *  passed to [select] when it is called so it can be used to conveniently transfer
 *  data from outside the [SelectCustom] to inside the function used by
 *  [SelectCustom].
 * @param select
 *  A function that takes population ([List]) Individuals, and a [Map] mapping
 *  [String] to [Any]. It should return a [MutableList] of the selected
 *  individuals. They can either be a new individuals or references to the
 *  individuals in the initial population. The [Map] will contain any additional
 *  data placed into [data].
 */
class SelectCustom<INDIVIDUAL: BaseIndividual<*>>(
    val data: Map<String, Any>,
    private val select: (List<INDIVIDUAL>, Int, Map<String, Any>) -> MutableList<INDIVIDUAL>): BaseSelection() {

    /**
     * @param data
     *  [Pair]s of [String] to [Any]. These will be placed into a [Map] and will be
     *  used as a way to store any additional data you might need for the mutation,
     *  regardless of the type. This [Map] is passed to [mutate] when it is called so
     *  it can be used to conveniently transfer data from outside the [SelectCustom] to
     *  inside the function used by [SelectCustom].
     * @param select
     *  A function that takes population ([List]) Individuals, and a [Map] mapping
     *  [String] to [Any]. It should return a [MutableList] of the selected
     *  individuals. They can either be a new individuals or references to the
     *  individuals in the initial population. The [Map] will contain any additional
     *  data placed into [data].
     */
    constructor(vararg data: Pair<String, Any>,
                select: (List<INDIVIDUAL>, Int, Map<String, Any>) -> MutableList<INDIVIDUAL>): this(mapOf(*data), select)

    /**
     * Select the desired number of individuals.
     *
     * @param pop The population to select from.
     * @param k How many individuals to select.
     *
     * @return A [List] with the selected individuals.
     */
    override fun <INDIVIDUAL_: BaseIndividual<*>> invoke(pop: List<INDIVIDUAL_>, k: Int): MutableList<INDIVIDUAL_> =
        select(pop.map { it as INDIVIDUAL }, k, data).map { it as INDIVIDUAL_ }.toMutableList()

}
