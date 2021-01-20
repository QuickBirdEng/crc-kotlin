package com.quickbirdstudios

/// Class to conveniently calculate CRC-32 It uses the CRC32 polynomial (0x04C11DB7) by default
@ExperimentalUnsignedTypes
class CRC32(val polynomial: UInt = 0x04C11DB7.toUInt()) : CRC<UInt> {
    override val lookupTable: List<UInt> = (0 until 256).map { crc32(it.toUByte(), polynomial) }

    override var value: UInt = 0.toUInt()
        private set

    override fun update(inputs: UByteArray) {
        value = crc32(inputs, value)
    }

    override fun reset() {
        value = 0.toUInt()
    }

    private fun crc32(inputs: UByteArray, initialValue: UInt = 0.toUInt()): UInt {
        return inputs.fold(initialValue) { remainder, byte ->
            val bigEndianInput = byte.toBigEndianUInt()
            val index = (bigEndianInput xor remainder) shr 24
            lookupTable[index.toInt()] xor (remainder shl 8)
        }
    }

    private fun crc32(input: UByte, polynomial: UInt): UInt {
        val bigEndianInput = input.toBigEndianUInt()

        return (0 until 8).fold(bigEndianInput) { result, _ ->
            val isMostSignificantBitOne = result and 0x80000000.toUInt() != 0.toUInt()
            val shiftedResult = result shl 1

            when (isMostSignificantBitOne) {
                true -> shiftedResult xor polynomial
                false -> shiftedResult
            }
        }
    }
}
