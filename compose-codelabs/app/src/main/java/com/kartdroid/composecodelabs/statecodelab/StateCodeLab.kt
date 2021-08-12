package com.kartdroid.composecodelabs.statecodelab

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kartdroid.composecodelabs.databinding.StateCodeLabConventionalBinding
import com.kartdroid.composecodelabs.theme.LightColorPalette

/************************ CONVENTIONAL ***********************/

class StateCodeLabConventional : AppCompatActivity() {
    private lateinit var viewBinding: StateCodeLabConventionalBinding
    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = StateCodeLabConventionalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupViews()
        supportActionBar?.title = "StateCodeLabConventional"
    }

    private fun setupViews() {
        // doAfterTextChange is an event that modifies state
        viewBinding.helloTextInput.doAfterTextChanged {
            name = it.toString()
            updateHelloText()
        }
    }


    private fun updateHelloText() {
        viewBinding.helloText.text = "Hello $name"
    }
}

/**************************************** Use MVVM ***********************/

class HelloViewModel : ViewModel() {
    private val _nameState = MutableLiveData<String>()
    val nameState: LiveData<String> = _nameState

    fun onNameChanged(name: String) {
        _nameState.value = name
    }
}


class StateCodeLabWithMVVM : AppCompatActivity() {
    private lateinit var viewBinding: StateCodeLabConventionalBinding
    private val viewModel by viewModels<HelloViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = StateCodeLabConventionalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupViews()
        supportActionBar?.title = "StateCodeLabWithMVVM"
    }

    fun setupViews() {
        viewBinding.helloTextInput.doAfterTextChanged {
            viewModel.onNameChanged(it.toString())
        }
        viewModel.nameState.observe(this) { name ->
            viewBinding.helloText.text = "Hello $name"
        }
    }
}

class StateCodeLabMVVMWithCompose: AppCompatActivity() {
    private val viewModel by viewModels<HelloViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "StateCodeLabMVVMWithCompose"
        setContent {
            HelloScreen(viewModel)
        }
    }

    @Composable
    fun HelloScreen(viewMode: HelloViewModel) {
        val state by viewModel.nameState.observeAsState("")
        HelloScreenContent(name = state, onNameChanged = {
            viewModel.onNameChanged(it)
        })
    }

    @Composable
    fun HelloScreenContent(
        name: String,
        onNameChanged: (String) -> Unit
    )  {
        MaterialTheme(
            colors = LightColorPalette
        ) {
            Surface(
                color = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(
                        modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                            .border(
                                2.dp,
                                MaterialTheme.colors.secondary,
                                RoundedCornerShape(25.dp)
                            )
                            /**
                             * This padding is inserted after
                             * border hence , it creates a space
                             * between the Text and border
                             * */
                            .padding(16.dp) ,
                        text = if(name.isBlank()) { "Type Something" } else "Hello $name"
                    )
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        /** Keeping this empty will not accumulate the characters.
                         *  Instead only the last character typed is shown on "Text" component
                         *  "TextField" never sees any character inside.
                         *  */
                        value = name,
                        onValueChange = {
                            println("change = $it")
                            onNameChanged(it)
                                        },
                        label = { Text(text = "Pleas Enter Some Text")}
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun HelloScreenPreview() {
        HelloScreenContent("Hello World") {}
    }
}

