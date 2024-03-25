package com.example.surveyviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

enum class Fragments { SURVEY, RESULTS}

class MainActivity : AppCompatActivity() {

    private var currentFragment = Fragments.SURVEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragmentString = savedInstanceState?.getString(".CURRENT_FRAGMENT") ?: "SURVEY"
        currentFragment = Fragments.valueOf(currentFragmentString)

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
                requestKey, bundle ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ResultsFragment.newInstance(), "RESULTS")
                .addToBackStack("RESULTS")
                .commit()

            currentFragment = Fragments.RESULTS
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        currentFragment = Fragments.SURVEY
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(".CURRENT_FRAGMENT", currentFragment.toString())
    }
}