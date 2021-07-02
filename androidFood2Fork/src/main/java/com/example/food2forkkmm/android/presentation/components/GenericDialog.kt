package com.example.food2forkkmm.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food2forkkmm.domain.model.NegativeAction
import com.example.food2forkkmm.domain.model.PositiveAction

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadFromQueue: () -> Unit,
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismiss?.invoke()
            onRemoveHeadFromQueue()
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h2
            )
        },
        text = {
            if(description != null){
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if(negativeAction != null){
                    Button(
                        onClick = {
                            negativeAction.onNegativeAction()
                            onRemoveHeadFromQueue()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = negativeAction.negativeBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
                if(positiveAction !=null){
                    Button(
                        onClick = {
                            positiveAction.onPositiveAction()
                            onRemoveHeadFromQueue()
                                  },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = positiveAction.positiveBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }

            }
        }


    )
}