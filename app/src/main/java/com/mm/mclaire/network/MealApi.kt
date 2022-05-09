package com.mm.mclaire.network

import com.mm.mclaire.pojo.MealList
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/** Retrofit needs:
     1.Data Class
     2.Interface
     3.Instance/Builder
 **/

private const val BASE_URL= "https://www.themealdb.com/api/json/v1/1/"

//Moshi convertor
private val moshi= Moshi.Builder()
//.add(KotlinJsonAdapterFactory())
.build()

//Retrofit object with baseURL and ConverterFactory
private val retrofit= Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface MealApi {
      @GET("random.php")
      fun getRandomMeal():Call<MealList>
    }


//Singleton pattern:Only one instance of 'MealApi' will be used in the entire app
object RetrofitInstance{
        val api: MealApi by lazy{
            retrofit.create(MealApi::class.java)
        }
    }
