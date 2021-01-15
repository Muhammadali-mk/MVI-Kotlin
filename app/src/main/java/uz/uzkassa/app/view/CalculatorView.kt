package uz.uzkassa.app.view

import com.arkivanov.mvikotlin.core.view.MviView

interface CalculatorView : MviView<CalculatorView.Model, CalculatorView.Event> {

    data class Model(val value: String)

    sealed class Event {
        object IncrementClicked : Event()
        object DecrementClicked : Event()
        object TotalSumClicked : Event()
    }
}