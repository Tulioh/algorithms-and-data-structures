package com.tuliomagalhaes.chunk

import java.util.*

/*fun chunk(array: Array<Int>, size: Int): Array<Array<Int>> {
    var result = emptyArray<Array<Int>>()

    var arrayPosition = 0
    while(arrayPosition <= array.size - 1) {
        result = java.util.Arrays.copyOf(result, result.size + 1)
        var targetPos = arrayPosition + size
        if (targetPos > array.size) {
            targetPos -= (targetPos - array.size)
        }
        result[result.size - 1] = array.copyOfRange(arrayPosition, targetPos)
        arrayPosition += size
    }

    return result
}*/

fun chunk(array: Array<Int>, size: Int): Array<Array<Int>> {
    var chunk = emptyArray<Array<Int>>()

    for(arrayPosition in array.indices step size) {
        chunk = java.util.Arrays.copyOf(chunk, chunk.size + 1)
        var targetPos = arrayPosition + size
        if (targetPos > array.size) {
            targetPos -= (targetPos - array.size)
        }
        chunk[chunk.size - 1] = array.copyOfRange(arrayPosition, targetPos)
    }

    return chunk
}

/*fun chunkList(array: List<Int>, size: Int): List<List<Int>> {
    val chunk = mutableListOf<MutableList<Int>>()

    array.forEach {
        val lastChunk = chunk.lastSafe()
        if (lastChunk == null || lastChunk.size == size) {
            chunk.add(mutableListOf(it))
        } else  {
            chunk.last().add(it)
        }
    }

    return chunk
}

private fun <T> List<T>.lastSafe(): T? {
    return try {
        this[size - 1]
    } catch (ex: Exception) {
        return null
    }
}*/

fun chunkList(array: List<Int>, size: Int): List<List<Int>> {
    val chunked = mutableListOf<List<Int>>()
    var index = 0

    while (index < array.size) {
        var targetPos = index + size
        if (targetPos > array.size) {
            targetPos -= (targetPos - array.size)
        }
        chunked.add(array.slice(index until targetPos))
        index += size
    }

    return chunked
}