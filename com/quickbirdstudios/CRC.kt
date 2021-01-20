package com.quickbirdstudios

interface CRC<T> {
    val lookupTable: List<T>
    val value: T

    fun update(inputs: UByteArray)
    fun reset()

    fun update(input: UByte) {
        update(ubyteArrayOf(input))
    }

    fun update(inputs: ByteArray) {
        update(inputs.toUByteArray())
    }

    fun update(input: Int) {
        update(input.toByteArray())
    }

    fun update(input: Double) {
        update(input.toByteArray())
    }
}