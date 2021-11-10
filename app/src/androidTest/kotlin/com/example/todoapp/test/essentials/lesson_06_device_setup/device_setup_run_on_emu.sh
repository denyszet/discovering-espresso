#!/usr/bin/env bash

# set touch and hold delay to 1500 milliseconds to avoid test flakiness
settings put secure long_press_timeout 2500

#disable soft keyboard
settings put secure show_ime_with_hard_keyboard 0

# enable developer options, set stay awake to true
settings put global development_settings_enabled 1
settings put global stay_on_while_plugged_in 1

# set animation values to 0.0
settings put global animator_duration_scale 0.0
settings put global transition_animation_scale 0.0
settings put global window_animation_scale 0.0
