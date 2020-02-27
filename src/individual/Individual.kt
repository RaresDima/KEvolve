package individual

import extensions.delegates.AssignOnce
import fitness.Fitness

/**
 * Single-objective Individual class.
 *
 * All classes used as individuals for single-objective optimization should
 * inherit from this using the default, no argument constructor.
 *
 * E.g. `class MyIndividual(...): Individual() {...}`
 *
 * Inheriting from this class is necessary for the [Fitness] creation and
 * initialization to be handled automatically, with no further action on the
 * matter required by the user.
 *
 * @property fitness The [Fitness] object of this individual.
 */
abstract class Individual: BaseIndividual<Fitness>()
