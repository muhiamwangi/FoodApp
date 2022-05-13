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

class HomeViewModel:ViewModel(){

    //mutableLiveData--->You can change its value
    //Its set to private since we only want it to be modified from this class only
    private var randomMealLiveData= MutableLiveData<Meal>()

    fun getRandomMeal(){
            //retrofit instance
        RetrofitInstance.api.getRandomMeal().enqueue(object: Callback<MealList> {
            //onResponse is called when we succesfully connect to the API
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
            //if the body of the response has a value, we set that body to our random meal
                if(response.body()!=null){
                    val randomMeal: Meal =response.body()!!.meals[0]
            //setting the random meal to 'randomMealLiveData' which will be observed in HomeFragment
                randomMealLiveData.value=randomMeal

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HOME FRAGMENT",t.message.toString())
            }
        })
    }

    //FUNCTION IS USED TO OBSERVE randomMealLiveData in our HomeFragment
    //Livedata-->Cant change its value
    fun observeRandomMealLiveData():LiveData<Meal>{
        return randomMealLiveData
    }
}