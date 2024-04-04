package com.huntams.listproducts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.huntams.data.base.ResultLoader
import com.huntams.listproducts.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private val binding by viewBinding(FragmentListBinding::bind)
    private val viewModel by viewModels<ListViewModel>()

    @Inject
    lateinit var productsAdapter: ProductsAdapter

    private val banners by lazy {
        BannersAdapter(List(Random.nextInt(1, 10)) {
            UUID.randomUUID().toString()
        })
    }

    private var category= ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerViewBanners.apply {
            adapter = banners
        }
        binding.recyclerViewProducts.adapter = productsAdapter.apply {
            setCallback {
                Log.e("error", it.throwable.message.toString())
                toastError {
                    viewModel.getProducts()
                    viewModel.getCategories()
                }
            }
        }

        binding.recyclerViewProducts.visibility = View.VISIBLE
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultLoader.Success -> {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        result.value.category.forEach {
                            chipGroup.addView(createTagChip(it.strCategory))
                        }
                        category =
                            binding.chipGroup.findViewById<Chip>(binding.chipGroup.children.toList()[0].id)?.text.toString()
                        chipGroup.check(binding.chipGroup.children.toList()[0].id)
                    }
                }

                is ResultLoader.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultLoader.Failure -> {
                    Log.e("error", result.throwable.message.toString())
                    toastError { viewModel.getProducts()
                        viewModel.getCategories() }
                }

                else -> {}
            }
        }
        viewModel.mealsLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultLoader.Success -> {
                    with(binding) {
                        progressBar.visibility = View.GONE
                        if (category.isNotBlank()) {
                            productsAdapter.submitList(viewModel.getProductByCategory(category))
                        }
                    }
                }

                is ResultLoader.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultLoader.Failure -> {
                    Log.e("error", result.throwable.message.toString())
                    toastError { viewModel.getProducts()
                        viewModel.getCategories()}
                }

                else -> {}
            }
        }
        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedId ->
            if (checkedId.isNotEmpty()) {
                category = binding.chipGroup.findViewById<Chip>(checkedId[0])?.text.toString()
                when (viewModel.mealsLiveData.value) {
                    is ResultLoader.Success -> {
                        productsAdapter.submitList(viewModel.getProductByCategory(category))
                    }

                    else -> {}
                }
            } else
                binding.chipGroup.check(binding.chipGroup.children.toList()[0].id)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun toastError(block: () -> Unit) {
        binding.progressBar.visibility = View.VISIBLE
        val mySnackbar = Snackbar.make(
            binding.root,
            getString(R.string.internet_connection),
            Snackbar.LENGTH_INDEFINITE
        )
        mySnackbar.setAction(getString(R.string.reload)) {
            block()
        }
        mySnackbar.show()
    }

    private fun createTagChip(chipName: String): Chip {
        return (layoutInflater.inflate(
            R.layout.item_chip_categories,
            binding.chipGroup,
            false
        ) as Chip).apply {
            text = chipName
        }

    }
}