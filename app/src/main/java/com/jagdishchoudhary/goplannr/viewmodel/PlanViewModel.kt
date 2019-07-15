package com.jagdishchoudhary.goplannr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jagdishchoudhary.goplannr.data.FirebaseQueryLiveData

class PlanViewModel : ViewModel() {

    private val liveData = FirebaseQueryLiveData(PLAN_REF)

    val dataSnapshotLiveData: LiveData<DataSnapshot>
        get() = liveData

    companion object {
        private val PLAN_REF = FirebaseDatabase.getInstance().reference.child("plans")
    }

}
