package com.example.surveyviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

enum class Fragments { SURVEY, RESULTS}

class MainActivity : AppCompatActivity() {

    private var currentFragment = Fragments.SURVEY
    // the two fragments that will be used in the program

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragmentString = savedInstanceState?.getString(".CURRENT_FRAGMENT") ?: "SURVEY"
        currentFragment = Fragments.valueOf(currentFragmentString)
        // figures out which is the current fragment

        val surveyFragment = SurveyFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, surveyFragment, "SURVEY")
            .commit()

        if (currentFragment == Fragments.RESULTS) {
            val resultsFragment = ResultsFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, resultsFragment, "RESULTS")
                .commit()
        }

        supportFragmentManager.setFragmentResultListener("COUNTS", this) {
            // awaits result from the results fragment. this was how i initially intended to move data
            // keeping it in case i ever decided to rework it to this way
                requestKey, bundle ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ResultsFragment.newInstance(), "RESULTS")
                .addToBackStack("RESULTS")
                .commit()

            currentFragment = Fragments.RESULTS
        }
    }

    override fun onBackPressed() {
        // makes sure that the survey fragment is loaded when the user backs from the results fragment
        super.onBackPressed()
        currentFragment = Fragments.SURVEY
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(".CURRENT_FRAGMENT", currentFragment.toString())
    }
}