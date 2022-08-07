package me.dio.business_card.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import me.dio.business_card.App
import me.dio.business_card.R
import me.dio.business_card.data.Card
import me.dio.business_card.databinding.ActivityAddBusinessCardBinding

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBusinessCardBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBusinessCardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        insertListeners()


    }


    private fun insertListeners() {
        binding.colorBtn.setOnClickListener {
            colorDialog()
        }

        binding.ibSave.setOnClickListener {
            val nome = binding.mtNome.editText?.text.toString()
            val telefone = binding.mtTelefone.editText?.text.toString()
            val email = binding.mtEmail.editText?.text.toString()
            val empresa = binding.mtEmpresa.editText?.text.toString()
            val button = binding.color.text.toString()

            val card = Card(
                nome = nome,
                telefone = telefone,
                email = email,
                empresa = empresa,
                button = button,
            )
            if (card.nome.isEmpty() || card.telefone.isEmpty() || card.email.isEmpty() || card.empresa.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.fill_all_fields_warning),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mainViewModel.insertCard(card)
                Toast.makeText(this, getString(R.string.card_added_success), Toast.LENGTH_SHORT)
                    .show()

                finish()
            }

        }


    }

    private fun colorDialog() {
        val colors = arrayOf(
            "#FF7F50", "#FF69B4", "#ADFF2F", "#8B008B", "#4B0082", "#FFA500", "#FFD700", "#FFFF00",
            "#00FF00", "#008000", "#00FFFF", "#00BFFF", "#0000FF", "#FF00FF", "#FF0000", "#8B0000",
        )

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose a color")
            .setItems(colors) { _, which ->
                binding.color.text = colors[which]
                binding.colorBtn.setColorFilter(
                    android.graphics.Color.parseColor(colors[which])
                )
            }
            .create().show()

    }
}
