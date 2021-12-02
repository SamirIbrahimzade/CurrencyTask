package com.qarone.currencytask.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qarone.currencytask.R
import com.qarone.currencytask.data.models.Rate
import com.qarone.currencytask.databinding.RateItemBinding

class RateAdapter : RecyclerView.Adapter<RateAdapter.RatesViewHolder>() {

    private val rates = mutableListOf<Rate>()

    fun setData(newPaymentTypes:List<Rate>){
        this.rates.clear()
        this.rates.addAll(newPaymentTypes)
        notifyDataSetChanged()
    }
    fun addData(rate: Rate){
        this.rates.add(rate)
        notifyDataSetChanged()
    }
    fun clearData(){
        this.rates.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rate_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bindData(rates[position])
    }

    override fun getItemCount(): Int = rates.size

    class RatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RateItemBinding.bind(itemView)

        fun bindData(rate: Rate) {
            binding.apply {
                tvNameItemRate.text = rate.name
                tvRateItemRate.text = rate.rate.toString()
            }
        }
    }

}