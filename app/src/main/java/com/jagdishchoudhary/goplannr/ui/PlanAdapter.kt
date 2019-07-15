package com.jagdishchoudhary.goplannr.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.button.MaterialButton
import com.jagdishchoudhary.goplannr.R
import com.jagdishchoudhary.goplannr.model.Plan

import java.util.ArrayList

class PlanAdapter(private var mPlanList: List<Plan>?) : RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {


    internal fun setPlanList(planList: List<Plan>) {
        mPlanList = planList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.plan_view, parent, false)

        return PlanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = mPlanList!![position]
        holder.planName.text = plan.name
        val featureArry = plan.features
        val featureArray = featureArry!!.size
        for (i in 0 until featureArray) {
            holder.features.append(" - " + featureArry[i])
            holder.features.append("\n")
        }
        val hospitalArry = plan.availableHospitals
        val hospitalArray = hospitalArry!!.size
        for (i in 0 until hospitalArray) {
            holder.hospitals.append(" - " + hospitalArry[i])
            holder.hospitals.append("\n")
        }
        holder.btnMore.setOnClickListener {
            if (holder.btnMore.text == "Show More") {
                holder.btnMore.text = "Show Less"
                holder.layoutMore.visibility = View.VISIBLE
            } else {
                holder.btnMore.text = "Show More"
                holder.layoutMore.visibility = View.GONE
            }
        }
        holder.cover.text = "Cover : " + plan.cover!!
        holder.price.text = "Pay : " + plan.price + "per month"


    }

    override fun getItemCount(): Int {
        return if (mPlanList == null) 0 else mPlanList!!.size
    }


    inner class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val planName: TextView
        private val features: TextView
        private val hospitals: TextView
        private val cover: TextView
        private val price: TextView
        private val btnMore: MaterialButton
        private val layoutMore: LinearLayout

        init {
            planName = itemView.findViewById<View>(R.id.planName) as TextView
            features = itemView.findViewById<View>(R.id.featuresText) as TextView
            hospitals = itemView.findViewById<View>(R.id.hospitalsText) as TextView
            btnMore = itemView.findViewById<View>(R.id.showMore) as MaterialButton
            layoutMore = itemView.findViewById<View>(R.id.layoutMore) as LinearLayout
            cover = itemView.findViewById<View>(R.id.planCover) as TextView
            price = itemView.findViewById<View>(R.id.planPrice) as TextView
        }
    }

    companion object {

        private val TAG = "Adapter"
    }
}



