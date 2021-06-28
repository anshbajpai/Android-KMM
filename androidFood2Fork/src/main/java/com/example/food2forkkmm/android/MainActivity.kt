package com.example.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.example.food2forkkmm.android.presentation.navigation.Navigation
import com.example.food2forkkmm.datasource.network.KtorClientFactory
import com.example.food2forkkmm.datasource.network.RecipeServiceImpl
import com.example.food2forkkmm.datasource.network.model.RecipeDto
import com.example.food2forkkmm.datasource.network.toRecipe
import com.example.food2forkkmm.domain.util.DatetimeUtil
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
        }

    }
}
