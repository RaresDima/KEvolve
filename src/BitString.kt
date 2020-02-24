import java.util.ArrayList

class BitString(val bits: List<Bit> ) {

    companion object {

        @JvmName("fromNumbers")
        operator fun invoke(bits: List<Number>)  = BitString(bits.map(::Bit))

        @JvmName("fromBooleans")
        operator fun invoke(bits: List<Boolean>) = BitString(bits.map(::Bit))
    }

    val values
        get() = bits.map(Bit::value)

}
