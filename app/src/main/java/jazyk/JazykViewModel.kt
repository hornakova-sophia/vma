package jazyk
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class JazykViewModel(private val repository: JazykRepository) : ViewModel() {

    val jazyky = repository.jazyky.asLiveData()
    suspend fun getByUUID(id: Int) {
        return repository.getByUUID(id)
    }
    fun getAllJazyky() {
        viewModelScope.launch {
            repository.getAllJazyky()
        }
    }


    fun delete(jazyk: Jazyk) {
        viewModelScope.launch {
            repository.delete(jazyk)
        }
    }

    class JazykViewModelFactory(private val repository: JazykRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(JazykViewModel::class.java)) {
                return JazykViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}