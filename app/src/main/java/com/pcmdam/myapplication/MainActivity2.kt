package com.pcmdam.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var alumnosPorClase: Map<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val spinnerClase = findViewById<Spinner>(R.id.spinnerClase)
        val listViewAlumnos = findViewById<ListView>(R.id.listViewAlumnos)
        val buttonRegresar = findViewById<Button>(R.id.buttonRegresar)

        alumnosPorClase = intent.getSerializableExtra("alumnos_por_clase") as Map<String, List<String>>

        val clases = alumnosPorClase.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, clases)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClase.adapter = adapter

        spinnerClase.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                val claseSeleccionada = clases[position]
                val alumnos = alumnosPorClase[claseSeleccionada] ?: listOf("No hay alumnos")
                val listAdapter = ArrayAdapter(
                    this@MainActivity2,
                    android.R.layout.simple_list_item_1,
                    alumnos
                )
                listViewAlumnos.adapter = listAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        buttonRegresar.setOnClickListener {
            finish()
        }
    }
}
