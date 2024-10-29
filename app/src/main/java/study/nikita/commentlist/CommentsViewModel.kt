package study.nikita.commentlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {

    private val apiService = ApiService.create()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments

    init {
        fetchComments()
    }

    private fun fetchComments() {
        viewModelScope.launch {
            try {
                _comments.value = apiService.getComments()
            } catch (e: Exception) {
                _comments.value = emptyList()
            }
        }
    }
}