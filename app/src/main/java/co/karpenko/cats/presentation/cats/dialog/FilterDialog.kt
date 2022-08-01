package co.karpenko.cats.presentation.cats.dialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.karpenko.cats.R
import co.karpenko.cats.common.lifecycle.observe
import co.karpenko.cats.presentation.cats.CatsFilterViewModel
import co.karpenko.cats.presentation.cats.catsadapter.CatsFilterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cats_filter.*

/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */

@AndroidEntryPoint
class FilterDialogFragment : DialogFragment(R.layout.fragment_cats_filter) {

    private val viewModel: CatsFilterViewModel by viewModels()

    private val catsFilterAdapter = CatsFilterAdapter(::onClick)
    private val callback get() = (parentFragment as? Callback) ?: activity as? Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catsListFilter.layoutManager = LinearLayoutManager(context)
        catsListFilter.setHasFixedSize(true)
        catsListFilter.adapter = catsFilterAdapter

        catsListFilter.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = catsFilterAdapter
        }

        observe(viewModel.event) {
            when (it) {
                is CatsFilterViewModel.Event.Done -> {
                    catsFilterAdapter.submitList(it.itemFilter)
                }
                is CatsFilterViewModel.Event.Error -> Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun onClick(filter: String?) {
        callback?.onPostFilteredItem(filter ?: "")
        dismissAllowingStateLoss()
    }


    companion object {
        fun show(fragmentManager: FragmentManager) {
            FilterDialogFragment().run { show(fragmentManager, tag) }
        }
    }

    interface Callback {

        fun onPostFilteredItem(id: String)
    }
}
