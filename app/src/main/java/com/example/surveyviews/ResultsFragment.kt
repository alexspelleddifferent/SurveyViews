package com.example.surveyviews

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ResultsFragment : Fragment() {

    private lateinit var countsViewModel: CountsViewModel

    private lateinit var buttonReset: Button
    private lateinit var countLeft: TextView
    private lateinit var countRight: TextView
    private lateinit var buttonContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        countsViewModel = ViewModelProvider(requireActivity()).get(CountsViewModel::class.java)
        buttonReset = view.findViewById(R.id.reset_button)
        countLeft = view.findViewById(R.id.count_left)
        countRight = view.findViewById(R.id.count_right)
        buttonContinue = view.findViewById(R.id.continue_button)

        // update data on screen to the view model counts
        updateBoth()

        buttonReset.setOnClickListener {
            countsViewModel.reset()
            updateBoth()
        }

        buttonContinue.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }
    fun updateBoth() {
        countLeft.text = getString(R.string.left_count, countsViewModel.getLeftCount())
        countRight.text = getString(R.string.right_count, countsViewModel.getRightCount())
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            ResultsFragment().apply {
                arguments = Bundle().apply {  }
            }
    }
}