package uz.uzkassa.app.mapper

import uz.uzkassa.app.store.CalculatorStore
import uz.uzkassa.app.view.CalculatorView

fun CalculatorStore.State.map() = CalculatorView.Model(
    value = value.toString()
)

internal val stateToModel: CalculatorStore.State.() -> CalculatorView.Model =
    {
        CalculatorView.Model(
            value = value.toString()
        )

    }

internal val eventToIntent: CalculatorView.Event.() -> CalculatorStore.Intent =
    {
        when (this) {
            is CalculatorView.Event.IncrementClicked -> CalculatorStore.Intent.Increment
            is CalculatorView.Event.DecrementClicked -> CalculatorStore.Intent.Decrement
            CalculatorView.Event.TotalSumClicked -> CalculatorStore.Intent.Sum(100)
        }
    }

fun CalculatorView.Event.map(): CalculatorStore.Intent = when (this) {
    CalculatorView.Event.IncrementClicked -> CalculatorStore.Intent.Increment
    CalculatorView.Event.DecrementClicked -> CalculatorStore.Intent.Decrement
    CalculatorView.Event.TotalSumClicked -> CalculatorStore.Intent.Sum(100)
}