package br.com.eduardo.martins.githubrepositories.features.github.state

import br.com.eduardo.martins.githubrepositories.R
import br.com.eduardo.martins.githubrepositories.databinding.ActivityRepositoryBinding
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import br.com.eduardo.martins.githubrepositories.features.*
import br.com.eduardo.martins.githubrepositories.features.github.adapter.RepositoryAdapter
import br.com.eduardo.martins.githubrepositories.utils.extensions.hide
import br.com.eduardo.martins.githubrepositories.utils.extensions.show
import br.com.eduardo.martins.githubrepositories.utils.extensions.showSnackBar

class RepositoryState(
    private val binding: ActivityRepositoryBinding,
    private val adapter: RepositoryAdapter
) {

    fun setState(state: ScreenState) {
        when(state) {
            is RepositoryScreenState.Result -> setResults(state.result)
            is HttpError -> setHttpError()
            is NetworkError -> setNetworkError()
        }
    }

    fun showProgress() {
        binding.includeProgressBar.progress.show()
    }

    fun hideProgress() {
        binding.includeProgressBar.progress.hide()
    }

    fun setResults(result: List<Items>) {
        binding.recyclerViewRespository.show()
        binding.includerRepositoryConnectionError.connectionError.hide()
        adapter.update(result)
    }

    fun setHttpError() {
        when {
            adapter.isEmpty() -> {
                binding.includerRepositoryConnectionError.imageViewConnectionError.setImageResource(R.drawable.ic_server_error)
                binding.includerRepositoryConnectionError.textViewConnectionErrorMessage.setText(R.string.repository_error_server)
                binding.includerRepositoryConnectionError.connectionError.show()
                binding.recyclerViewRespository.hide()
            }
            else -> binding.root.showSnackBar(R.string.repository_error_server)
        }
    }

    fun setNetworkError() {
        when {
            adapter.isEmpty() -> {
                binding.includerRepositoryConnectionError.imageViewConnectionError.setImageResource(R.drawable.ic_wifi)
                binding.includerRepositoryConnectionError.textViewConnectionErrorMessage.setText(R.string.repository_internet_connection)
                binding.includerRepositoryConnectionError.connectionError.show()
                binding.recyclerViewRespository.hide()
            }
            else -> binding.root.showSnackBar(R.string.repository_internet_connection)
        }
    }

}