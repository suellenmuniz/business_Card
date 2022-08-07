package me.dio.business_card.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import me.dio.business_card.App
import me.dio.business_card.R
import me.dio.business_card.data.BusinessCard
import me.dio.business_card.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }
    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.btnConfirm.text.toString()


            )
            if (businessCard.nome.isEmpty() || businessCard.telefone.isEmpty() || businessCard.email.isEmpty() || businessCard.empresa.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.label_business_card),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
            mainViewModel.insertCard(businessCard)
            Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
            finish()
        }

    }
    }

private fun colorPicked() {
    val colors = arrayOf(
        "#FF7F50", "#FF69B4", "#ADFF2F", "#8B008B", "#4B0082", "#FFA500", "#FFD700", "#FFFF00",
        "#00FF00", "#008000", "#00FFFF", "#00BFFF", "#0000FF", "#FF00FF", "#FF0000", "#8B0000",
    )

    val builder = AlertDialog.Builder(this)
    builder.setTitle("Choose a color")
        .setItems(colors) { _, which ->
            binding.btnConfirm.text = colors[which]
            binding.btnClose.setColorFilter(
                android.graphics.Color.parseColor(colors[which])
            )
        }
        .create().show()
}

}
