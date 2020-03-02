package benchmark.continuous.singleobjective

abstract class BaseContinuousSingleObjectiveFitnessFunction() {
    abstract fun invoke(x: List<Double>): Double
}
