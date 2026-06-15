package com.example.gastos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query

@Dao
interface GastoDao {
    @Insert
    suspend fun insertar(gasto: Gasto)

    @Delete
    suspend fun eliminar(gasto: Gasto)

    @Query("SELECT * FROM gastos")
    suspend fun obtenerTodos(): List<Gasto>
}