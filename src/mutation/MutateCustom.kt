package mutation

import java.lang.IllegalStateException

/**
 * Replace values in the individual using a custom function.
 *
 * This class is intended to be used to implement highly customized logic without
 * the need to create a new class inheriting from [BaseMutation].
 *
 * - NOTE: [getDna] has no use in this class and will always throw an exception if
 * called.
 *
 * - NOTE: [MutateCustom] provides a quick way to test more complex mutation logic
 * without creating a new class yet. A class inheriting [BaseMutation] will very
 * likely be more powerful and lead to cleaner code than a [MutateCustom] though.
 *
 * @property data
 *  A [Map] mapping [String] to [Any]. This is intended to be used as a way to
 *  store any additional data you might need, regardless of the type. This [Map] is
 *  passed to [mutate] when it is called so it can be used to conveniently transfer
 *  data from outside the [MutateCustom] to inside the function used by
 *  [MutateCustom].
 *
 *  Since the values in the [Map] are stored as [Any] they need to be cast back to
 *  their original types when accessed. E.g:
 *
 *  `MutateCustom("number" to 6.0, "divisor" to 2.0) { ind: MyIndividual, data ->`
 *
 *  `ind.someValue = (data["number"] as Double) / (data["divisor"] as Double)`
 *
 *  `}`
 *
 * @constructor
 * @param data
 *  A [Map] mapping [String] to [Any]. This is intended to be used as a way to
 *  store any additional data you might need, regardless of the type. This [Map] is
 *  passed to [mutate] when it is called so it can be used to conveniently transfer
 *  data from outside the [MutateCustom] to inside the function used by
 *  [MutateCustom].
 * @param mutate
 *  A function that takes an Individual and a [Map] mapping [String] to [Any].
 *  It should return a mutated Individual. It can either be a new individual or the
 *  same individual mutated in-place. The [Map] will contain any additional data
 *  placed into [data].
 */
class MutateCustom<INDIVIDUAL>(
    val data: Map<String, Any>,
    private val mutate: (INDIVIDUAL, Map<String, Any>) -> INDIVIDUAL):

    BaseMutation<INDIVIDUAL, Nothing>({
        throw IllegalStateException(
            "getDna() has no purpose in MutateCustom. Place all the mutation login in mutate()."
        )
    }) {

    /**
     * @param data
     *  [Pair]s of [String] to [Any]. These will be placed into a [Map] and will be
     *  used as a way to store any additional data you might need for the mutation,
     *  regardless of the type. This [Map] is passed to [mutate] when it is called so
     *  it can be used to conveniently transfer data from outside the [MutateCustom] to
     *  inside the function used by [MutateCustom].
     * @param mutate
     *  A function that takes an Individual and a [Map] mapping [String] to [Any].
     *  It should return a mutated Individual. It can either be a new individual or the
     *  same individual mutated in-place. The [Map] will contain any additional data
     *  placed into [data].
     */
    constructor(vararg data: Pair<String, Any>,
                mutate: (INDIVIDUAL, Map<String, Any>) -> INDIVIDUAL): this(mapOf(*data), mutate)

    /**
     * Replace values in the individual using [mutate].
     *
     * @param ind The individual to mutate.
     *
     * @return The mutated individual.
     */
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL = mutate(ind, data)

}
