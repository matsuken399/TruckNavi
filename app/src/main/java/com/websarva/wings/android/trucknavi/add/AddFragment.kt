package com.websarva.wings.android.trucknavi.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.websarva.wings.android.trucknavi.R
import com.websarva.wings.android.trucknavi.model.User
import com.websarva.wings.android.trucknavi.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.save_btn.setOnClickListener {
            insertDataToDatabase()
        }

        view.cancel_btn.setOnClickListener {
            cancelDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val date = addDate_et.text
        val course = addCourse_et.text
        val no = addNo_et.text
        val name = addName_et.text.toString()
        val lat = addLat_et.text
        val lng = addLng_et.text

        if (inputCheck(date, course, no, name, lat, lng)) {
            val user = User(0,
                Integer.parseInt(date.toString()),
                Integer.parseInt(course.toString()),
                Integer.parseInt(no.toString()),
                name,
                Integer.parseInt(lat.toString()),
                Integer.parseInt(lng.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill uot all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun cancelDataToDatabase() {
        clearEdits()
    }

    private fun inputCheck(
        date: Editable,
        course: Editable,
        no: Editable,
        name: String,
        lat: Editable,
        lng: Editable
    ): Boolean {
        return !(date.isEmpty() && course.isEmpty() && no.isEmpty() && TextUtils.isEmpty(name) && lat.isEmpty() && lng.isEmpty())
    }

    private fun clearEdits() {
        addDate_et.text.clear()
        addCourse_et.text.clear()
        addNo_et.text.clear()
        addName_et.text.clear()
        addLat_et.text.clear()
        addLng_et.text.clear()
    }

}