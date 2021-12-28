package com.shubhamgupta16.woomart.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shubhamgupta16.woomart.common.CompletionGenericLiveData;
import com.shubhamgupta16.woomart.common.CompletionLiveData;
import com.shubhamgupta16.woomart.common.DocumentLiveData;
import com.shubhamgupta16.woomart.models.User;
import com.shubhamgupta16.woomart.repo.FirebaseUserRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public final class UserViewModel extends ViewModel {
    private final MutableLiveData<String> id = new MutableLiveData<>();
    private final FirebaseUserRepository firebaseUserRepository;

    @Inject
    UserViewModel(FirebaseUserRepository firebaseUserRepository) {
        this.firebaseUserRepository = firebaseUserRepository;
    }

    public CompletionGenericLiveData<AuthResult> login(String username, String password) {
        return firebaseUserRepository.login(username, password);
    }

    public void logout() {
        firebaseUserRepository.logout();
    }

    public CompletionGenericLiveData<AuthResult> anonymousSignIn() {
        return firebaseUserRepository.anonymousSignIn();
    }

    public CompletionGenericLiveData<AuthResult> firebaseLogin(String email, String password) {
        return firebaseUserRepository.login(email, password);
    }

    public CompletionGenericLiveData<AuthResult> signUp(String email, String password) {
        return firebaseUserRepository.signUp(email, password);
    }

    public CompletionLiveData addUser(User user, OnSuccessListener successListener, OnFailureListener failureListener) {
        return firebaseUserRepository.addUser(user, successListener, failureListener);
    }

    public DocumentLiveData<User> user(String user_id) {
        return firebaseUserRepository.user(user_id);
    }

    public DocumentLiveData<User> user() {
        return firebaseUserRepository.user();
    }

    public CompletionLiveData updateUser(User user) {
        user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());

        return firebaseUserRepository.update(user);
    }

}