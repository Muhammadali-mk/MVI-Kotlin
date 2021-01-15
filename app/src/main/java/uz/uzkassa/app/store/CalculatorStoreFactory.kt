package uz.uzkassa.app.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalculatorStoreFactory(
    private val storeFactory: StoreFactory
) {
    fun create(): CalculatorStore =
        object : CalculatorStore,
            Store<CalculatorStore.Intent, CalculatorStore.State, Nothing> by storeFactory.create(
                name = "CounterStore",
                initialState = CalculatorStore.State(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed class Result {
        data class Value(val value: Long) : Result()
    }

    private object ReducerImpl : Reducer<CalculatorStore.State, Result> {

        override fun CalculatorStore.State.reduce(result: Result): CalculatorStore.State =
            when (result) {
                is Result.Value -> copy(value = result.value)
            }
    }

    private class ExecutorImpl :
        SuspendExecutor<CalculatorStore.Intent, Nothing, CalculatorStore.State, Result, Nothing>() {
        override suspend fun executeIntent(
            intent: CalculatorStore.Intent,
            getState: () -> CalculatorStore.State
        ) = when (intent) {
            CalculatorStore.Intent.Increment -> dispatch(Result.Value(getState().value + 1L))
            CalculatorStore.Intent.Decrement -> dispatch(Result.Value(getState().value + 1L))
            is CalculatorStore.Intent.Sum -> calculateSum(intent.n)
        }

        private suspend fun calculateSum(n: Int) {
            val sum = withContext(Dispatchers.Default) {
                (1..n.toLong()).sum()
            }
            dispatch(Result.Value(sum))
        }
    }
}