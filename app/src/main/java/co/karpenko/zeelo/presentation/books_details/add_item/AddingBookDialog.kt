package co.karpenko.zeelo.presentation.books_details.add_item

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import co.karpenko.zeelo.R
import co.karpenko.zeelo.common.lifecycle.observe
import co.karpenko.zeelo.domain.base.BaseDialogFragment
import co.karpenko.zeelo.presentation.books.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_adding.*

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

@AndroidEntryPoint
class AddingBookDialog : BaseDialogFragment(R.layout.dialog_adding) {

    private val viewModel: AddingBookDialogViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_Custom)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edTextPrice.doAfterTextChanged {
            viewModel.onFormPriceChanged(it?.toString()?.trim()!!)
        }

        edTitle.doAfterTextChanged {
            viewModel.onFormTitleChanged(it?.toString()?.trim()!!)
        }

        edAuthor.doAfterTextChanged {
            viewModel.onFormAuthorChanged(it?.toString()?.trim()!!)
        }

        btAdd.setOnClickListener {
            viewModel.addBookDetails()
        }

        observe(viewModel.event) {
            when (it) {
                is Event.AddedItem -> {
                    dismiss()
                }
                Event.EmptyFields -> Toast.makeText(context, R.string.empty_field, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun show(fragmentManager: FragmentManager) {
            AddingBookDialog().run {
                show(fragmentManager, tag)
            }
        }
    }
}
