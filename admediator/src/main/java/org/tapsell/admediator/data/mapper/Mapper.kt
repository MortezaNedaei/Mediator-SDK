package org.tapsell.admediator.data.mapper


interface Mapper<in Input, out Output> {
    fun map(input: Input): Output
}
