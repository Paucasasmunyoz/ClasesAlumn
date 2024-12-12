package com.pcmdam.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val clases = listOf("1rEso", "2nEso", "3rEso", "4rtESO")
    private val alumnosPorClase = mutableMapOf<String, MutableList<String>>(
        "1rEso" to mutableListOf(),
        "2nEso" to mutableListOf(),
        "3rEso" to mutableListOf(),
        "4rtESO" to mutableListOf()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val spinnerClase = findViewById<Spinner>(R.id.spinnerClase)
        val buttonAddAlumno = findViewById<Button>(R.id.buttonAddAlumno)
        val buttonIniciarSesion = findViewById<Button>(R.id.buttonIniciarSesion)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, clases)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClase.adapter = adapter

        buttonAddAlumno.setOnClickListener {
            val nombre = editTextName.text.toString().trim()
            val edad = editTextAge.text.toString().trim()
            val claseSeleccionada = spinnerClase.selectedItem.toString()

            if (nombre.isEmpty() || edad.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa nombre y edad", Toast.LENGTH_SHORT).show()
            } else {
                val alumnoConEdad = "$nombre ($edad años)"
                alumnosPorClase[claseSeleccionada]?.add(alumnoConEdad)
                Toast.makeText(this, "Alumno añadido a $claseSeleccionada", Toast.LENGTH_SHORT).show()
                editTextName.text.clear()
                editTextAge.text.clear()
            }
        }

        buttonIniciarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("alumnos_por_clase", HashMap(alumnosPorClase))
            }
            startActivity(intent)
        }
    }
}
