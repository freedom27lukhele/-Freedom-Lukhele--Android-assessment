package com.glucode.about_you.about.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewQuestionCardBinding

class QuestionCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding: ViewQuestionCardBinding =
        ViewQuestionCardBinding.inflate(LayoutInflater.from(context), this)

    var title: String? = null
        set(value) {
            field = value
            binding.title.text = value
            binding.title.setTextColor(ContextCompat.getColor(context, R.color.white))
        }

    var answers: List<String> = listOf()
        set(value) {
            field = value
            binding.answers.removeAllViews()
            value.forEach { answer ->
                addAnswer(answer)
            }
        }

    var selection: Int? = null
        set(value) {
            field = value
            value ?: return
            binding.answers.children.elementAt(value).isSelected = true
        }

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }

    private fun addAnswer(title: String) {
        val answerView = AnswerCardView(context)
        answerView.title = title
        answerView.setOnClickListener { onAnswerClick(it) }
        binding.answers.addView(answerView)
    }

    private fun onAnswerClick(view: View) {
        if (!view.isSelected) {
            binding.answers.children.filter { it.isSelected }.forEach {
                it.isSelected = false
            }

            view.isSelected = true

            selection = binding.answers.children.indexOf(view)
            setSelection()
        }
    }
/*
* This set selection process first checks for any nulls
* then  clears all the elections before it sets a view you have chosen
*/
    private fun setSelection() {
        if (selection != null) {
            val index = selection

            if (index != null) {
                if (index >= 0 && index < binding.answers.childCount) {
                    for (i in 0 until binding.answers.childCount) {
                        binding.answers.getChildAt(i).isSelected = false
                    }
                    binding.answers.getChildAt(index).isSelected = true
                }
            }
        }
    }
}