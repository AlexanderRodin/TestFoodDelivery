package com.example.testfooddelivery

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testfooddelivery.databinding.CardBinding
import com.example.testfooddelivery.domain.Product

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardBinding.bind(view)

    fun bind(product: Product) = with(binding) {
//        Picasso.get().load(product.image[0]).into(binding.imgProduct)
        tvName.text = product.title
        tvDescription.text = product.description
        btnAddBasket.text = product.price.toString()
    }
}