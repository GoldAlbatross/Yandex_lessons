package com.example.sprint_19_navcomponent

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment(R.layout.fragment_start) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catButton = activity?.findViewById<Button>(R.id.catButton)!!
        val hamsterButton = activity?.findViewById<Button>(R.id.hamsterButton)!!

        catButton.setOnClickListener {
            Log.d("qqq","+++cat_fact")
            findNavController().navigate(
                resId = R.id.action_startFragment_to_factFragment,
                args = FactFragment.createArgs(getString(R.string.cat_fact)),
            )
        }

        hamsterButton.setOnClickListener {
            Log.d("qqq","+++hamster_fact")
            findNavController().navigate(
                resId = R.id.action_startFragment_to_factFragment,
                args = FactFragment.createArgs(getString(R.string.hamster_fact)),
            )
        }
    }
}