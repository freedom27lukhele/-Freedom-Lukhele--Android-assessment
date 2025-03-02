package com.glucode.about_you.engineers

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ProfileViewBinding

class ProfileCardView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ProfileViewBinding

    val profileImage: ImageView
    private val nameTextView: TextView
    private val roleTextView: TextView
    private val coffeeTextView: TextView
    private val yearsTextView: TextView
    private val bugsTextView: TextView


    init {

        val inflater = LayoutInflater.from(context)
        binding = ProfileViewBinding.inflate(inflater, this, true)

        profileImage = binding.profileImage
        nameTextView = binding.name
        roleTextView = binding.role
        coffeeTextView = binding.coffee
        yearsTextView = binding.years
        bugsTextView = binding.bugs

        radius = 16f
        elevation = 4f
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(16, 16, 16, 16)
        }
    }

    fun setProfileImage(url: String) {
        if (url.isNotEmpty()) {
            Glide.with(context).load(url).placeholder(R.drawable.ic_person).into(profileImage)
        } else {
            profileImage.setImageResource(R.drawable.ic_person)
        }
    }

    fun setName(name: String) {
        nameTextView.text = name
    }

    fun setRole(role: String) {
        roleTextView.text = role
    }

    fun setStats(coffee: Int, years: Int, bugs: Int) {
        coffeeTextView.text = coffee.toString()
        yearsTextView.text = years.toString()
        bugsTextView.text = bugs.toString()
    }
}
