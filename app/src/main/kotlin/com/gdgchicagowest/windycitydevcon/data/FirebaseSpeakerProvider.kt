package com.gdgchicagowest.windycitydevcon.data

import com.gdgchicagowest.windycitydevcon.model.Speaker
import com.google.firebase.database.*

class FirebaseSpeakerProvider : SpeakerProvider {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    private val queries: MutableMap<Any, Query> = mutableMapOf()
    private val listeners: MutableMap<Any, ValueEventListener> = mutableMapOf()

    override fun addSpeakerListener(key: Any, onComplete: (Map<String, Speaker>?) -> Unit) {

    }

    override fun addSpeakerListener(id: String, onComplete: (Speaker?) -> Unit) {
        if (queries[id] != null) {
            removeSpeakerListener(id)
        }

        val listener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot?) {
                onComplete(data?.getValue(Speaker::class.java))
            }

            override fun onCancelled(e: DatabaseError?) {
                onComplete(null)
            }
        }
        listeners[id] = listener

        val query = database.child("speakers").child(id)
        query.addValueEventListener(listener)
        queries[id] = query
    }

    override fun removeSpeakerListener(key: Any) {
        val query = queries[key]
        query?.removeEventListener(listeners[key])

        queries.remove(key)
        listeners.remove(key)
    }
}