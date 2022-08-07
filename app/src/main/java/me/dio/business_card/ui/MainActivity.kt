package me.dio.business_card.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import me.dio.business_card.App
import me.dio.business_card.R
import me.dio.business_card.databinding.ActivityMainBinding
import me.dio.business_card.util.Image

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val mainAdapter: BusinessCardAdapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvCards.adapter = mainAdapter
        getAllBusinessCards()
        insertListeners()


    }

    private fun getAllBusinessCards() {
            mainViewModel.getAll().observe(this) { cardsList ->
            mainAdapter.submitList(cardsList)
        }
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }

        mainAdapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
       mainAdapter.listenerDelete = { card ->
            mainViewModel.delete(card)
            Toast.makeText(this@MainActivity, getString(R.string.card_deleted_label), Toast.LENGTH_SHORT).show()
        }
    }


}