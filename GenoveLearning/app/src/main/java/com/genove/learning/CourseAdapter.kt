package com.genove.learning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genove.learning.databinding.ItemCourseBinding
import java.util.Locale

class CourseAdapter(private val onClick: (Course) -> Unit) :
    ListAdapter<Course, CourseAdapter.VH>(Diff) {

    inner class VH(val b: ItemCourseBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = getItem(position)
        val ctx = holder.b.root.context

        // Set course banner image
        holder.b.imgCourse.setImageResource(c.imageRes)

        // Set textual information
        holder.b.tvTitle.text = c.title
        holder.b.tvBadge.text = c.level
        holder.b.tvSubtitle.text = "Par ${c.instructor} · ${c.category}"
        holder.b.tvDuration.text = ctx.getString(R.string.course_duration_hours, c.hours)

        // Format rating (e.g. "4.9 (2.1k)")
        val reviewDisplay = if (c.reviewsCount >= 1000) {
            String.format(Locale.US, "%.1fk", c.reviewsCount / 1000f)
        } else {
            c.reviewsCount.toString()
        }
        holder.b.tvRating.text = String.format(Locale.US, "%.1f (%s)", c.rating, reviewDisplay)

        holder.b.root.setOnClickListener { onClick(c) }
    }

    object Diff : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(a: Course, b: Course) = a.id == b.id
        override fun areContentsTheSame(a: Course, b: Course) = a == b
    }
}
