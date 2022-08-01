package co.karpenko.cats.presentation.cats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.karpenko.cats.R
import co.karpenko.cats.presentation.cats.catsadapter.CatsListAdapter
import co.karpenko.cats.presentation.cats.dialog.FilterDialogFragment
import co.karpenko.cats.presentation.cats_details.CatsDetailsActivity.Companion.launch
import co.karpenko.cats.presentation.widgets.ProgressStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_cats.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */

@AndroidEntryPoint
class CatsActivity : AppCompatActivity(), FilterDialogFragment.Callback {

    private val viewModel: CatsViewModel by viewModels()

    private val catsAdapter = CatsListAdapter(::onClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cats)

        listCats.layoutManager = LinearLayoutManager(this)
        listCats.setHasFixedSize(true)
        listCats.adapter = catsAdapter

        listCats.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = catsAdapter.withLoadStateFooter(
                footer = ProgressStateAdapter()
            )
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.catsData.collectLatest {
                catsAdapter.submitData(it)
            }
        }

        btFilter.setOnClickListener {
            FilterDialogFragment.show(fragmentManager = supportFragmentManager)
        }
    }

    private fun onClick(catId: String?) = catId?.let { launch(it, this) }


    override fun onPostFilteredItem(id: String) = viewModel.setFilter(id)


    companion object {
        fun launch(context: Context) = context.startActivity(Intent(context, CatsActivity::class.java))
    }
}
