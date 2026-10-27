package com.genove.learning

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/** Écran temporaire pour les onglets pas encore développés. */
class PlaceholderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        return TextView(requireContext()).apply {
            text = arguments?.getString(ARG_TITLE).orEmpty() + "\n(à venir)"
            gravity = Gravity.CENTER
            textSize = 18f
            setTextColor(resources.getColor(R.color.gl_text_secondary, null))
        }
    }

    companion object {
        private const val ARG_TITLE = "title"
        fun newInstance(title: String) = PlaceholderFragment().apply {
            arguments = Bundle().apply { putString(ARG_TITLE, title) }
        }
    }
}
