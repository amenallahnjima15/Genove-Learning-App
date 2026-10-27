package com.genove.learning

import androidx.annotation.DrawableRes

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val level: String,
    val hours: Int,
    @DrawableRes val imageRes: Int,
    val rating: Float = 4.8f,
    val reviewsCount: Int = 1200,
    val instructor: String = "Genove Expert",
    val keywords: List<String> = emptyList()
)

object DemoData {
    const val ALL = "Toutes"
    const val DEV = "Développement"
    const val AI = "Intelligence artificielle"
    const val DATA = "Data & BI"
    const val BUSINESS = "Business"

    val courses = listOf(
        Course(
            id = 1,
            title = "Développement Android avec Kotlin",
            description = "Créez des applications Android natives modernes avec Kotlin, XML Views et Material Design.",
            category = DEV,
            level = "Intermédiaire",
            hours = 32,
            imageRes = R.drawable.img_course_android,
            rating = 4.9f,
            reviewsCount = 2150,
            instructor = "Alex Martin",
            keywords = listOf("android", "kotlin", "mobile", "app", "dev")
        ),
        Course(
            id = 2,
            title = "Développement Web avec React",
            description = "Construisez des interfaces web performantes et modernes avec React, TypeScript et Tailwind.",
            category = DEV,
            level = "Intermédiaire",
            hours = 28,
            imageRes = R.drawable.img_course_react,
            rating = 4.8f,
            reviewsCount = 1840,
            instructor = "Sarah Benali",
            keywords = listOf("web", "react", "javascript", "frontend")
        ),
        Course(
            id = 3,
            title = "Introduction à l'intelligence artificielle",
            description = "Comprendre les réseaux de neurones, le Deep Learning, les LLMs et les cas d'usage réels.",
            category = AI,
            level = "Débutant",
            hours = 12,
            imageRes = R.drawable.img_course_ai,
            rating = 4.9f,
            reviewsCount = 3200,
            instructor = "Dr. Karim Trabelsi",
            keywords = listOf("ia", "ai", "intelligence artificielle", "deep learning")
        ),
        Course(
            id = 4,
            title = "Data Analytics avec Power BI",
            description = "Analysez, modélisez et visualisez vos flux de données avec des dashboards Power BI percutants.",
            category = DATA,
            level = "Intermédiaire",
            hours = 20,
            imageRes = R.drawable.img_course_powerbi,
            rating = 4.7f,
            reviewsCount = 980,
            instructor = "Elena Rostova",
            keywords = listOf("power bi", "dashboard", "analytics", "data", "bi")
        ),
        Course(
            id = 5,
            title = "Bases de données & Requêtes SQL",
            description = "Maîtrisez les requêtes complexes, les jointures, les indexations et l'architecture relationnelle.",
            category = DATA,
            level = "Débutant",
            hours = 18,
            imageRes = R.drawable.img_course_sql,
            rating = 4.8f,
            reviewsCount = 1420,
            instructor = "Mehdi Gharbi",
            keywords = listOf("sql", "database", "bdd", "postgres", "mysql")
        ),
        Course(
            id = 6,
            title = "Python pour les débutants",
            description = "Apprenez la programmation de A à Z avec Python : variables, boucles, fonctions et POO.",
            category = DEV,
            level = "Débutant",
            hours = 16,
            imageRes = R.drawable.img_course_python,
            rating = 4.9f,
            reviewsCount = 4100,
            instructor = "Thomas Dubois",
            keywords = listOf("python", "programmation", "coding", "debutant")
        ),
        Course(
            id = 7,
            title = "Machine Learning : Fondamentaux",
            description = "Les algorithmes fondamentaux du machine learning supervisé, non supervisé et validation de modèles.",
            category = AI,
            level = "Avancé",
            hours = 30,
            imageRes = R.drawable.img_course_ml,
            rating = 4.9f,
            reviewsCount = 2600,
            instructor = "Dr. Sonia Mansour",
            keywords = listOf("ml", "machine learning", "ia", "algorithmes")
        ),
        Course(
            id = 8,
            title = "Business Intelligence & Stratégie",
            description = "Concevez une démarche décisionnelle d'entreprise de la collecte d'indicateurs au pilotage stratégique.",
            category = BUSINESS,
            level = "Intermédiaire",
            hours = 22,
            imageRes = R.drawable.img_course_bi,
            rating = 4.7f,
            reviewsCount = 870,
            instructor = "Yassine Ayari",
            keywords = listOf("bi", "business", "décisionnel", "strategie", "kpi")
        )
    )

    fun byId(id: Int) = courses.firstOrNull { it.id == id }
}
