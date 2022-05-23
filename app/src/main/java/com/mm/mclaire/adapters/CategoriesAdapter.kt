package com.mm.mclaire.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mm.mclaire.databinding.CategoryBinding
import com.mm.mclaire.pojo.Category

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoriesList= ArrayList<Category>()

    fun setCategoryList(categoriesList: List<Category>){
        this.categoriesList= categoriesList as  ArrayList<Category>
                notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       return CategoryViewHolder(
           CategoryBinding.inflate(LayoutInflater.from(parent.context))
       )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       //SET INFO TO THE VIEWS
        Glide.with(holder.itemView).load(categoriesList[position].strCategoryThumb).into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text=categoriesList[position].strCategory
    }

    override fun getItemCount()=categoriesList.size
}