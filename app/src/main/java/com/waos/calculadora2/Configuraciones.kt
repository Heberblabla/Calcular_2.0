package com.waos.calculadora2

import android.content.Intent
import android.os.Bundle
import android.view.View
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
        // Oculta las barras del sistema :v
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        val s1 = findViewById<Switch>(R.id.switch1)
        val s2 = findViewById<Switch>(R.id.switch2)
        val s3 = findViewById<Switch>(R.id.switch3)
        val s4 = findViewById<Switch>(R.id.switch4)

        // Leer el estado desde GlobalData
        s1.isChecked = GlobalData.switch1
        s2.isChecked = GlobalData.switch2
        s3.isChecked = GlobalData.switch3
        s4.isChecked = GlobalData.switch4

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


    fun configuraciones(view: View){
        finish()
    }


}