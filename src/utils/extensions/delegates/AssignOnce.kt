package utils.extensions.delegates

import exceptions.utils.extensions.delegates.AlreadyAssignedPropertyAssignmentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegated Property used to make values lazily initializable.
 *
 * A var property using this delegate can only be assigned once.
 *
 * If the variable is read from before the initialization then an
 * [UninitializedPropertyAccessException] is thrown.
 *
 * If the variable is assigned another value after being initialized then an
 * [AlreadyAssignedPropertyAssignmentException] is thrown.
 *
 * @throws UninitializedPropertyAccessException
 *  If the variable is read from before the initialization.
 *
 * @throws AlreadyAssignedPropertyAssignmentException
 *  If the variable is assigned another value after being initialized.
 */
class AssignOnce<in THIS_CLASS: Any, PROPERTY_TYPE: Any>: ReadWriteProperty<THIS_CLASS, PROPERTY_TYPE> {
    var isSet: Boolean = false
    lateinit var value: PROPERTY_TYPE

    override fun getValue(thisRef: THIS_CLASS, property: KProperty<*>): PROPERTY_TYPE {
        if (isSet)
            return value
        else
            throw UninitializedPropertyAccessException("${thisRef.javaClass.name}.${property.name} has not been set.")

    }

    override fun setValue(thisRef: THIS_CLASS, property: KProperty<*>, value: PROPERTY_TYPE) {
        if (!isSet) {
            this.value = value
            isSet = true
        }
        else
            throw AlreadyAssignedPropertyAssignmentException("${thisRef.javaClass.name}.${property.name} can only be set once.")

    }
}
