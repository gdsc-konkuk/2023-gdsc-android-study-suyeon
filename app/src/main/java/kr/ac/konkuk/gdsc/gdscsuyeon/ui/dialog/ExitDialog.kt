package kr.ac.konkuk.gdsc.gdscsuyeon.ui.dialog

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ExitDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("지금 나가시면 앱이 종료됩니다.")
            .setPositiveButton("Yes") { _, _ ->
                activity?.finish()
            }
            .setNegativeButton("No") { _, _ ->
            }
            .create()
    }
}