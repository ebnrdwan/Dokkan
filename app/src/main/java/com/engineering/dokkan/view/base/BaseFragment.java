package com.engineering.dokkan.view.base;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.NavHostFragment;

import com.engineering.dokkan.R;
import com.google.firebase.auth.FirebaseAuth;

abstract public class BaseFragment extends Fragment {


    /*================= must be implemented Methods - start=========================*/

    /**
     * his is return the layout Resource id to pass it to onCreateView
     */
    public abstract int getLayoutId();


    /**
     * find your views here,
     *
     * @param view is the rootView that inflated by fragment
     *             use it to find your views --> view.findViewById(id)
     */
    public abstract void initializeViews(View view);


    /*adding all view listeners here like on view click or on item click*/
    public abstract void setListeners();


    /*will  be needed in the future isa, not now, */
    private void onActivityReady(Bundle bundle) {
    }


    /*================= must be implemented Methods - End=========================*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListeners();
        onActivityReady(savedInstanceState);
        if (getActivity() != null)
            getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    navigateUp();
                }
            });
    }


    public void navigateTo(NavDirections direction) {
        NavHostFragment.findNavController(this).navigate(direction);

    }


    public void navigateTo(Uri deepLink) {
        NavHostFragment.findNavController(this).navigate(deepLink);
    }

    public void navigateTo(
            @IdRes int actionId,
            NavOptions navOptions,
            Navigator.Extras navigatorExtras,
            Bundle bundle
    ) {
        NavController navController =
                NavHostFragment.findNavController(this); /* extension function has a bug in clear task */
        if (navigatorExtras == null) {
            navController
                    .navigate(actionId, bundle, navOptions);
        } else
            navController.navigate(actionId, bundle, navOptions, navigatorExtras);
    }

    public NavController getNavController() {
        return NavHostFragment.findNavController(this);
    }


    public void navigateUp() {
        NavHostFragment.findNavController(this).navigateUp();
    }

    public boolean isUserExisted() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public String getUserId() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public String getUserIdWrapper() {
        if (isUserExisted()) {
            return getUserId();
        } else {
            navigateTo(R.id.action_global_to_loginFragment, null, null, null);
            return "";
        }
    }


}
