package co.karpenko.cats.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

open class BaseDialogFragment(
    @LayoutRes private val layoutId: Int
) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.getWindow()?.setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        return inflater.inflate(layoutId, group, false)
    }
}
