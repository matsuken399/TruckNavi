package com.websarva.wings.android.trucknavi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        btnSave.setOnClickListener {
            if (etAddDate.text.isEmpty() || etAddCourse.text.isEmpty()) {
//                || etAddNo.text.isEmpty() || etAddName.text.isEmpty() || etAddLatlng.text.isEmpty()
                Toast.makeText(this, "Enter Customer Information", Toast.LENGTH_SHORT).show()

            } else {
                val customer = Customer()
                customer.customerDate = etAddDate.text.toString().toInt()
                customer.customerCourse = etAddCourse.text.toString().toInt()
                customer.customerNo = etAddNo.text.toString().toInt()
                customer.customerName = etAddName.text.toString()
                customer.customerLat = etAddLat.text.toString().toDouble()
                customer.customerLng = etAddLng.text.toString().toDouble()

                if (etAddDate.text.isEmpty())
                    customer.customerDate = 0 else
                    customer.customerDate = etAddDate.text.toString().toInt()

                if (etAddCourse.text.isEmpty())
                    customer.customerCourse = 0 else
                    customer.customerCourse = etAddCourse.text.toString().toInt()

                if (etAddNo.text.isEmpty())
                    customer.customerNo = 0 else
                    customer.customerNo = etAddNo.text.toString().toInt()

                if (etAddName.text.isEmpty())
                    customer.customerName = "" else
                    customer.customerName = etAddName.text.toString()

                if (etAddLat.text.isEmpty())
                    customer.customerLat = 0.0 else
                    customer.customerLat = etAddLat.text.toString().toDouble()

                if (etAddLng.text.isEmpty())
                    customer.customerLng = 0.0 else
                    customer.customerLng = etAddLng.text.toString().toDouble()

                CourseFixActivity.dbHandler.addCustomer(this, customer)
                clearEdits()
            }
        }

        btnCancel.setOnClickListener {
            clearEdits()
            finish()
        }
    }

    private fun clearEdits() {
        etAddDate.text.clear()
        etAddCourse.text.clear()
        etAddNo.text.clear()
        etAddName.text.clear()
        etAddLat.text.clear()
        etAddLng.text.clear()
    }
}