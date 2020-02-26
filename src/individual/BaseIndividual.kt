package individual

import extensions.delegates.AssignOnce
import fitness.Fitness

abstract class BaseIndividual {
    var fitness: Fitness by AssignOnce()
}
