package com.mm.mclaire.network

import com.mm.mclaire.pojo.CategoriesList
import com.mm.mclaire.pojo.MealsByCategoryList
import com.mm.mclaire.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealData(@Query("i") id: String): Call<MealList>

    @GET("filter.php?")
    fun getPopularMeals(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories(): Call<CategoriesList>
}