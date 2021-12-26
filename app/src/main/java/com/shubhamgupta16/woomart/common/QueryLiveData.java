package com.shubhamgupta16.woomart.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.*;
import com.shubhamgupta16.woomart.models.Model;

import java.util.ArrayList;
import java.util.List;

import me.gilo.woodroid.callback.Resource;
import me.gilo.woodroid.callback.Status;


public final class QueryLiveData<T extends Model>
        extends LiveData<Resource<List<T>>> implements EventListener<QuerySnapshot> {

    private final Query query;
    private final Class<T> type;
    private ListenerRegistration registration;

    public QueryLiveData(Query query, Class<T> type) {
        this.query = query;
        this.type = type;

        setValue(new Resource<>(Status.LOADING));
    }

    @Override
    public void onEvent(QuerySnapshot snapshots, FirebaseFirestoreException e) {
        if (e != null) {
            setValue(new Resource<>(e));
            return;
        }
        setValue(new Resource<>(documentToList(snapshots)));
    }

    @Override
    protected void onActive() {
        super.onActive();
        registration = query.addSnapshotListener(this);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (registration != null) {
            registration.remove();
            registration = null;
        }
    }

    @NonNull
    private List<T> documentToList(QuerySnapshot snapshots) {
        final List<T> retList = new ArrayList<>();
        if (snapshots.isEmpty()) {
            return retList;
        }

        for (DocumentSnapshot document : snapshots.getDocuments()) {
            retList.add(document.toObject(type).withId(document.getId()));
        }

        return retList;
    }
}
