package com.example.surveyviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class SurveyFragment : Fragment() {

    private lateinit var buttonLeft: Button
    private lateinit var buttonRight: Button
    private lateinit var buttonResults: Button

    private lateinit var countsViewModel: CountsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        // this is the main fragment creator. it creates a view object with the elements that we want from the xml
        val view = inflater.inflate(R.layout.fragment_survey, container, false)
        countsViewModel = ViewModelProvider(requireActivity()).get(CountsViewModel::class.java)
        buttonLeft = view.findViewById(R.id.button_left)
        buttonRight = view.findViewById(R.id.button_right)
        buttonResults = view.findViewById(R.id.results_button)

        buttonLeft.setOnClickListener {
            countsViewModel.updateLeft()
        }

        buttonRight.setOnClickListener {
            countsViewModel.updateRight()
        }

        buttonResults.setOnClickListener {
            goToResults()
        }
        return view
    }
    private fun goToResults() {
        val counts = listOf<Int>(countsViewModel.getLeftCount(), countsViewModel.getRightCount())
        parentFragmentManager.setFragmentResult("COUNTS", Bundle.EMPTY)
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            SurveyFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}