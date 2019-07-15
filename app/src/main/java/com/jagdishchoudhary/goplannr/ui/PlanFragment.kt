package com.jagdishchoudhary.goplannr.ui

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import com.google.firebase.database.DataSnapshot
import com.jagdishchoudhary.goplannr.viewmodel.PlanViewModel
import com.jagdishchoudhary.goplannr.R
import com.jagdishchoudhary.goplannr.model.Plan

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlanFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    private val planList = ArrayList<Plan>()
    private var planRecycler: RecyclerView? = null
    private var planAdapter: PlanAdapter? = null
    private var planViewModel: PlanViewModel? = null
    internal var liveData: LiveData<DataSnapshot>? =null
    private var progressBar: ProgressBar? = null

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
        val view = inflater.inflate(R.layout.fragment_plan, container, false)
        val context = activity
        val sp = context!!.getSharedPreferences("USER_DETAILS", Context.MODE_PRIVATE)
        progressBar = view.findViewById<View>(R.id.progressBar) as ProgressBar
        planRecycler = view.findViewById<View>(R.id.planRecycler) as RecyclerView
        planViewModel = ViewModelProviders.of(this).get(PlanViewModel::class.java)
        liveData = planViewModel!!.dataSnapshotLiveData

        val userAge = sp.getString("Age", "1 years")
        val userSalary = sp.getString("Salary", "1 LPA")
        val userAgeInt = Integer.parseInt(userAge!!.substring(0, userAge.length - 6))
        val userSalaryInt = Integer.parseInt(userSalary!!.substring(0, userSalary.length - 4))

        (liveData as LiveData<DataSnapshot>).observe(viewLifecycleOwner, Observer { dataSnapshot ->
            if (dataSnapshot.exists()) {
                for (postSnapshot in dataSnapshot.children) {
                    val plan = postSnapshot.getValue(Plan::class.java)
                    if (userAgeInt < plan!!.maxAge!! && userAgeInt > plan.minAge!!) {
                        if (userSalaryInt < plan.maxSalary!! && userSalaryInt > plan.minSalary!!) {
                            planList.add(plan)
                            planAdapter!!.setPlanList(planList)
                            Toast.makeText(activity, "Here we are", Toast.LENGTH_SHORT).show()
                        }
                    }


                }

            }
        })
        //Toast.makeText(getActivity(), "List size after: " + Integer.toString(planList.size()), Toast.LENGTH_SHORT).show();
        planAdapter = PlanAdapter(planList)
        //planAdapter.setPlanList(planList);

        planRecycler!!.layoutManager = LinearLayoutManager(activity)

        planRecycler!!.itemAnimator = DefaultItemAnimator()
        planRecycler!!.adapter = planAdapter

        //planAdapter.notifyDataSetChanged();


        return view
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

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlanFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): PlanFragment {
            val fragment = PlanFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }


}// Required empty public constructor
