package com.example.senlapractice

import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.senlapractice.data.ApiFactory
import com.example.senlapractice.ui.theme.SenlaPracticeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SenlaPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        // тест запроса к серверу
        lifecycleScope.launch {
            try {
                val response = ApiFactory().movieApi.getPopularMovies()
                Log.d("TMDB", "Получено фильмов: ${response.results.size}")
                Log.d("TMDB", "Первый фильм: ${response.results.firstOrNull()?.title}")
            } catch (e: Exception) {
                Log.e("TMDB", "Ошибка запроса", e)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SenlaPracticeTheme {
        Greeting("Android")
    }
}