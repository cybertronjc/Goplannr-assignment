package com.jagdishchoudhary.goplannr.ui

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.jagdishchoudhary.goplannr.R
import com.jagdishchoudhary.goplannr.model.User


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragment1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null
    private var btnContinue: Button? = null
    private var textName: TextInputEditText? = null
    private var textPhone: TextInputEditText? = null
    private val gson: Gson? = null

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
        val v = inflater.inflate(R.layout.fragment_fragment1, container, false)
        val context = activity
        val sp = context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        textName = v.findViewById<View>(R.id.textName) as TextInputEditText
        textPhone = v.findViewById<View>(R.id.textPhone) as TextInputEditText
        btnContinue = v.findViewById<View>(R.id.btnContinue) as Button
        //        gson = new Gson();
        //        String getJson = sp.getString("MyUser", "" );
        //        User user = gson.fromJson(getJson, User.class);
        //Toast.makeText(getActivity(), "User values:" + user.getName(), Toast.LENGTH_SHORT).show();
        if (sp.contains("Name") && sp.contains("Phone")) {
            val userName = sp.getString("Name", "User Name")
            textName!!.setText(userName)
            val userPhone = sp.getString("Phone", "User Phone")
            textPhone!!.setText(userPhone)

            Toast.makeText(context, "Shared Pref:" + userName!!, Toast.LENGTH_SHORT).show()
        }




        btnContinue!!.setOnClickListener { v ->
            //                User user = new User(textName.getText().toString(), textPhone.getText().toString(), 1,1);
            //
            //                String json = gson.toJson(User.class);
            //                editor.putString("MyUser", json);
            //                editor.commit();
            if (textName!!.length() == 0 || textPhone!!.length() < 10) {
                Toast.makeText(activity, "Please enter your details", Toast.LENGTH_SHORT).show()
            } else {
                editor.putString("Name", textName!!.text!!.toString())
                editor.putString("Phone", textPhone!!.text!!.toString())
                editor.commit()
                Navigation.findNavController(v).navigate(R.id.fragmentAtoB)
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

        val PREFS_NAME = "USER_PREFS"


        private val TAG = "UserDetails1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): Fragment1 {
            val fragment = Fragment1()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
