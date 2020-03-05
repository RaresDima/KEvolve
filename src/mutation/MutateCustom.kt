package mutation

import individual.BaseIndividual
import utils.Bit
import java.lang.IllegalStateException

/**
 * Replace values in the individual using a custom function.
 *
 * This class is intended to be used to implement highly customized logic without
 * the need to create a new class inheriting from [BaseMutation].
 *
 * - NOTE: [getDna] has no use in this class and will always throw an exception if called.
 *
 * - NOTE: [MutateCustom] provides a quick way to test more complex mutation logic
 * without creating a new class yet. A class inheriting [BaseMutation] will very
 * likely be more powerful and lead to cleaner code than a [MutateCustom] though.
 *
 * @property mutate
 *  A function that takes an Individual and returns a mutated Individual. It can
 *  either be a new individual or the same individual mutated in-place.
 *
 * @constructor
 * @param mutate
 *  A function that takes an Individual and returns a mutated Individual. It can
 *  either be a new individual or the same individual mutated in-place.
 */
class MutateCustom<INDIVIDUAL>(val mutate: (INDIVIDUAL) -> INDIVIDUAL):
    BaseMutation<INDIVIDUAL, Nothing>({
        throw IllegalStateException(
            "getDna() has no purpose in MutateCustom. Place all the mutation login in mutate()."
        )
    }) {

    var data: Map<String, Any> = mapOf()

    /**
     * Replace values in the individual using [mutate].
     *
     * @param ind The individual to mutate.
     *
     * @return The mutated individual.
     */
    override operator fun invoke(ind: INDIVIDUAL): INDIVIDUAL = mutate(ind)

}
