package com.waos.calculadora2

object GlobalData {

    var switch1 = false
    var switch2 = false
    var switch3 = false
    var switch4 = false

    // Default
    val frasesDefault = listOf(
        "Es %s.",
        "El resultado es %s.",
        "Sale %s.",
        "%s nomás salió."
    )

    // Especiales
    val frasesEspeciales = listOf(
        "Es %s, no seas ciclero :v",
        "Manya, salió %s.",
        "Esa es, %s de ley.",
        "Pucha... sale %s, ¿está bien?"
    )

    // Provinciano
    val frasesProvinciano = listOf(
        "Sale %s pe causa.",
        "%s nomás pe.",
        "Oe, es %s ps.",
        "Mira pe, salió %s."
    )

    // Llamas
    val frasesLlamas = listOf(
        "La llama matemática confirma: %s 🦙",
        "La llama calculó y dice: %s",
        "El consejo supremo de llamas aprobó %s"
    )

    // Void
    val frasesVoid = listOf(
        "El vacío responde: %s",
        "Desde el void llega el número %s",
        "Nada existe… excepto %s"
    )

    fun obtenerFrasesActivas(): List<String> {
        val lista = mutableListOf<String>()

        lista.addAll(frasesDefault)

        if (switch1) lista.addAll(frasesEspeciales)
        if (switch2) lista.addAll(frasesProvinciano)
        if (switch3) lista.addAll(frasesLlamas)
        if (switch4) lista.addAll(frasesVoid)

        return lista
    }
}