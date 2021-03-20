package br.com.borserog.listadedesejos.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.borserog.listadedesejos.R
import br.com.borserog.listadedesejos.models.Desejo

class ItemFormActivity : AppCompatActivity() {
    private lateinit var btnCancelar: Button
    private lateinit var btnCadastrar: Button
    private lateinit var textDescricao: EditText
    private lateinit var textValor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_form)

        this.init();


        this.btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        this.btnCadastrar.setOnClickListener { onCadastrarClicked(it) }


    }

    private fun onCadastrarClicked(view: View?) {
        val desejo = Desejo(textDescricao.text.toString(), textValor.text.toString().toFloat())

        val returnIntent = Intent()
        returnIntent.putExtra("desejo_data", desejo)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    private fun init() {
        this.btnCancelar = findViewById(R.id.btnCancelar)
        this.textDescricao = findViewById(R.id.textDescricao)
        this.textValor = findViewById(R.id.textValor)
        this.btnCadastrar = findViewById(R.id.btnCadastrar)
    }
}