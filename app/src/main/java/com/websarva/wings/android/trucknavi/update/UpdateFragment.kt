package com.websarva.wings.android.trucknavi.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.websarva.wings.android.trucknavi.R
import com.websarva.wings.android.trucknavi.model.User
import com.websarva.wings.android.trucknavi.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlin.reflect.KProperty

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateDate_et.setText(args.currentUser.date.toString())
        view.updateCourse_et.setText(args.currentUser.course.toString())
        view.updateNo_et.setText(args.currentUser.no.toString())
        view.updateName_et.setText(args.currentUser.name)
        view.updateLat_et.setText(args.currentUser.lat.toString())
        view.updateLng_et.setText(args.currentUser.lng.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val date = Integer.parseInt(updateDate_et.text.toString())
        val course = Integer.parseInt(updateCourse_et.text.toString())
        val no = Integer.parseInt(updateNo_et.text.toString())
        val name = updateName_et.text.toString()
        val lat = Integer.parseInt(updateLat_et.text.toString())
        val lng = Integer.parseInt(updateLng_et.text.toString())

        if (inputCheck(
                updateDate_et.text,
                updateCourse_et.text,
                updateNo_et.text,
                name,
                updateLat_et.text,
                updateLng_et.text
            )
        ) {
            val updateUser = User(
                args.currentUser.id,
                date,
                course,
                no,
                name,
                lat,
                lng
            )
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Succefully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all field!", Toast.LENGTH_SHORT)
                .show()
        }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Succesfully removed: ${args.currentUser.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.name}?")
        builder.create().show()

    }
}