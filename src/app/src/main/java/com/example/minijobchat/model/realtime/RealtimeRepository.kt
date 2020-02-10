package com.example.minijobchat.model.realtime

import com.example.minijobchat.model.dto.FriendRequest
import com.example.minijobchat.model.dto.Notification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

interface RealtimeRepository {
    fun provideRealtimeFriendRequest(): RealtimeData<FriendRequest>
    fun provideRealtimeNotification(): RealtimeData<Notification>
}

class RealtimeRepositoryFirebaseImp: RealtimeRepository {
    val mDatabase = FirebaseFirestore.getInstance()
    val mAuth = FirebaseAuth.getInstance()

    override fun provideRealtimeFriendRequest(): RealtimeData<FriendRequest> {
        return RealtimeData(object: OnRetrieveDataListener<FriendRequest> {
            private lateinit var listenerRegistration: ListenerRegistration

            override fun run(observer: RealtimeObserver<FriendRequest>) {
                if (mAuth.currentUser == null) return
                listenerRegistration = createSnapshotListener("${mAuth.currentUser?.uid}/friend-requests", observer)
            }

            override fun dispose() {
                listenerRegistration.remove()
            }
        })
    }

    override fun provideRealtimeNotification(): RealtimeData<Notification> {
        return RealtimeData(object: OnRetrieveDataListener<Notification> {
            private lateinit var listenerRegistration: ListenerRegistration

            override fun run(observer: RealtimeObserver<Notification>) {
                if (mAuth.currentUser == null) return
                listenerRegistration = createSnapshotListener("${mAuth.currentUser?.uid}/notifications", observer)
            }

            override fun dispose() {
                listenerRegistration.remove()
            }
        })
    }

    private fun<T> createSnapshotListener(collectionPath: String, observer: RealtimeObserver<T>): ListenerRegistration {
        return mDatabase.collection(collectionPath)
            .addSnapshotListener { value, e ->
                if (e != null) return@addSnapshotListener
                if (value == null) return@addSnapshotListener
                for (dc in value.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED ->
                            observer.onAdded(dc.document.id, dc.document.toObject(FriendRequest::class.java))
                        DocumentChange.Type.MODIFIED ->
                            observer.onModified(dc.document.id, dc.document.toObject(FriendRequest::class.java))
                        DocumentChange.Type.REMOVED ->
                            observer.onRemoved(dc.document.id, dc.document.toObject(FriendRequest::class.java))
                    }
                }
            }
    }
}