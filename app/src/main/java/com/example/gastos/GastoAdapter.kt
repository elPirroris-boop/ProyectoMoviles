package com.example.gastos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GastoAdapter(
    private var gastos: List<Gasto>,
    private val onEliminar: (Gasto) -> Unit
) : RecyclerView.Adapter<GastoAdapter.GastoViewHolder>() {

    class GastoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvCategoria: TextView = itemView.findViewById(R.id.tvCategoria)
        val tvMonto: TextView = itemView.findViewById(R.id.tvMonto)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gasto, parent, false)
        return GastoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GastoViewHolder, position: Int) {
        val gasto = gastos[position]
        holder.tvNombre.text = gasto.nombre
        holder.tvCategoria.text = gasto.categoria
        holder.tvMonto.text = "$${gasto.monto}"
        holder.btnEliminar.setOnClickListener { onEliminar(gasto) }
    }

    override fun getItemCount(): Int = gastos.size

    fun actualizar(nuevosGastos: List<Gasto>) {
        gastos = nuevosGastos
        notifyDataSetChanged()
    }
}