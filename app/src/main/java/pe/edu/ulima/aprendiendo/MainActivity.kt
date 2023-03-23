package pe.edu.ulima.aprendiendo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import pe.edu.ulima.aprendiendo.databinding.ActivityMainBinding
import pe.edu.ulima.aprendiendo.model.QuoteModel
import pe.edu.ulima.aprendiendo.viewmodel.QuoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.quoteModel.observe(this, Observer { currentQoute ->
            binding.tvQuote.text = currentQoute.quote
            binding.tvAuthor.text = currentQoute.author
        })

        binding.viewContainer.setOnClickListener {
            Log.d("click", "+++++++++++++++++++")
            quoteViewModel.randomQuote()
        }
    }
}