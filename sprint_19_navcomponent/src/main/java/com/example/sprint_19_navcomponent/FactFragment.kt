package com.example.sprint_19_navcomponent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FactFragment : Fragment(R.layout.fragment_fact) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = arguments?.getString(ARGS_FACT) ?: ""

        val fact = activity?.findViewById<TextView>(R.id.fact)!!
        val imageButton = activity?.findViewById<Button>(R.id.imageButton)!!
        val back = activity?.findViewById<ImageView>(R.id.back)!!

        fact.text = text

        val imageId =
            if (text == getString(R.string.cat_fact)) {
                Log.d("qqq","+++cat")
                R.drawable.cat
            }
            else {
                Log.d("qqq","+++hamster")
                R.drawable.hamster
            }

        imageButton.setOnClickListener {
            findNavController().navigate(
                resId = R.id.action_factFragment_to_imageFragment,
                args = ImageFragment.createArgs(imageId),
            )
        }

        back.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    companion object {
        const val ARGS_FACT = "fact"
        fun createArgs(fact: String): Bundle =
            bundleOf(ARGS_FACT to fact)
    }
}