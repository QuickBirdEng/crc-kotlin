package com.quickbirdstudios

/// Class to conveniently calculate CRC-8. The polynomial to use. It uses CRC8-Bluetooth's polynomial (0xA7) by default
@ExperimentalUnsignedTypes
class CRC8(val polynomial: UByte = 0xA7.toUByte()) : CRC<UByte> {
    override val lookupTable: List<UByte> = (0 until 256).map { crc8(it.toUByte(), polynomial) }

    override var value: UByte = 0.toUByte()
        private set

    override fun update(inputs: UByteArray) {
        value = crc8(inputs, value)
    }

    override fun reset() {
        value = 0.toUByte()
    }

    private fun crc8(inputs: UByteArray, initialValue: UByte = 0.toUByte()): UByte {
        return inputs.fold(initialValue) { remainder, byte ->
            val index = byte xor remainder
            lookupTable[index.toInt()]
        }
    }

    private fun crc8(input: UByte, polynomial: UByte): UByte {
        return (0 until 8).fold(input) { result, _ ->
            val isMostSignificantBitOne = result and 0x80.toUByte() != 0.toUByte()
            val shiftedResult = result shl 1

            when (isMostSignificantBitOne) {
                true -> shiftedResult xor polynomial
                false -> shiftedResult
            }
        }
    }
}
