package com.example.lesson6.presentation.ui.example

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.lesson6.R
import com.example.lesson6.data.responsemodel.ResponseStates
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ExampleFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        ExampleViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    @Inject
    lateinit var adapter: ExampleAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        recyclerView.adapter = adapter
        viewModel.login()
        viewModel.exampleLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResponseStates.Success -> {
                    Toast.makeText(context, result.data.profile.name, Toast.LENGTH_SHORT).show()
                }
                is ResponseStates.Failure -> {
                    Toast.makeText(context, result.e.message, Toast.LENGTH_SHORT).show()
                }
                is ResponseStates.Loading -> {
                    Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                }
            }

        }
        adapter.items.addAll(listOf("1", "2", "3", "4", "5", "6", "7"))

    }
}