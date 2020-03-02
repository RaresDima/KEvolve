package benchmark.continuous.singleobjective

import benchmark.BaseFitnessFunction

abstract class BaseContinuousSingleObjectiveFitnessFunction: BaseFitnessFunction() {
    abstract override operator fun invoke(): Double
}