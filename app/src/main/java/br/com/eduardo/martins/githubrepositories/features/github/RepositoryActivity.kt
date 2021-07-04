package br.com.eduardo.martins.githubrepositories.features.github

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardo.martins.githubrepositories.databinding.ActivityRepositoryBinding
import br.com.eduardo.martins.githubrepositories.features.BaseActivity
import br.com.eduardo.martins.githubrepositories.features.github.adapter.RepositoryAdapter
import br.com.eduardo.martins.githubrepositories.features.github.state.RepositoryState
import br.com.eduardo.martins.githubrepositories.utils.ViewModelFactory
import br.com.eduardo.martins.githubrepositories.utils.extensions.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryActivity : BaseActivity() {

    private lateinit var binding: ActivityRepositoryBinding

    private var isLoad: Boolean = false

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    private val viewmodel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RepositoryViewModel::class.java)
    }

    private val adapterRepository by lazy {
        RepositoryAdapter(arrayListOf())
    }

    private val state by lazy {
        RepositoryState(binding, adapterRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityRepositoryBinding.inflate(layoutInflater).let {
            binding = it
            setContentView(binding.root)
        }

        setupObservers()

        setupListeners()

        loadRepositories()
    }

    override fun onResume() {
        super.onResume()
        setupRecycler()
    }

    private fun setupObservers() {
        viewmodel.showProgressEvent.observe(this, Observer { state.showProgress() })
        viewmodel.hideProgressEvent.observe(this, Observer { hideProgress() })
        viewmodel.showMessageEvent.observe(this, Observer { showMessage(it) })
        viewmodel.screenStateEvent.observe(this, Observer { state.setState(it) })
    }

    private fun setupListeners() {
        binding.includerRepositoryConnectionError.buttonConnectionErrorTryAgain.setOnClickListener { loadRepositories() }
    }

    private fun hideProgress() {
        isLoad = false
        state.hideProgress()
    }

    private fun loadRepositories() {
        launch { viewmodel.retrieveRepositories() }
    }

    private fun setupRecycler() {
        binding.recyclerViewRespository.setup(context = this, adapter = adapterRepository)
        binding.recyclerViewRespository.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoad) {
                    val manager = recyclerView.layoutManager as LinearLayoutManager?
                    if (manager != null && manager.findLastVisibleItemPosition() == adapterRepository.itemCount - 1) {
                        isLoad = true
                        loadRepositories()
                    }
                }
            }

        })
    }

}