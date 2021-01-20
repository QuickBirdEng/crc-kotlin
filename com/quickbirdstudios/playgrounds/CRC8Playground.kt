package com.quickbirdstudios.playgrounds

import com.quickbirdstudios.CRC8

@ExperimentalUnsignedTypes
fun main() {
    val crc8 = CRC8()

    println("CRC: ${crc8.value}")      // initial value is 0
    crc8.update(1.0)
    crc8.update(1)
    crc8.update(1.toUByte())
    crc8.update(
        ubyteArrayOf(1.toUByte(), 20.toUByte())
    )

    // get the current crc value
    println("CRC: ${crc8.value}")

    // append more
    crc8.update(5.6)
    crc8.update(225)
    crc8.update(ubyteArrayOf(1.toUByte()))

    // get the current crc value
    println("CRC: ${crc8.value}")

    // reset the crc value
    crc8.reset()

    crc8.value  // value is 0 after reset

    println("CRC: ${crc8.value}")

    // CRC8's lookup table
    println("Lookup Table: ${crc8.lookupTable}")

    // CRC8's lookup table (hexadecimal representation)
    val lookupTableHexa = crc8.lookupTable.map { it.toString(radix = 16) }
    println("Lookup Table(Hexa): $lookupTableHexa")
}
