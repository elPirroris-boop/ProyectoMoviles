package com.example.gastos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class AgregarGastoActivity : AppCompatActivity() {

    private lateinit var db: GastosDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_gasto)

        db = GastosDatabase.getDatabase(this)

        val etNombre = findViewById<TextInputEditText>(R.id.etNombre)
        val etMonto = findViewById<TextInputEditText>(R.id.etMonto)
        val spinnerCategoria = findViewById<Spinner>(R.id.spinnerCategoria)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val categorias = listOf("Comida", "Transporte", "Entretenimiento", "Otros")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategoria.adapter = spinnerAdapter

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val montoTexto = etMonto.text.toString().trim()
            val categoria = spinnerCategoria.selectedItem.toString()

            if (nombre.isEmpty() || montoTexto.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val monto = montoTexto.toDoubleOrNull()
            if (monto == null) {
                Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                db.gastoDao().insertar(Gasto(nombre = nombre, monto = monto, categoria = categoria))
                finish()
            }
        }
    }
}