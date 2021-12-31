package org.tapsell.app.ui.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    protected val TAG = this::class.java.name
    protected val job = Job()
    protected val uiScope = CoroutineScope(Job() + Dispatchers.Main)
    private val rootView get() = requireActivity().window.decorView.rootView

    private var snackBar: Snackbar? = null


    protected fun toast(message: String, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(requireContext(), message, length).show()

    protected fun snack(message: String, length: Int = Snackbar.LENGTH_SHORT) =
        Snackbar.make(rootView, message, length).show()

    protected fun snack(@StringRes message: Int, length: Int = Snackbar.LENGTH_SHORT) =
        Snackbar.make(rootView, message, length).show()


    private fun getMethodName() = object {}.javaClass.enclosingMethod?.name.toString()

    override fun onPause() {
        super.onPause()
        snackBar?.dismiss()
    }

}
