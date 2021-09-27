package com.android.codex.others

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.android.codex.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogView: DialogFragment() {

    private var yesListener : (()->Unit)?=null

    fun setListener(listener:()->Unit){
        yesListener=listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Cancel Run")
            .setMessage("Are you sure to delete ?")
            .setIcon(R.drawable.round_delete_black_24dp)
            .setPositiveButton("Yes") { _, _ ->
                yesListener?.let { it }
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .create()
    }
}