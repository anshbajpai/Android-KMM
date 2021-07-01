package com.example.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.example.food2forkkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<String>?
){
    dialogQueue?.peek()?.let { 
        GenericDialog(title = "Error",description = it)
    }
}