package co.karpenko.cats.presentation.cats_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import co.karpenko.cats.R
import co.karpenko.cats.common.lifecycle.observe
import co.karpenko.cats.data.mapper.Cat
import co.karpenko.cats.presentation.cats.Event
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_cat_details.*

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@AndroidEntryPoint
class CatsDetailsActivity : AppCompatActivity() {

    private val viewModel: CatsDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_details)

        val catsId = intent.getStringExtra(catsId) ?: ""
        viewModel.getCatsDetails(catsId)

        observe(viewModel.event) {
            when (it) {
                is Event.EmptyDetails -> {
                    Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
                }
                is Event.CatDetailsEvent -> {
                    showCatDetails(it.catDetails)
                }
            }
        }
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun showCatDetails(cat: Cat) {
        tvName.text = cat.name
        tvDescription.text = cat.description
        tvTemperament.text = cat.temperament
        tvWikepedia.text = cat.linkWikipedia
        Glide.with(this)
            .load(cat.linkImage)
            .circleCrop()
            .into(imgCat)
    }

    companion object {
        private const val catsId = "catsId"

        fun launch(idCat: String, context: Context) {
            return context.startActivity(
                Intent(context, CatsDetailsActivity::class.java).also {
                    it.putExtra(catsId, idCat)
                }
            )
        }
    }
}
