package com.genove.learning

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.genove.learning.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment() {
    private var _b: FragmentCatalogBinding? = null
    private val b get() = _b!!

    private val adapter = CourseAdapter { course ->
        startActivity(
            Intent(requireContext(), CourseDetailActivity::class.java)
                .putExtra(CourseDetailActivity.EXTRA_COURSE_ID, course.id)
        )
    }
    private var category = DemoData.ALL
    private var query = ""

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _b = FragmentCatalogBinding.inflate(i, c, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        b.tvGreeting.text = getString(R.string.catalog_greeting, "Amen")
        b.rvCourses.adapter = adapter

        // Redirection vers le profil
        b.btnProfileInfo.setOnClickListener {
            (activity as? MainActivity)?.navigateToProfile()
        }

        b.etSearch.doAfterTextChanged {
            query = it?.toString().orEmpty().trim()
            applyFilters()
        }

        b.chipGroupCategories.setOnCheckedStateChangeListener { _, ids ->
            category = when (ids.firstOrNull()) {
                R.id.chipDev -> DemoData.DEV
                R.id.chipAi -> DemoData.AI
                R.id.chipData -> DemoData.DATA
                R.id.chipBusiness -> DemoData.BUSINESS
                else -> DemoData.ALL
            }
            applyFilters()
        }

        // Le bouton filtre remet à zéro recherche + catégorie
        b.btnFilter.setOnClickListener {
            b.etSearch.setText("")
            b.chipGroupCategories.check(R.id.chipAll)
            Toast.makeText(requireContext(), "Filtres réinitialisés", Toast.LENGTH_SHORT).show()
        }
        b.tvSeeAll.setOnClickListener { b.chipGroupCategories.check(R.id.chipAll) }

        applyFilters()
    }

    private fun applyFilters() {
        val q = query.lowercase()
        val list = DemoData.courses.filter { c ->
            (category == DemoData.ALL || c.category == category) &&
                (q.isEmpty() || c.title.lowercase().contains(q) ||
                    c.description.lowercase().contains(q) ||
                    c.keywords.any { it.contains(q) })
        }
        adapter.submitList(list)

        // Show/hide empty state container
        b.emptyContainer.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE

        // Update section title with live count
        b.tvSectionTitle.text = if (category == DemoData.ALL) {
            "Catalogue des formations (${list.size})"
        } else {
            "$category (${list.size})"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
