package com.matensi.runrungo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matensi.runrungo.R
import com.matensi.runrungo.databinding.FragmentMainBinding
import com.matensi.runrungo.databinding.ViewTrackBinding

class ViewTrackFragment : Fragment() {
    private lateinit var binding: ViewTrackBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewTrackFragment()


    }
}