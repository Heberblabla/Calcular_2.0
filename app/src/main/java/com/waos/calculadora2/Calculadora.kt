package com.waos.calculadora2

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.text.DecimalFormat


class Calculadora : AppCompatActivity() {

    lateinit var tvOperacion: TextView
    lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Oculta las barras del sistema :v
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE


        //cajas de textos
        tvOperacion = findViewById(R.id.operacion)
        tvResultado = findViewById(R.id.resultado)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        MobileAds.initialize(this) {}

        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

    }

    fun presionarBoton(view: View) {
        val num2 = tvOperacion.text.toString()

        when (view.id) {
            R.id.borrarUno -> {
                if (num2.isNotEmpty()) {
                    tvOperacion.text = num2.dropLast(1)
                }
            }
            R.id.cero -> tvOperacion.text = num2 + "0"
            R.id.uno -> tvOperacion.text = num2 + "1"
            R.id.dos -> tvOperacion.text = num2 + "2"
            R.id.tres -> tvOperacion.text = num2 + "3"
            R.id.cuatro -> tvOperacion.text = num2 + "4"
            R.id.cinco -> tvOperacion.text = num2 + "5"
            R.id.seis -> tvOperacion.text = num2 + "6"
            R.id.siete -> tvOperacion.text = num2 + "7"
            R.id.ocho -> tvOperacion.text = num2 + "8"
            R.id.nueve -> tvOperacion.text = num2 + "9"
            R.id.puntito -> tvOperacion.text = num2 + "."
            R.id.sumar -> tvOperacion.text = num2 + "+"
            R.id.restar -> tvOperacion.text = num2 + "-"
            R.id.multiplcar -> tvOperacion.text = num2 + "*"
            R.id.dividiir -> tvOperacion.text = num2 + "/"
            R.id.borrar -> {
                tvOperacion.text = ""
                tvResultado.text = ""
            }  // limpiar todo
            R.id.igual -> {
                try {
                    val expresion = num2
                    var resultado1 = eval(expresion)
                    var resultado = formatResult(resultado1)

                    // lista de frases
                    val frases = listOf(
    "Es $resultado, no seas ciclero :v",
    "¡Gánate con el $resultado, mano!",
    "Manya, salió $resultado.",
    "Esa es, $resultado de ley.",
    "Pucha... sale $resultado, ¿estás bien?",
    "Oe, estudia pe', es $resultado.",

    // Nuevas
    "Sale $resultado pe causa, clarito nomás.",
    "¿Ves? $resultado, no era tan difícil pe.",
    "Ahí tá, $resultado. Más fácil que la tabla del 1.",
    "$resultado pues mano, la calculadora no miente.",
    "Toma tu $resultado y no llores después.",
    "$resultado, confirmado por la ciencia y la tía calculadora.",
    "Listo pe, $resultado. Siguiente ejercicio sin miedo.",
    "Ese $resultado está más fijo que combi en hora punta.",
    "$resultado nomás salió, no reclames al árbitro.",
    "Orgulloso estoy: $resultado 😌",
    "$resultado pe… y sin yapa ah."
)


                    // elegir una frase random
                    val fraseRandom = frases.random()

                    tvResultado.text = fraseRandom

                } catch (e: Exception) {
                    tvResultado.text = "Error"
                }
            }

        }
    }

    // Función simple para evaluar la expresión
    private fun eval(expresion: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expresion.length) expresion[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < expresion.length) throw RuntimeException("Caracter inesperado: " + ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    when {
                        eat('+'.code) -> x += parseTerm()
                        eat('-'.code) -> x -= parseTerm()
                        else -> return x
                    }
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    when {
                        eat('*'.code) -> x *= parseFactor()
                        eat('/'.code) -> x /= parseFactor()
                        else -> return x
                    }
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor()
                if (eat('-'.code)) return -parseFactor()

                var x: Double
                val startPos = pos
                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if ((ch in '0'.code..'9'.code) || ch == '.'.code) {
                    while ((ch in '0'.code..'9'.code) || ch == '.'.code) nextChar()
                    x = expresion.substring(startPos, pos).toDouble()
                } else {
                    throw RuntimeException("Caracter inesperado: " + ch.toChar())
                }
                return x
            }
        }.parse()
    }

    fun formatResult(value: Double): String {
        val df = DecimalFormat("#.##")
        df.maximumFractionDigits = 2
        df.minimumFractionDigits = 0
        df.isGroupingUsed = false
        return df.format(value)
    }

}
