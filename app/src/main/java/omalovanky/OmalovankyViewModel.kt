package omalovanky
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OmalovankyViewModel(private val repository: OmalovankyRepository) : ViewModel() {

    val omalovankyy = repository.omalovankyy.asLiveData()

    fun delete(omalovanky: Omalovanky) {
        viewModelScope.launch {
            repository.delete(omalovanky)
        }
    }

    class OmalovankyViewModelFactory(private val repository: OmalovankyRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OmalovankyViewModel::class.java)) {
                return OmalovankyViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}
