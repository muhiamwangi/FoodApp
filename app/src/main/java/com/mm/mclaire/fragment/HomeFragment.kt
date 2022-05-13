package com.mm.mclaire.fragment

import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mm.mclaire.activity.MealActivity
import com.mm.mclaire.databinding.FragmentHomeBinding
import com.mm.mclaire.pojo.Meal
import com.mm.mclaire.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private  lateinit var binding:FragmentHomeBinding
    private  lateinit var homeMvvm:HomeViewModel

//fragment instantiated & is in CREATED state
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    // homeMvvm = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
    }

    //inflate the fragments' layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root}

    //Bind specific views to properties by calling findViewById()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    homeMvvm.getRandomMeal()
    observeRandomMeal()
        onRandomMealClick()
    }

    private fun onRandomMealClick(){
        binding.randomMealCard.setOnClickListener(){
            val intent= Intent(activity,MealActivity::class.java )
            startActivity(intent)
        }
    }

    //listen to randomMealLiveData in this function
    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                        Glide.with(this@HomeFragment)
                            .load(t!!.strMealThumb)
                            .into(binding.imgRandomMeal)
            }
        })
    }
}