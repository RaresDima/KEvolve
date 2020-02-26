package individual

import extensions.delegates.AssignOnce
import fitness.MultiObjectiveFitness

abstract class BaseMultiObjectiveIndividual {
    var fitness: MultiObjectiveFitness by AssignOnce()
}
