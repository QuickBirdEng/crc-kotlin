package com.quickbirdstudios

/// Class to conveniently calculate CRC-16. It uses the CRC16-CCITT polynomial (0x1021)  by default
@ExperimentalUnsignedTypes
class CRC16(val polynomial: UShort = 0x1021.toUShort()) : CRC<UShort> {
    override val lookupTable: List<UShort> = (0 until 256).map { crc16(it.toUByte(), polynomial) }

    override var value: UShort = 0.toUShort()
        private set

    override fun update(inputs: UByteArray) {
        value = crc16(inputs, value)
    }

    override fun reset() {
        value = 0.toUShort()
    }

    private fun crc16(inputs: UByteArray, initialValue: UShort = 0.toUShort()): UShort {
        return inputs.fold(initialValue) { remainder, byte ->
            val bigEndianInput = byte.toBigEndianUShort()
            val index = (bigEndianInput xor remainder) shr 8
            lookupTable[index.toInt()] xor (remainder shl 8)
        }
    }

    private fun crc16(input: UByte, polynomial: UShort): UShort {
        val bigEndianInput = input.toBigEndianUShort()

        return (0 until 8).fold(bigEndianInput) { result, _ ->
            val isMostSignificantBitOne = result and 0x8000.toUShort() != 0.toUShort()
            val shiftedResult = result shl 1

            when (isMostSignificantBitOne) {
                true -> shiftedResult xor polynomial
                false -> shiftedResult
            }
        }
    }
}
