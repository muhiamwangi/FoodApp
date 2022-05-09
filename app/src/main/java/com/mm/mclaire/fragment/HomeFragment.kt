package com.mm.mclaire.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mm.mclaire.R
import com.mm.mclaire.databinding.FragmentHomeBinding
import com.mm.mclaire.network.RetrofitInstance
import com.mm.mclaire.pojo.Meal
import com.mm.mclaire.pojo.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
private  lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           RetrofitInstance.api.getRandomMeal().enqueue(object:Callback<MealList>{
               override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                   if(response.body()!=null){
                       val randomMeal: Meal=response.body()!!.meals[0]
                      // Log.d("testCV", "MEAL ID:${randomMeal.idMeal} NAME:${randomMeal.strMeal}")

                       //Using GLIDE:Image Loader Library
                       Glide.with(this@HomeFragment)
                       .load(randomMeal.strMealThumb)
                       .into(binding.imgRandomMeal)

                   }else{
                       return
                   }
               }

               override fun onFailure(call: Call<MealList>, t: Throwable) {
                   Log.d("HOME FRAGMENT",t.message.toString())
               }
           })
    }
}