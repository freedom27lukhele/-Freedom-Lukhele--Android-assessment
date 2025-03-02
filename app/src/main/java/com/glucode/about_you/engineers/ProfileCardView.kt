package com.glucode.about_you.engineers

import android.content.Context
import android.util.AttributeSet
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


    private val profileImage: ImageView
    private val nameTextView: TextView
    private val roleTextView: TextView
    private val coffeeTextView: TextView
    private val yearsTextView: TextView
    private val bugsTextView: TextView


    init {
        profileImage = findViewById(R.id.profile_image)
        nameTextView = findViewById(R.id.name)
        roleTextView = findViewById(R.id.role)
        coffeeTextView = findViewById(R.id.coffee)
        yearsTextView = findViewById(R.id.years)
        bugsTextView = findViewById(R.id.bugs)

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
        Glide.with(context).load(url).placeholder(R.drawable.ic_person).into(profileImage)
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
