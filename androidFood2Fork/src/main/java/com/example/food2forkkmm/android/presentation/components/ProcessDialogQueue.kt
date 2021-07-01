package com.example.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.example.food2forkkmm.domain.model.GenericMessageInfo
import com.example.food2forkkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?
){
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            title = dialogInfo.title,
            description = dialogInfo.description,
        )
    }
}