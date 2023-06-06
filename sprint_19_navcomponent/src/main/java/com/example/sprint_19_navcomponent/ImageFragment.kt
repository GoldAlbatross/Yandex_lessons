package com.example.sprint_19_navcomponent

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ImageFragment : Fragment(R.layout.fragment_image) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goToStartButton = activity?.findViewById<Button>(R.id.goToStartButton)!!
        val back = activity?.findViewById<ImageView>(R.id.back)!!
        val image = activity?.findViewById<ImageView>(R.id.image)!!

        goToStartButton.setOnClickListener {
            findNavController().popBackStack(R.id.startFragment, true)
        }

        back.setOnClickListener {
            Log.d("qqq","+++back")
            findNavController().navigateUp()
        }

        image.setImageResource(arguments?.getInt(ARGS_IMAGE) ?: 0)
        image.animation = AnimationUtils.loadAnimation(context, R.anim.anim)
        image.animate()

    }

    companion object {
        private const val ARGS_IMAGE = "image"
        fun createArgs(imageResId: Int): Bundle =
            bundleOf(ARGS_IMAGE to imageResId)
    }
}