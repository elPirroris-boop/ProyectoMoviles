package com.example.gastos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: GastoAdapter
    private lateinit var db: GastosDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = GastosDatabase.getDatabase(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewGastos)
        val fab = findViewById<FloatingActionButton>(R.id.fabAgregar)

        adapter = GastoAdapter(emptyList()) { gasto ->
            lifecycleScope.launch {
                db.gastoDao().eliminar(gasto)
                cargarGastos()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            startActivity(Intent(this, AgregarGastoActivity::class.java))
        }

        cargarGastos()
    }

    override fun onResume() {
        super.onResume()
        cargarGastos()
    }

    private fun cargarGastos() {
        lifecycleScope.launch {
            val gastos = db.gastoDao().obtenerTodos()
            adapter.actualizar(gastos)
        }
    }
}