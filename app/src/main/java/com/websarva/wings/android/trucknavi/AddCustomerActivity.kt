package com.websarva.wings.android.trucknavi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.websarva.wings.android.trucknavi.course_fix.Companion.dbHandler
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        btnSave.setOnClickListener {
            if (etAddDate.text.isEmpty() || etAddCourse.text.isEmpty() ) {
//                || etAddNo.text.isEmpty() || etAddName.text.isEmpty() || etAddLatlng.text.isEmpty()
                Toast.makeText(this, "Enter Customer Information", Toast.LENGTH_SHORT).show()

            } else {
                val customer = Customer()
                customer.customerDate = etAddDate.text.toString().toInt()
                customer.customerCourse = etAddCourse.text.toString().toInt()
                customer.customerNo = etAddNo.text.toString().toInt()
                customer.customerName = etAddName.text.toString()
                customer.customerLatlng = etAddLatlng.text.toString().toDouble()

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

                if (etAddLatlng.text.isEmpty())
                    customer.customerLatlng = 0.0 else
                    customer.customerLatlng = etAddLatlng.text.toString().toDouble()

                course_fix.dbHandler.addCustomer(this, customer)
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
        etAddLatlng.text.clear()
    }
}