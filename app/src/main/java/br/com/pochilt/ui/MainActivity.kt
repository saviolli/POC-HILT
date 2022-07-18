package br.com.pochilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.pochilt.R
import br.com.pochilt.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observe()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.init()
    }

    private fun observe() {
        lifecycleScope.launch {
            mainViewModel.screen.collect {
                when(it) {
                    is MainViewModel.Screen.UpdateTitle -> {
                        findViewById<TextView>(R.id.tv_title).text = it.title
                    }
                }
            }
        }
    }
}