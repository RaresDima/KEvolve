package individual

import extensions.delegates.AssignOnce
import fitness.BaseFitness

abstract class BaseIndividual<FITNESS_TYPE: BaseFitness> {
    var fitness: FITNESS_TYPE by AssignOnce()
}
