package com.zistus.domain.state

/**
 * A wrapper for api and db response state
 */
sealed class DataState<out T: Any> {
    /**
     * State showing data to be the last update
     */
    data class Success<out T: Any>(val data: T) : DataState<T>()

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<out T: Any>(val data: T) : DataState<T>()

    /**
     * A state to show a [throwable] is thrown.
     */
    data class Failed<out T: Throwable>(val error: Throwable, val data: T?) : DataState<T>()
}

/*

sealed class Output<out T : Any>{
    data class Success<out T : Any>(val output : T) : Output<T>()
    data class Error(val exception: Exception)  : Output<Nothing>()
}
 */

/*
package com.ajocard.domain.common
/**
 * A wrapper for database and network states.
 */
sealed class ResultState<T> {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(val data: T) : ResultState<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultState<T>()

    /**
     * A state to show a [throwable] is thrown.
     */
    data class Error<T>(val throwable: Throwable, val data: T?) : ResultState<T>()
}
 */