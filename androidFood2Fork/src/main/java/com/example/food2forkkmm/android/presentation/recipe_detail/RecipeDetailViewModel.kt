package com.example.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkkmm.domain.model.GenericMessageInfo
import com.example.food2forkkmm.domain.model.PositiveAction
import com.example.food2forkkmm.domain.model.Recipe
import com.example.food2forkkmm.domain.model.UIComponentType
import com.example.food2forkkmm.domain.util.DatetimeUtil
import com.example.food2forkkmm.domain.util.GenericMessageInfoQueueUtil
import com.example.food2forkkmm.domain.util.Queue
import com.example.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.example.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.example.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import com.example.food2forkkmm.presentation.recipe_list.RecipeListEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
): ViewModel()
{

    val state:MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId))
            }

        }

    }

    fun onTriggerEvent(event: RecipeDetailEvents){
        when(event){
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(event.recipeId)
            }
            is RecipeDetailEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description("Invalid Event")
                )
            }
        }
    }

    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId).collectCommon(viewModelScope) { dataState ->

            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let {
                state.value = state.value.copy(recipe = it)
            }

            dataState.message?.let {
                appendToMessageQueue(it)
            }

        }
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder){
        if(!GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue, messageInfo = messageInfo.build()
            )){
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }

    }

    private fun removeHeadMessage(){
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf()))
            state.value = state.value.copy(queue = queue)
        }catch (e: Exception){

        }
    }

}