/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.statistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.data.Task;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Main UI for the statistics screen.
 */
public class StatisticsFragment extends Fragment implements StatisticsContract.View {

    private TextView mStatisticsTV;
    private TextView seekBarTextView;
    private SeekBar seekBar;
    private String progressText;
    private Switch dialogSwitch;
    private Switch spinnerSwitch;
    private Spinner spinner;
    private StatisticsContract.Presenter mPresenter;
    private int activeTasks = 0;
    private int completedTasks = 0;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public void setPresenter(@NonNull StatisticsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.statistics_frag, container, false);
        mStatisticsTV = root.findViewById(R.id.statistics);
        seekBarTextView = root.findViewById(R.id.seekBarTextView);
        seekBar = root.findViewById(R.id.simpleSeekBar);
        spinner = root.findViewById(R.id.statisticsSpinner);
        dialogSwitch = root.findViewById(R.id.statisticsDialogSwitch);
        spinnerSwitch = root.findViewById(R.id.statisticsSpinnerSwitch);
        progressText = getString(R.string.statisticsProgress);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        seekBarTextView.setText(String.format(progressText + " %d", seekBar.getProgress()));

                    }
                }
        );
        dialogSwitch.setOnCheckedChangeListener(
                new Switch.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (dialogSwitch.isChecked()) {
                            showDialogWithDelay();
                        }
                    }
                }
        );
        spinnerSwitch.setOnCheckedChangeListener(
                new Switch.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (spinnerSwitch.isChecked()) {
                            spinner.setEnabled(true);
                        } else {
                            spinner.setEnabled(false);
                        }
                    }
        });
        seekBar.setMax(50);
        seekBar.setMin(0);
        seekBar.setProgress(0);
        seekBarTextView.setText(String.format(progressText + " %d", 0));
        spinner.setEnabled(false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if (active) {
            mStatisticsTV.setText(getString(R.string.loading));
        } else {
            mStatisticsTV.setText("");
        }
    }

    @Override
    public void showStatistics(int numberOfIncompleteTasks, int numberOfCompletedTasks) {
        activeTasks = numberOfIncompleteTasks;
        completedTasks = numberOfCompletedTasks;
        if (numberOfCompletedTasks == 0 && numberOfIncompleteTasks == 0) {
            mStatisticsTV.setText(getResources().getString(R.string.statistics_no_tasks));
        } else {
            String displayString = getResources().getString(R.string.statistics_active_tasks) + " "
                    + numberOfIncompleteTasks + "\n" + getResources().getString(
                    R.string.statistics_completed_tasks) + " " + numberOfCompletedTasks;
            mStatisticsTV.setText(displayString);
            seekBar.setProgress(numberOfCompletedTasks);
        }
    }

    @Override
    public void showSpinner(List<Task> tasks) {
        List<String> tasksList = new ArrayList<>();
        tasksList.add(getResources().getString(R.string.statistics_spinner_hint));
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                tasksList.add(tasks.get(i).getTitle());
            }
        }
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                tasksList
        );
        spinner.setAdapter(adapter);
    }

    @Override
    public void showLoadingStatisticsError() {
        mStatisticsTV.setText(getResources().getString(R.string.statistics_error));
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void showDialogWithDelay() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Statistics alert dialog")
                .setMessage("Active tasks: " + activeTasks + ", completed tasks: " + completedTasks + ".")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing - test sample
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing - test sample
                    }
                })
                .show();
    }
}
