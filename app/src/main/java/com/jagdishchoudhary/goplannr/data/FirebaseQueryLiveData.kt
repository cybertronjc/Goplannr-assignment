package com.jagdishchoudhary.goplannr.data

import android.util.Log
import androidx.lifecycle.LiveData


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

import androidx.constraintlayout.widget.Constraints.TAG

class FirebaseQueryLiveData : LiveData<DataSnapshot> {
    private val query: Query
    private val listener = MyValueEventListener()


    constructor(ref: DatabaseReference) {
        this.query = ref
    }

    constructor(query: Query) {
        this.query = query
    }

    override fun onActive() {
        Log.d(LOG_TAG, "onActive")
        query.addListenerForSingleValueEvent(listener)
    }

    override fun onInactive() {
        Log.d(LOG_TAG, "onInactive")
        query.removeEventListener(listener)

    }

    private inner class MyValueEventListener : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            value = dataSnapshot
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

    companion object {
        private val LOG_TAG = "FirebaseQueryLiveData"
    }
}
