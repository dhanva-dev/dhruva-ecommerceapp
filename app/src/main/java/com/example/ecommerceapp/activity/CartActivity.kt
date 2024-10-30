package com.example.ecommerceapp.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.Adapter.CartAdapter
import com.example.ecommerceapp.Adapter.ManagmentCart
import com.example.ecommerceapp.databinding.ActivityCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener

class CartActivity : BaseActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var managementCart: ManagmentCart
    private var tax: Double= 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        setVariable()
        initCartList()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.viewCart.adapter= CartAdapter(managementCart.getListCart(), this, object: ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }

            })

        with(binding) {
            emptyTxt.visibility = if(managementCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility = if(managementCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round(managementCart.getTotalFee()*100)/100

        with(binding) {
            totalFeeTxt.text="$itemTotal"
            taxTxt.text = "$tax"
            deliveryTxt.text = "$delivery"
            TotalTxt.text = "$total"
        }
    }
    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            finish()
        }

    }
}