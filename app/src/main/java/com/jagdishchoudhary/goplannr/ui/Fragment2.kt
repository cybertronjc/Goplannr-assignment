package com.jagdishchoudhary.goplannr.ui

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast


import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import com.jagdishchoudhary.goplannr.R

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragment2.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    internal var ageAdapter: ArrayAdapter<String>
    internal var salaryAdapter: ArrayAdapter<String>
    private var btnContinue: Button? = null

    private var mListener: OnFragmentInteractionListener? = null
    private var userAge: String? = null
    private var userSalary: String? = null
    private val editAge: TextInputEditText? = null
    private val editSalary: TextView? = null
    private val ageSpinnertouched: Boolean = false
    private val salarySpinnertouched: Boolean = false
    private var salarySpinner: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_fragment2, container, false)

        val context = activity
        //editAge = (TextInputEditText)v.findViewById(R.id.editAge);
        //editSalary = (TextView) v.findViewById(R.id.editSalary);

        val ageSpinner = v.findViewById<Spinner>(R.id.ageSpinner)
        val sp = context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        btnContinue = v.findViewById<View>(R.id.btnNext) as Button

        if (sp.contains("Age") && sp.contains("Salary")) {
            val userAge = sp.getString("Age", "1 years")

            val userSalary = sp.getString("Salary", "1 LPA")
            Toast.makeText(context, "details:$userAge$userSalary", Toast.LENGTH_SHORT).show()
        }

        val age = ArrayList<String>()
        age.add("Select your age")
        for (i in 1..100) {
            age.add(Integer.toString(i) + " years")
        }
        val salary = ArrayList<String>()
        salary.add("Select your salary")
        for (i in 1..40) {
            salary.add(Integer.toString(i) + " LPA")
        }


        ageAdapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                age
        )

        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        ageSpinner.adapter = ageAdapter
        ageAdapter.notifyDataSetChanged()


        ageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = age[position]
                userAge = ageSpinner.selectedItem.toString()
                //editAge.setText(item);
                //Toast.makeText(getActivity(), "Age Selected : "+ item, Toast.LENGTH_SHORT).show();

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        salarySpinner = v.findViewById(R.id.salarySpinner)
        salaryAdapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                salary
        )
        salaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        salarySpinner!!.adapter = salaryAdapter
        salaryAdapter.notifyDataSetChanged()
        salarySpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                userSalary = salarySpinner!!.selectedItem.toString()
                // editSalary.setText(parent.getSelectedItem().toString());
                // Toast.makeText(getActivity(), "Salary Selected : "+ salarySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



        btnContinue!!.setOnClickListener { v ->
            if (userAge == "Select your age" || userSalary == "Select your salary") {
                Toast.makeText(activity, "Please provide details", Toast.LENGTH_SHORT).show()
            } else {
                editor.putString("Age", userAge)
                editor.putString("Salary", userSalary)
                editor.commit()
                Navigation.findNavController(v).navigate(R.id.fragmentBtoC)
            }
        }
        return v
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        val PREFS_NAME = "USER_DETAILS"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): Fragment2 {
            val fragment = Fragment2()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
