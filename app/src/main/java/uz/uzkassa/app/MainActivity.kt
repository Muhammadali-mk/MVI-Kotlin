package uz.uzkassa.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import uz.uzkassa.app.controller.CalculatorController
import uz.uzkassa.app.databinding.ActivityMainBinding
import uz.uzkassa.app.view.CalculatorViewImpl

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var controller: CalculatorController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller = CalculatorController()
        controller.onViewCreated(CalculatorViewImpl(binding), lifecycle.asMviLifecycle())
    }

    override fun onStop() {
        controller.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        controller.onDestroy()
        super.onDestroy()
    }
}