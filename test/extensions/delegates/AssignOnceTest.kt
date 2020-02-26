package extensions.delegates

import individual.BaseIndividual
import individual.BaseMultiObjectiveIndividual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalStateException
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class AssignOnceTest {

    companion object {

        class Individual: BaseIndividual()
        class MultiObjectiveIndividual: BaseMultiObjectiveIndividual()

    }


    @Test
    fun `Fitness uninitialized`() {
        val ind = Individual()
        assertThrows<IllegalStateException>("Individual.fitness") {  }
    }

    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun `value initialized`(v: Double) = assertEquals(Fitness().apply { value = v }.value, v)


    @Test
    fun `valid uninitialized`() = assertEquals(Fitness().valid, false)

    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun `valid initialized`(v: Double) = assertEquals(Fitness().apply { value = v }.valid, true)

    @ParameterizedTest
    @MethodSource("fitnessValueProvider")
    fun invalidate(v: Double) {
        val fit = Fitness().apply { value = v }
        assertEquals(fit.valid, true)
        fit.invalidate()
        assertEquals(fit.valid, false)
    }

}
