package com.genove.learning

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/** Écran de détail minimal (à remplacer par le vrai design). */
class CourseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val course = DemoData.byId(intent.getIntExtra(EXTRA_COURSE_ID, -1))
        val pad = (24 * resources.displayMetrics.density).toInt()
        setContentView(TextView(this).apply {
            setPadding(pad, pad * 2, pad, pad)
            textSize = 16f
            text = course?.let { "${it.title}\n\n${it.description}\n\n${it.category} · ${it.level} · ${it.hours} h" }
                ?: "Formation introuvable"
        })
    }

    companion object { const val EXTRA_COURSE_ID = "course_id" }
}
