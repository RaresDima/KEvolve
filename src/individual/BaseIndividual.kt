package individual

import utils.extensions.delegates.AssignOnce
import fitness.BaseFitness
import population.PopulationFactory

/**
 * Base Individual class that exposes functionalities all Individual classes
 * should have.
 *
 * @property fitness The Fitness object of this individual.
 */
abstract class BaseIndividual<FITNESS_TYPE: BaseFitness> {
    var fitness: FITNESS_TYPE by AssignOnce()
}
