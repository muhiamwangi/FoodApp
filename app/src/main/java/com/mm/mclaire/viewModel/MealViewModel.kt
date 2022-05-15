package com.mm.mclaire.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mm.mclaire.network.RetrofitInstance
import com.mm.mclaire.pojo.Meal
import com.mm.mclaire.pojo.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel:ViewModel() {

    private var mealLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id: String) {
        RetrofitInstance.api.getMealData(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    var mealDetail: Meal = response.body()!!.meals[0]
                    mealLiveData.value = mealDetail
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("  MEAL-ACTIVITY", t.message.toString())
            }
        })
    }

    fun observeMealLiveData(): LiveData<Meal> {
        return mealLiveData
    }
}