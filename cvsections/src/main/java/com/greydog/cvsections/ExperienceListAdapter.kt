package com.greydog.cvsections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.greydog.cvsections.databinding.ExperienceItemBinding
import com.greydog.datamodels.Experience

class ExperienceListAdapter: RecyclerView.Adapter<ExperienceListAdapter.ViewHolder>() {
    private var experienceList: List<Experience> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ExperienceItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.experience_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(experienceList[position])
    }

    override fun getItemCount(): Int {
        return experienceList.size
    }

    fun updateList(experienceList: List<Experience>) {
        this.experienceList = experienceList

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ExperienceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ExperienceItemViewModel()

        fun bind(experience: Experience) {
            viewModel.company.value = experience.company
            viewModel.title.value = experience.title
            viewModel.description.value = experience.description
            viewModel.location.value = experience.location
            viewModel.period.value = experience.period
            viewModel.status.value = experience.status
            binding.viewModel = viewModel
        }
    }
}