/*
* Author:       Zhencheng Chen
* Program:      Mini Project
* Course:       Mobile App Development II
* Date:         10/23/2021
* */

package com.chen.zhencheng.miniproject

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InfoDialog : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Info")
            .setMessage("Mini Project - Zhencheng Chen")
            .create()
    } //onCreateDialog
}//class