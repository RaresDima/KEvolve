package extensions

fun Boolean.toByte():  Byte  = if (this) 1 else 0
fun Boolean.toShort(): Short = if (this) 1 else 0
fun Boolean.toInt():   Int   = if (this) 1 else 0
fun Boolean.toLong():  Long  = if (this) 1 else 0

fun Boolean.toFloat():  Float  = if (this) 1f  else 0f
fun Boolean.toDouble(): Double = if (this) 1.0 else 0.0
