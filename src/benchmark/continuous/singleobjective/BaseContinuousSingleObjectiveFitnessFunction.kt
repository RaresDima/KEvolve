package benchmark.continuous.singleobjective

/**
 * Base Continuous Single-Objective Fitness Function class that exposes
 * functionalities all such fitness functions should have.
 */
abstract class BaseContinuousSingleObjectiveFitnessFunction {

    /**
     * Compute the value of the function for [x].
     *
     * @param x The argument for which to compute the function.
     *
     * @return The value of the function for [x] (f([x])).
     */
    abstract fun invoke(x: List<Double>): Double
}
