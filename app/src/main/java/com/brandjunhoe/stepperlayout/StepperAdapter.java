package com.brandjunhoe.stepperlayout;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepperAdapter extends AbstractFragmentStepAdapter {


    private int fragmentCount = 2; // 개수
    private StepperEndFragement stepperEndFragement;

    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0:
                return new StepperStartFragement();
            case 1:
                return stepperEndFragement = new StepperEndFragement();
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragmentCount;
    }

    public void goToNext(String text){
        stepperEndFragement.initData(text);
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position) {
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("첫번째") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("두번째") //can be a CharSequence instead
                        .create();
        }
        return null;
    }
}
