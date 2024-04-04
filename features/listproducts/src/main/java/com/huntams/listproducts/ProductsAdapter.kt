package com.huntams.listproducts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.huntams.listproducts.databinding.ItemProductBinding
import com.huntams.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProductsAdapter @Inject constructor() :
    ListAdapter<Product, ProductsAdapter.DataViewHolder>(
        diffUtilCallback
    ) {

    private var onClick: (ErrorResult) -> Unit = {}
    fun setCallback(callback: (ErrorResult) -> Unit) {
        this.onClick = callback
    }

    private var buttonClick: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(
        private val binding: ItemProductBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {


            with(binding) {

                buttonPrice.text =  root.context.getString(R.string.price,item.getFormattedPrice())
                imageViewProduct.load(item.strMealThumb) {
                    listener(onSuccess = { _, _ ->
                        placeholder(R.drawable.draw_banner_300)
                    }, onError = { _: ImageRequest, error ->
                        onClick.invoke(error)
                    })

                    error(R.drawable.ic_error_24)
                }
                textViewNameProduct.text = item.strMeal
                textViewInfoProduct.text = item.strInstructions
                buttonPrice.setOnClickListener {
                    buttonClick(item)
                }

            }

        }
    }
}
private val diffUtilCallback = object : DiffUtil.ItemCallback<Product>() {

    override fun areContentsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem.idMeal == newItem.idMeal

    }
}