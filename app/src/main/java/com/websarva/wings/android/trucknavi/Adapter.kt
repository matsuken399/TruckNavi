package com.websarva.wings.android.trucknavi

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.trucknavi.course_fix.Companion.dbHandler
import kotlinx.android.synthetic.main.io_customer_update.view.*
import kotlinx.android.synthetic.main.io_customers.view.*

class CustomerAdapter(mCtx: Context, val customers: ArrayList<Customer>) :
        RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    val mCtx = mCtx

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvInputDate = itemView.tvInputDate
        val tvInputCourse = itemView.tvInputCourse
        val tvInputNo = itemView.tvInputNo
        val tvInputName = itemView.tvInputName
        val tvInputLatlng = itemView.tvInputLatlng
        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.io_customers, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val customer: Customer = customers[p1]
        p0.tvInputDate.text = customer.customerDate.toString()
        p0.tvInputCourse.text = customer.customerCourse.toString()
        p0.tvInputNo.text = customer.customerNo.toString()
        p0.tvInputName.text = customer.customerName
        p0.tvInputLatlng.text = customer.customerLatlng.toString()

        p0.btnDelete.setOnClickListener {
            val customerName: String = customer.customerName

            val alertDialog = AlertDialog.Builder(mCtx)
                    .setTitle("Warning")
                    .setMessage("Are You Sure to Delete : $customerName ")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        if (course_fix.dbHandler.deleteCustomer(customer.customerID)) {
                            customers.removeAt(p1)
                            notifyItemRemoved(p1)
                            notifyItemRangeChanged(p1, customers.size)
                            Toast.makeText(mCtx, "Customer $customerName Deleted", Toast.LENGTH_SHORT)
                                    .show()
                        } else {
                            Toast.makeText(mCtx, "Error Deleting", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .show()
        }

        p0.btnUpdate.setOnClickListener {
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.io_customer_update, null)

            val txtCustDate: TextView = view.findViewById(R.id.editUpCustomerDate)
            val txtCustCourse: TextView = view.findViewById(R.id.editUpCustomerCourse)
            val txtCustNo: TextView = view.findViewById(R.id.editUpCustomerNo)
            val txtCustName: TextView = view.findViewById(R.id.editUpCustomerName)
            val txtCustLatlng: TextView = view.findViewById(R.id.editUpCustomerLatlng)

            txtCustDate.text = customer.customerDate.toString()
            txtCustCourse.text = customer.customerCourse.toString()
            txtCustNo.text = customer.customerNo.toString()
            txtCustName.text = customer.customerName
            txtCustLatlng.text = customer.customerLatlng.toString()

            val builder = AlertDialog.Builder(mCtx)
                    .setTitle("Update Customer Info.")
                    .setView(view)
                    .setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
                        var isUpdate: Boolean = course_fix.dbHandler.updateCustomer(
                                customer.customerID.toString(),
                                view.editUpCustomerDate.text.toString(),
                                view.editUpCustomerCourse.text.toString().toInt(),
                                view.editUpCustomerNo.text.toString().toInt(),
                                view.editUpCustomerName.text.toString(),
                                view.editUpCustomerLatlng.text.toString().toDouble()
                        )
                        if (isUpdate == true) {
                            customers[p1].customerDate = view.editUpCustomerDate.text.toString().toInt()
                            customers[p1].customerCourse = view.editUpCustomerCourse.text.toString().toInt()
                            customers[p1].customerNo = view.editUpCustomerNo.text.toString().toInt()
                            customers[p1].customerName = view.editUpCustomerName.text.toString()
                            customers[p1].customerLatlng = view.editUpCustomerLatlng.text.toString().toDouble()

                            notifyDataSetChanged()
                            Toast.makeText(mCtx, "Updated Successfull", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(mCtx, "Error Updating", Toast.LENGTH_SHORT).show()
                        }
                    }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    })
            val alert = builder.create()
            alert.show()
        }
    }

}
