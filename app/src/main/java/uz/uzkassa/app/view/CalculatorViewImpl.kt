package uz.uzkassa.app.view

import android.widget.Button
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.arkivanov.mvikotlin.core.view.BaseMviView
import uz.uzkassa.app.R

class CalculatorViewImpl(private val binding: ViewBinding) :
    BaseMviView<CalculatorView.Model, CalculatorView.Event>(),
    CalculatorView {
    init {
        with(binding.root) {
            findViewById<Button>(R.id.increment_button).setOnClickListener {
                dispatch(CalculatorView.Event.IncrementClicked)
            }
            findViewById<Button>(R.id.decrement_button).setOnClickListener {
                dispatch(CalculatorView.Event.DecrementClicked)
            }
            findViewById<Button>(R.id.total_sum_button).setOnClickListener {
                dispatch(CalculatorView.Event.TotalSumClicked)
            }
        }
    }

    override fun render(model: CalculatorView.Model) {
        binding.root.findViewById<TextView>(R.id.counter_text_view).text = model.value
        binding.root.findViewById<TextView>(R.id.total_sum_text_view).text = model.value
    }
}