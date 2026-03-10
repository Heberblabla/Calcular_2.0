package com.waos.calculadora2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.widget.Switch

class Configuraciones : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_configuraciones)

        val s1 = findViewById<Switch>(R.id.switch1)
        val s2 = findViewById<Switch>(R.id.switch2)
        val s3 = findViewById<Switch>(R.id.switch3)
        val s4 = findViewById<Switch>(R.id.switch4)

        s1.setOnCheckedChangeListener { _, isChecked ->
            GlobalData.switch1 = isChecked
        }

        s2.setOnCheckedChangeListener { _, isChecked ->
            GlobalData.switch2 = isChecked
        }

        s3.setOnCheckedChangeListener { _, isChecked ->
            GlobalData.switch3 = isChecked
        }

        s4.setOnCheckedChangeListener { _, isChecked ->
            GlobalData.switch4 = isChecked
        }

    }
}