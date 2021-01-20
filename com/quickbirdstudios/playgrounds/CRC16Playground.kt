package com.quickbirdstudios.playgrounds

import com.quickbirdstudios.CRC16

@ExperimentalUnsignedTypes
fun main(args: Array<String>) {
    val crc16 = CRC16()

    println("CRC: ${crc16.value}")      // initial value is 0
    crc16.update(1.0)
    crc16.update(1)
    crc16.update(1.toUByte())
    crc16.update(
        ubyteArrayOf(1.toUByte(), 20.toUByte())
    )

    // get the current crc value
    println("CRC: ${crc16.value}")

    // append more
    crc16.update(5.6)
    crc16.update(225)
    crc16.update(ubyteArrayOf(1.toUByte()))

    // get the current crc value
    println("CRC: ${crc16.value}")

    // reset the crc value
    crc16.reset()

    crc16.value  // value is 0 after reset

    println("CRC: ${crc16.value}")

    // CRC16's lookup table
    println("Lookup Table: ${crc16.lookupTable}")

    // CRC16's lookup table (hexadecimal representation)
    val lookupTableHexa = crc16.lookupTable.map { it.toString(radix = 16) }
    println("Lookup Table(Hexa): $lookupTableHexa")
}
