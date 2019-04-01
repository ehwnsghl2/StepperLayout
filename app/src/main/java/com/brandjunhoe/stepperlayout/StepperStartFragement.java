package com.brandjunhoe.stepperlayout;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class StepperStartFragement extends Fragment implements BlockingStep {

    private ProceedListener mProceedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stepper_start, container, false);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                onAttachToContext(activity);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCompleteAuth");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProceedListener) {
            mProceedListener = (ProceedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListener");
        }
    }

    protected void onAttachToContext(Context context) {
        if (context instanceof ProceedListener) {
            mProceedListener = (ProceedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCompleteAuth");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != mProceedListener) {
            mProceedListener = null;
        }
    }

    private void goToNext(String text) {
        if (mProceedListener != null) {
            mProceedListener.onProceed(text);
        }
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
        goToNext("텍스트 데이터 전달");
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError error) {
    }

}
