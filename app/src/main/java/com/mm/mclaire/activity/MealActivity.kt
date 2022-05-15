package com.mm.mclaire.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mm.mclaire.R
import com.mm.mclaire.databinding.ActivityMealBinding
import com.mm.mclaire.fragment.HomeFragment
import com.mm.mclaire.pojo.Meal
import com.mm.mclaire.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMealBinding
    private  lateinit var mealId:String
    private  lateinit var mealName:String
    private  lateinit var mealThumb:String
    private  lateinit var youtubeLink:String
    private  lateinit var mealMvvM:MealViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMealBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mealMvvM=ViewModelProviders.of(this)[MealViewModel::class.java]
        getMealInfoFromIntent()
        setInfoInViews()
        loadingCase()
        mealMvvM.getMealDetail(mealId)//can only be called after 'getMealInfoFromIntent()'-else-CRASH
        //observe livedata
        observeMealDetailLiveData()

        onYoutubeIconClick()
    }

    private fun onYoutubeIconClick() {
        binding.imgYoutube.setOnClickListener{
            val intent=Intent(Intent.ACTION_VIEW,Uri.parse(youtubeLink))
            startActivity(intent)
        }    }

    //here we set category, area and instructions when the meal/livedata changes
    private fun observeMealDetailLiveData() {
        mealMvvM.observeMealLiveData().observe(this,object:Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal=t
                binding.category.text= (" Category:${meal!!.strCategory}")
                binding.region.text=(" Area:${meal!!.strArea}")
                binding.tvInstructionsDetail.text=meal!!.strInstructions
                youtubeLink=meal.strYoutube
            }
        })
    }

    private fun setInfoInViews() {
        //1.IMAGEVIEW
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgRandom)

       //2.TOOLBAR
        binding.collapsingToolbar.title=mealName
        //binding.collapsingToolbar.setCollapsedTitleTextColor((resources.getColor(R.color.white)))
        binding.collapsingToolbar.setExpandedTitleColor((resources.getColor(R.color.white)))
    }

    private fun getMealInfoFromIntent() {
        val intent=intent
        mealId=intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName=intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb=intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
        //show mealThumb in the imageView and the mealName in the ToolBar for our DetailFragment USING setInfoInViews FN
    }

    private fun loadingCase(){
        binding.progressBar.visibility=View.VISIBLE
        binding.favorite.visibility=View.INVISIBLE
        binding.tvInstructions.visibility=View.INVISIBLE
        binding.category.visibility=View.INVISIBLE
        binding.region.visibility=View.INVISIBLE
        binding.imgYoutube.visibility=View.INVISIBLE
    }

    private fun onResponseCase(){
    binding.progressBar.visibility=View.INVISIBLE
    binding.favorite.visibility=View.VISIBLE
    binding.tvInstructions.visibility=View.VISIBLE
    binding.category.visibility=View.VISIBLE
    binding.region.visibility=View.VISIBLE
    binding.imgYoutube.visibility=View.VISIBLE
    }
}