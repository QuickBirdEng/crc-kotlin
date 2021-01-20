package com.quickbirdstudios.playgrounds

import com.quickbirdstudios.CRC32

@ExperimentalUnsignedTypes
fun main() {
    val crc32 = CRC32()

    println("CRC: ${crc32.value}")      // initial value is 0
    crc32.update(1.0)
    crc32.update(1)
    crc32.update(1.toUByte())
    crc32.update(
        ubyteArrayOf(1.toUByte(), 20.toUByte())
    )

    // get the current crc value
    println("CRC: ${crc32.value}")

    // append more
    crc32.update(5.6)
    crc32.update(225)
    crc32.update(ubyteArrayOf(1.toUByte()))

    // get the current crc value
    println("CRC: ${crc32.value}")

    // reset the crc value
    crc32.reset()

    crc32.value  // value is 0 after reset

    println("CRC: ${crc32.value}")

    // CRC8's lookup table
    println("Lookup Table: ${crc32.lookupTable}")

    // CRC8's lookup table (hexadecimal representation)
    val lookupTableHexa = crc32.lookupTable.map { it.toString(radix = 16) }
    println("Lookup Table(Hexa): $lookupTableHexa")
}