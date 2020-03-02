package utils

import exceptions.utils.InvalidDomainBoundsException
import exceptions.utils.InvalidPrecisionException
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

/**
 * Utility class for the classic binary representation used in Genetic
 * Algorithms.
 *
 * This binary representation differs from a "regular" one by the fact that it
 * can represent numbers from an arbitrary domain (interval) with an arbitrary
 * (predefined) precision using a fixed number of bits.
 *
 * This number of bits is the minimum number of bits needed to represent any
 * of the numbers in the given domain with the given precision.
 *
 * @property min The lower bound of the domain.
 * @property max The upper bound of the domain.
 * @property digits How many digits after the decimal point should be represented.
 *
 * @property precision 10^-digits E.g. if [digits] = 3 then [precision] = 0.001.
 * @property domainSize Size of the domain. [max] - [min].
 * @property nIntervals The number of subintervals the domain is split into.
 * @property nBits The number of bits needed to represent a value.
 *
 * @constructor
 * @param min The lower bound of the domain.
 * @param max The upper bound of the domain.
 * @param digits How many digits after the decimal point should be represented.
 *
 * @throws InvalidDomainBoundsException if [min] >= [max]
 * @throws InvalidPrecisionException if [digits] < 0
 */
class BinaryRepresentation(
    val min: Double,
    val max: Double,
    val digits: Int) {

    init {
        if (min >= max)
            throw InvalidDomainBoundsException("min = $min | max = $max | min >= max")

        if (digits < 0)
            throw InvalidPrecisionException("digits = $digits | digits < 0")
    }

    val precision: Double = 10.0.pow(-digits)
    val domainSize: Double = max - min
    val nIntervals: Double = domainSize * precision
    val nBits: Int = ceil(log2(nIntervals)).toInt()

    companion object {

        @JvmName("binaryToDecimalNumber")
        private fun <NUMBER_TYPE: Number> binaryToDecimal(bin: List<NUMBER_TYPE>): Long {
            var num = 0L
            var exp = 1L
            for (b in bin.asReversed()) {
                if (b.toInt() > 0)
                    num += exp
                exp *= 2
            }
            return num
        }

        @JvmName("binaryToDecimalBoolean")
        private fun binaryToDecimal(bin: List<Boolean>): Long {
            var num = 0L
            var exp = 1L
            for (b in bin.asReversed()) {
                if (b)
                    num += exp
                exp *= 2
            }
            return num
        }

        @JvmName("binaryToDecimalBit")
        private fun binaryToDecimal(bin: List<Bit>): Long {
            var num = 0L
            var exp = 1L
            for (b in bin.asReversed()) {
                if (b.toBoolean())
                    num += exp
                exp *= 2
            }
            return num
        }
    }

    /**
     * Converts a binary representation to its real ([Double]) value using
     * this specific domain and precision.
     *
     * @param xBinary The list of bits.
     *
     * @return The real([Double]) value of [xBinary].
     */
    @JvmName("toRealNumber")
    fun <NUMBER_TYPE: Number> toReal(xBinary: List<NUMBER_TYPE>): Double =
        min + binaryToDecimal(xBinary) * (domainSize / (2.0.pow(nBits) - 1))

    /**
     * Converts a binary representation to its real ([Double]) value using
     * this specific domain and precision.
     *
     * @param xBinary The list of bits.
     *
     * @return The real([Double]) value of [xBinary].
     */
    @JvmName("toRealBoolean")
    fun toReal(xBinary: List<Boolean>): Double =
        min + binaryToDecimal(xBinary) * (domainSize / (2.0.pow(nBits) - 1))

    /**
     * Converts a binary representation to its real ([Double]) value using
     * this specific domain and precision.
     *
     * @param xBinary The list of bits.
     *
     * @return The real([Double]) value of [xBinary].
     */
    @JvmName("toRealBit")
    fun toReal(xBinary: List<Bit>): Double =
        min + binaryToDecimal(xBinary) * (domainSize / (2.0.pow(nBits) - 1))

}
