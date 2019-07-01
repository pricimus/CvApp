package com.greydog.cvsections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.greydog.cvsections.databinding.ExperienceFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import com.greydog.extensions.inflateBinding
import kotlinx.android.synthetic.main.experience_fragment.*

class ExperienceFragment : Fragment() {

    private val viewModel: ExperienceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflateBinding<ExperienceFragmentBinding>(R.layout.experience_fragment, container) { it.vm = viewModel }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val experienceAdapter = ExperienceListAdapter()
        experiencelist.adapter = experienceAdapter
        experiencelist.layoutManager = LinearLayoutManager(requireContext())

        viewModel.experience.observe(viewLifecycleOwner, Observer {
            experienceAdapter.updateList(it)
        })
    }
}