package com.example.minijobchat.model.realtime

interface RealtimeSubscriber<T> {
    fun<T> onAdded(id: String, data: T)
    fun<T> onModified(id: String, data: T)
    fun<T> onRemoved(id: String, data: T)
}

interface RealtimeObserver<T> {
    fun<T> onAdded(id: String, data: T)
    fun<T> onModified(id: String, data: T)
    fun<T> onRemoved(id: String, data: T)
}

interface OnRetrieveDataListener<T> {
    fun run(observer: RealtimeObserver<T>)
    fun dispose()
}

class RealtimeData<T>(val listener: OnRetrieveDataListener<T>) {
    private val mSubscribers = mutableListOf<RealtimeSubscriber<T>>()
    private var mObserver: RealtimeObserver<T>? = null

    init {
        mObserver = object : RealtimeObserver<T> {

            override fun <T> onAdded(id: String, data: T) {
                for (subscriber in mSubscribers)
                    subscriber.onAdded(id, data)
            }

            override fun <T> onModified(id: String, data: T) {
                for (subscriber in mSubscribers)
                    subscriber.onModified(id, data)
            }

            override fun <T> onRemoved(id: String, data: T) {
                for (subscriber in mSubscribers)
                    subscriber.onRemoved(id, data)
            }

        }
    }

    fun subscribe(subscriber: RealtimeSubscriber<T>) {
        mSubscribers.add(subscriber)
    }

    fun dispose() {
        listener.dispose()
    }
}