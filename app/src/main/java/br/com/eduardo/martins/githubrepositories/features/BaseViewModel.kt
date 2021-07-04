package br.com.eduardo.martins.githubrepositories.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.eduardo.martins.githubrepositories.domain.exception.MyHttpErrorException
import br.com.eduardo.martins.githubrepositories.domain.exception.MyIOException
import br.com.eduardo.martins.githubrepositories.utils.SingleLiveEvent


abstract class BaseViewModel : ViewModel() {

    protected val showProgressLiveEvent = SingleLiveEvent<Void>()
    val showProgressEvent: LiveData<Void> get() = showProgressLiveEvent

    protected val hideProgressLiveEvent = SingleLiveEvent<Void>()
    val hideProgressEvent: LiveData<Void> get() = hideProgressLiveEvent
    
    protected val screenStateLiveEvent = SingleLiveEvent<ScreenState>()
    val screenStateEvent: LiveData<ScreenState> get() = screenStateLiveEvent

    protected val showMessageLiveEvent = SingleLiveEvent<String>()
    val showMessageEvent: LiveData<String> get() = showMessageLiveEvent

    /**
     * This method should be used when we the view model is asked to do some long running task.
     * Because we're running a long task, the user should see a progress indicator, and when the
     * job completes we need to remove the progress. This method is useful to avoid duplication of
     * this process
     *
     * param[work] a function to be executed between the showProgress and hideProgress events
     */
    protected suspend fun doWorkWithProgress(work: suspend () -> Unit) {

        showProgressLiveEvent.call()
        try {
            work()
        } catch (e: Exception) {
            handleProgressException(e)
        } finally {
            hideProgressLiveEvent.call()
        }
    }
    
    protected fun handleProgressException(e : Exception) {
        when(e) {
            is MyIOException -> screenStateLiveEvent.value = NetworkError
            is MyHttpErrorException -> screenStateLiveEvent.value = HttpError
            else -> showMessageLiveEvent.value = e.message
        }
    }

}