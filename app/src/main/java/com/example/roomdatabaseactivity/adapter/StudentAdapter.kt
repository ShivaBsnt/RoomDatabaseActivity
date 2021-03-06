package com.example.roomdatabaseactivity.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdatabaseactivity.R
import com.example.roomdatabaseactivity.parcel.StudentData
import com.example.roomdatabaseactivity.model.Student
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.hdodenhof.circleimageview.CircleImageView


class StudentAdapter(
    val lstStudent: ArrayList<Student>,
    val context: Context,


    ): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgProfile: CircleImageView
        val tvName: TextView
        val tvAge: TextView
        val tvAddress: TextView
        val tvGender: TextView
        val imgDelete: ImageView
        val imgEdit: ImageView


        init {
            imgProfile = view.findViewById(R.id.imgProfile)
            tvName = view.findViewById(R.id.tvName)
            tvAge = view.findViewById(R.id.tvAge)
            tvAddress = view.findViewById(R.id.tvAddress)
            tvGender = view.findViewById(R.id.tvGender)
            imgDelete = view.findViewById(R.id.imgDelete)
            imgEdit = view.findViewById(R.id.imgEdit)


        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_layout, parent, false)
        return StudentViewHolder(view)




    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val std = lstStudent[position]
        holder.tvName.text = std.studentName
        holder.tvAddress.text = std.studentAddress
        holder.tvAge.text = std.studentAge
        holder.tvGender.text = std.studentGender
        holder.imgEdit.setOnClickListener() {


            showDialog(position)
        }
        holder.imgDelete.setOnClickListener(){

            val builder = AlertDialog.Builder(context)
            //set title for alert dialog
            builder.setTitle("Delete")
            //set message for alert dialog
            builder.setMessage("Are you sure you want to delete?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(context,"clicked yes",Toast.LENGTH_LONG).show()
            }
            //performing cancel action
            builder.setNeutralButton("Cancel"){dialogInterface , which ->
                Toast.makeText(context,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
            }
            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(context,"clicked No",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }


        Glide.with(context)
            .load(std.studentImage)
            .into(holder.imgProfile)
    }

    private fun showDialog(position: Int) {
        val dialog = Dialog(context,R.style.ThemeOverlay_AppCompat_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.edit_details_form)
        dialog.setTitle("Hello")
//        val body = dialog.findViewById(R.id.body) as TextView
//        body.text = title
        val inputUsername = dialog.findViewById(R.id.inputUsername) as EditText
        val inputAddress = dialog.findViewById(R.id.inputAddress) as EditText
        val inputAge = dialog.findViewById(R.id.inputAge) as EditText
        val groupradio = dialog.findViewById(R.id.groupradio) as RadioGroup
        val gender = lstStudent[position].studentGender
        when(gender)
        {
            "Male" -> groupradio.check(R.id.rbMale)
            "Female" -> groupradio.check(R.id.rbFemale)
            "Other" -> groupradio.check(R.id.rbOther)
        }


        inputUsername.setText(lstStudent[position].studentName)
        inputAddress.setText(lstStudent[position].studentAddress)
        inputAge.setText(lstStudent[position].studentAge)
        val yesBtn = dialog.findViewById(R.id.btnUpdate) as Button
        val noBtn = dialog.findViewById(R.id.btnCancel) as Button
        yesBtn.setOnClickListener {
            StudentData.studentList[position].studentName = inputUsername.text.toString()
            StudentData.studentList[position].studentAddress = inputAddress.text.toString()
            StudentData.studentList[position].studentAge = inputAge.text.toString()
            var gender = ""
            var image=""
            when (groupradio.checkedRadioButtonId) {
                R.id.rbMale -> gender = "Male"
                R.id.rbFemale -> gender= "Female"
                R.id.rbOther -> gender = "Other"
            }
            if (gender == "Male") {
                image = "https://digiimento.com/wp-content/uploads/2015/11/avatar-male-200x200.png"
            }
            if (gender == "Female") {
                image =
                    "https://avatarmaker.net/images/8.png"
            }
            if (gender == "Other") {
                image = "https://img.icons8.com/cotton/2x/gender.png"
            }
            StudentData.studentList[position].studentGender = gender
            StudentData.studentList[position].studentImage = image

            notifyDataSetChanged()
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }

        dialog.show()

    }

//    private fun showDialog(position: Int) {
//        val dialog = Dialog(context,R.style.ThemeOverlay_AppCompat_Dialog_Alert)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.custom_dialog)
//        dialog.setTitle("Hello")
//
//        val inputUsername = dialog.findViewById(R.id.inputUsername) as EditText
//        val inputAddress = dialog.findViewById(R.id.inputAddress) as EditText
//        val inputAge = dialog.findViewById(R.id.inputAge) as EditText
//        val groupradio = dialog.findViewById(R.id.groupradio) as RadioGroup
//        val gender = lstStudent[position].studentGender
//        when(gender)
//        {
//            "Male" -> groupradio.check(R.id.rbMale)
//            "Female" -> groupradio.check(R.id.rbFemale)
//            "Other" -> groupradio.check(R.id.rbOther)
//        }
//
//
//        inputUsername.setText(lstStudent[position].studentName)
//        inputAddress.setText(lstStudent[position].studentAddress)
//        inputAge.setText(lstStudent[position].studentAge)
//        val yesBtn = dialog.findViewById(R.id.btnUpdate) as Button
//        val noBtn = dialog.findViewById(R.id.btnCancel) as Button
//        yesBtn.setOnClickListener {
//           StudentData.studentList[position].studentName = inputUsername.text.toString()
//           StudentData.studentList[position].studentAddress = inputAddress.text.toString()
//           StudentData.studentList[position].studentAge = inputAge.text.toString()
//            var gender = ""
//            var image=""
//            when (groupradio.checkedRadioButtonId) {
//                R.id.rbMale -> gender = "Male"
//                R.id.rbFemale -> gender= "Female"
//                R.id.rbOther -> gender = "Other"
//            }
//            if (gender == "Male") {
//                image = "https://digiimento.com/wp-content/uploads/2015/11/avatar-male-200x200.png"
//            }
//            if (gender == "Female") {
//                image =
//                    "https://avatarmaker.net/images/8.png"
//            }
//            if (gender == "Other") {
//                image = "https://img.icons8.com/cotton/2x/gender.png"
//            }
//            StudentData.studentList[position].studentGender = gender
//            StudentData.studentList[position].studentImage = image
//
//            notifyDataSetChanged()
//            dialog.dismiss()
//        }
//        noBtn.setOnClickListener { dialog.dismiss() }
//
//        dialog.show()
//    }

    override fun getItemCount(): Int {
        return lstStudent.size

    }








}
