package com.mm.mclaire.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mm.mclaire.databinding.PopularMealBinding
import com.mm.mclaire.pojo.MealsByCategory

//Adapter is responsible for feeding content to the RV
//RV uses adapter to figure out how to display data on to the screen
//Adapter manages data to be displayed as well as the individual views used to display the data
class PopularMealsAdapter():RecyclerView.Adapter<PopularMealsAdapter.PopularMealsViewholder>(){
    //lambda for setting onPopularMealClick

    lateinit var onItemClick:((MealsByCategory)->Unit)
    private var mealsList=ArrayList<MealsByCategory>()
    fun setMeals(mealList:ArrayList<MealsByCategory>){
        this.mealsList=mealList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealsViewholder {
    return PopularMealsViewholder(PopularMealBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: PopularMealsViewholder, position: Int) {
    Glide.with(holder.itemView)
        .load(mealsList[position].strMealThumb)
        .into(holder.binding.imgPopularMeal)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position]) }
    }
    override fun getItemCount()=mealsList.size

    class PopularMealsViewholder(val binding: PopularMealBinding) : RecyclerView.ViewHolder(binding.root)
/**
-Viewholder class takes a view as a param( but we are using viewBinding)
-This class reps a single list item
-Viewholder pattern holds a reference to top level view->(RV) and reference to the contained views
-Viewholder extends the customViewHolder class ->Recyclerview.Viewholder()
-Viewholder class have fields for all contained view that are used to display info for @ individual view items
**/
}