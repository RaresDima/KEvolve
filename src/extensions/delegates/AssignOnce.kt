package extensions.delegates

import java.lang.IllegalStateException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AssignOnce<in THIS_CLASS: Any, PROPERTY_TYPE: Any>: ReadWriteProperty<THIS_CLASS, PROPERTY_TYPE> {
    var isSet: Boolean = false
    lateinit var value: PROPERTY_TYPE

    override fun getValue(thisRef: THIS_CLASS, property: KProperty<*>): PROPERTY_TYPE {
        if (isSet)
            return value
        else
            throw IllegalStateException("${thisRef.javaClass.name}.${property.name} has not been set.")

    }

    override fun setValue(thisRef: THIS_CLASS, property: KProperty<*>, value: PROPERTY_TYPE) {
        if (!isSet) {
            this.value = value
            isSet = true
        }
        else
            throw IllegalStateException("${thisRef.javaClass.name}.${property.name} can only be set once.")

    }
}
