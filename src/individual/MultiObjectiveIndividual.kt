package individual

import fitness.MultiObjectiveFitness

/**
 * Multi-objective Individual class.
 *
 * All classes used as individuals for multi-objective optimization should inherit
 * from this using the default, no argument constructor.
 *
 * E.g. `class MyIndividual(...): BaseMultiObjectiveIndividual() {...}`
 *
 * Inheriting from this class is necessary for the [MultiObjectiveFitness]
 * creation and initialization to be handled automatically, with no further action
 * on the matter required by the user.
 *
 * @property fitness The [MultiObjectiveFitness] object of this individual.
 */
abstract class MultiObjectiveIndividual: BaseIndividual<MultiObjectiveFitness>()
