package br.com.borserog.listadedesejos.activities

import android.app.Activity
import android.content.Intent
import android.icu.number.Notation.simple
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import br.com.borserog.listadedesejos.R
import br.com.borserog.listadedesejos.models.Desejo
import com.google.android.material.floatingactionbutton.FloatingActionButton


const val LAUNCH_FORM = 1;
class MainActivity : AppCompatActivity() {
    private lateinit var fabBtn: FloatingActionButton
    private lateinit var listaDesejosRef: ListView
    private lateinit var listaDesejos: ArrayList<Desejo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaDesejos = arrayListOf()

        this.fabBtn = findViewById(R.id.fabBtn)
        this.listaDesejosRef = findViewById(R.id.listaDesejosRef)

        this.listaDesejosRef.adapter = ArrayAdapter<Desejo>(
            this,
            android.R.layout.simple_list_item_1,
            this.listaDesejos
        )

        fabBtn.setOnClickListener { navigateToForm(it) }
    }

    private fun navigateToForm(mainView: View?) {
        val intent = Intent(this, ItemFormActivity::class.java)

        startActivityForResult(intent, LAUNCH_FORM)
    }

    private fun addDesejo(desejo: Desejo) {
        (this.listaDesejosRef.adapter as ArrayAdapter<String>).add(desejo.toString());
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_FORM) {
            if (resultCode == Activity.RESULT_OK) {
                val desejo = data?.getSerializableExtra("desejo_data") as Desejo?

                Log.i("DEV", desejo.toString());

                if (desejo != null) {
                    addDesejo(desejo);
                    Toast.makeText(this, "Desejo Cadastrado", Toast.LENGTH_SHORT).show()
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelou por quê? Condenado...", Toast.LENGTH_SHORT).show()

            }
        }

    }
}