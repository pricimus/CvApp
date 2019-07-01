package com.greydog.cvsections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greydog.cvsections.databinding.ContactinfoFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import com.greydog.extensions.inflateBinding

class ContactInfoFragment : Fragment() {

    private val viewModel: ContactInfoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflateBinding<ContactinfoFragmentBinding>(R.layout.contactinfo_fragment, container) { it.vm = viewModel }
    }
}