package pe.edu.ulima.aprendiendo.providers

import pe.edu.ulima.aprendiendo.models.views.QuoteModel

class QuoteProvider {
    companion object {
        private val quotes = listOf<QuoteModel>(
            QuoteModel("No hay que ir para atrás ni para darse impulso", "Lao Tsé"),
            QuoteModel("No hay caminos para la paz; la paz es el camino", "Mahatma Gandhi"),
            QuoteModel("Haz el amor y no la guerra", "John Lennon"),
            QuoteModel("Para trabajar basta estar convencido de una cosa: que trabajar es menos aburrido que divertirse", "Charles Baudelaire"),
            QuoteModel("Lo peor que hacen los malos es obligarnos a dudar de los buenos ", "Jacinto Benavente"),
        )

        fun random(): QuoteModel {
            val position: Int = (0..4).random()
            return quotes[position]
        }
    }
}