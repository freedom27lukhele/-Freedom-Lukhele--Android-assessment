package com.glucode.about_you.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.ProfileCardView
import com.glucode.about_you.mockdata.MockData

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private var profileCardView: ProfileCardView? = null
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { imageUri ->
            // Update the image in ProfileCardView
            profileCardView?.let { cardView ->

                Glide.with(requireContext()).load(imageUri).into(cardView.profileImage)

                // Save the selected image URI to share between fragments
                val engineerName = arguments?.getString("name")
                engineerName?.let { name ->
                    // Find the engineer and update their image URI
                    val engineer = MockData.engineers.find { it.name == name }
                    engineer?.let {
                        // Save URI as string to the engineer object
                        it.defaultImageName = imageUri.toString()
                    }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpProfile()
        setUpQuestions()
    }

    private fun setUpQuestions() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.first { it.name == engineerName }

        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }

    private fun setUpProfile() {
        val engineerName = arguments?.getString("name")
        val engineer = MockData.engineers.first { it.name == engineerName }

        val profileCardView = ProfileCardView(requireContext()).apply {
            setProfileImage(engineer.defaultImageName)
            setName(engineer.name)
            setRole(engineer.role)
            setStats(
                engineer.quickStats.coffees,
                engineer.quickStats.years,
                engineer.quickStats.bugs
            )

            profileImage.setOnClickListener {
                pickImageLauncher.launch("image/*")
            }
        }
        binding.container.addView(profileCardView, 0)

    }


}