package com.greydog.cvapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.greydog.cvapp.databinding.MenuFragmentBinding
import com.greydog.extensions.inflateBinding
import kotlinx.android.synthetic.main.menu_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {

    private val viewModel: MenuViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflateBinding<MenuFragmentBinding>(R.layout.menu_fragment, container) { it.vm = viewModel }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headline.setOnClickListener {
            it.findNavController().navigate(R.id.action_menufragment_to_headlinefragment)
        }

        experience.setOnClickListener {
            it.findNavController().navigate(R.id.action_menufragment_to_experiencefragment)
        }

        contactinfo.setOnClickListener {
            it.findNavController().navigate(R.id.action_menufragment_to_contactinfofragment)
        }
    }
}