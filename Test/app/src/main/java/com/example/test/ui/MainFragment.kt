package com.example.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.FragmentMainBinding
import com.example.test.databinding.MainItemBinding
import com.example.test.domain.AboutDomainModel
import com.example.test.viewmodels.MainViewModel

class MainFragment : Fragment() {

    private lateinit var bind: FragmentMainBinding
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "This is to initialize check if we are accessing viewmodel only after onActivityCreated()"
        }
        ViewModelProvider(this, MainViewModel.Factory(activity.application))
            .get(MainViewModel::class.java)
    }

    private var viewModelAdapter: DevByteAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.aboutList.observe(viewLifecycleOwner, { aboutList ->
            aboutList?.apply {
                viewModelAdapter?.aboutList = aboutList
                bind.swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        bind = binding
        viewModelAdapter = DevByteAdapter()

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner, { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshDataFromRepository()
        }
        return binding.root
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, getString(R.string.network_error), Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}


class AboutViewHolder(val viewDataBinding: MainItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.main_item
    }
}

class DevByteAdapter : RecyclerView.Adapter<AboutViewHolder>() {
    var aboutList: List<AboutDomainModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val withDataBinding: MainItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AboutViewHolder.LAYOUT,
            parent,
            false
        )
        return AboutViewHolder(withDataBinding)
    }

    override fun getItemCount() = aboutList.size

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.row = aboutList[position]
            if (aboutList[position].description.isEmpty()) {
                it.description.visibility = View.GONE
            } else {
                it.description.visibility = View.VISIBLE
            }

            if (aboutList[position].imageHref.isEmpty()) {
                it.thumbnail.visibility = View.GONE
            } else {
                it.thumbnail.visibility = View.VISIBLE
            }
        }
    }

}