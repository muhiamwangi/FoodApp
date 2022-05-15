package com.mm.mclaire.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi= Moshi.Builder().build()
private const val BASE_URL= "https://www.themealdb.com/api/json/v1/1/"

object RetrofitInstance{
    val api: MealApi by lazy{
      Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
           .create(MealApi::class.java)
    }
}