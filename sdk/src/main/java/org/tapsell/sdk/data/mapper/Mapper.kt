package org.tapsell.sdk.data.mapper


interface Mapper<in Input, out Output> {
    fun map(input: Input): Output
}
