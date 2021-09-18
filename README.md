# Jetpack Compose Experiments

## Building Blocks

1. **Composable Functions**

  - A regular function annotated with `@Composable`
  - Can call other `@Composable` functions within it.

2. **Preview Composable Functions**

  - Used to preview the UI in Android Studio
  - Can mark any `parameterless` *Composable* Functions (or) Composable Functions with `default parameters`

    ```kotlin
      @Preview(showBackground = true, name= "DefaultTextPreview")
      @Composable
      fun DefaultText(text: String= "Default Text"){
          Text(text)
      }
    ```
    ![Default Text Preview][art_default_text_preview]

> - androidx.compose.* contains compiler and runtime classes
> - androidx.ui.* contains UI toolkit and libraries
> - **Composable functions can only be called from within the scope of other composable functions**.

### Composable Function Traits
> Think of `@Composable` functions as a **language feature** rather than being a part of **compose toolkit**.
- **Composable** functions has the ability to `re-compose`.
- **Composable** functions have a `lifecycle`.
- **Composable** functions can be `re-invoked`.
> Since they have a `lifecycle` and can be `re-invoked` , they have memory

### Modifiers

Modifiers allow us to **decorate** a composable.  

- The possible decorationsa are
  1. Change Composaable's behaviour
  2. Change it's appearence
  3. Add informations like accesibility labels
  4. Process User Input
  5. High-Level interactions like making something **clickable** , **scrollable**, **draggable** , **zoomable**.
- We can chain Modifiers
- Modifiers are **applied in the order in which we chain them**.

> By convention, the modifier is specified as the **first optional parameter** of a function.  
> This enables you to specify a modifier on a composable without having to name all parameters.

### Problems + Solutions


#### Jetpack Compose Preview is not visible
   
1. The `compose-compiler` and `kotlin` versions are incompatible
2. You don't have an `@Preview` annotated @Composable in the corresponding Source File  
3. Android Studio is not compatible with a particular version of `androidx.compose.ui:ui-tooling` .
     
     ```kotlin
     val compose_version = '1.0.0-rc02'
     implementation("androidx.compose.ui:ui-tooling:$compose_version") {
        version {
            // TODO: Remove this when Android Studio has become compatible again
            // Android Studio Bumblebee | 2021.1.1 Canary 3 is not compatible with module ui-tooling 1.0.0-rc01 or higher.
            // The Run Configuration for Composable Previews that Android Studio makes expects a PreviewActivity class
            // in the `androidx.compose.ui.tooling.preview` package, but it was moved in 1.0.0-rc01, and thus causes error:
            // "androidx.compose.ui.tooling.preview.PreviewActivity is not an Activity subclass or alias".
            // For more, see: https://stackoverflow.com/questions/68224361/jetpack-compose-cant-preview-after-updating-to-1-0-0-rc01
            strictly("1.0.0-beta09")
        }
    }
     ```

## Compose Concepts 

### Recomposing 

Observe Changes in App Data and automatically **re-call** the impacted **Compose Functions**.

### States In Compose

```kotlin
@Composable
fun StateDemoCounter() {
    val counter = remember { mutableStateOf(0) }
    Button(onClick = { counter.value++ }) {
        Text("${counter.value}")
    }
}

@Composable
fun StateDemoCounter1() {
    val (counter, setCounter) = remember { mutableStateOf(0) }
    Button(onClick = { setCounter(counter + 1) }) {
        Text("$counter")
    }
}

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun StateDemoCounter2() {
    var counter by remember { mutableStateOf(0) }
    Button(onClick = { counter++; }) {
        Text("$counter")
    }
}
```

- `mutableStateOf` gives a **Composable** __mutable memory__. When this mutable memory is changed, **re-composition** happens.
- `remember` only invokes the passed lambda during **composition** and returns the same value during every **re-composition**.

### Composition Locals

- `CompositionLocalProvider`  provides a Composition local to the **sub-tree**.
- `compositionLocalOf` creates a Composition local. 
- `LocalContentColor` gives you the preferred color for content such as Icons and Typography.  
It is changed by composables such as Surface that draw a background.

### Slot APIs

  A **pattern** compose uses to bring a layer of customization of top of `Composables`.  
  Slots leave an **empty space in the UI** for the developer to fill as they wish.

-  Eg., Button Composable has left the inside of the button to be filled by us.  
  `Slot`    
  ![Button Slot][art_button_slot]  
  ```kotlin
  Button(
      onClick = {},
      modifier = Modifier.align(Alignment.CenterHorizontally)
  ) {
      Icon(
          imageVector = Icons.Filled.Menu,
          contentDescription = null,
          modifier = Modifier.size(25.dp)
      )
      Spacer(Modifier.size(4.dp))
      Column {
          Text("Button")
          CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
              Text("subtitle", style = MaterialTheme.typography.caption)
          }
      }
  }
  ```
  `Slot Filled`  
  ![Button Slot Filled][art_button_slot_filled]

-  Top App Bar  
   `Slot`   
   ![Top AppBar Slot][art_topappbar_slot]
   ```kotlin
     TopAppBar(
      title = {
          Text(text = "Page title", maxLines = 2)
      },
      navigationIcon = {
          Icon(myNavIcon)
      }
    )
   ```
   `Slot Filled`  
   ![Top AppBar Slot Filled][art_topappbar_slot_filled]



 ## CodeLab Docs

 - [Layouts in Compose](./compose-codelabs/Layouts.md)
 - [States in Compose](./compose-codelabs/States.md)
 - [Themes in Compose](./compose-codelabs/Themes.md)

## Dependencies + Compatibility 

|     Android Studio Version |     Gradle Version    |  Android Build tools - Gradle PLugin | Kotlin Gradle Plugin |   JDK Version   |  Compose Version    |
|           -----            |           -----       |           -----               |     -----       |           -----     | --- |
| Android Studio Artic Fox - `2020.3.1` | gradle-`6.8.2`-bin.zip |     com.android.tools.build:gradle:`7.0.0-alpha09`     | org.jetbrains.kotlin:kotlin-gradle-plugin:`1.4.30` | jdk-`11.0.10`    |     `1.0.0-beta01`    |
| Android Studio Artic Fox - `2020.3.1` | gradle-`7.0.2`-bin.zip | com.android.tools.build:gradle:`7.0.0` | org.jetbrains.kotlin:kotlin-gradle-plugin:`1.5.10` | jdk-`11.0.10` | `1.0.0` (**First Stable Release**) |
### References

- [Jetpack Compose - Pathway][jetpack_compose_pathway]
- [Jetpack Compose - Setup][jetpack_compose_setup]
- [Jetpack Compose - Library Structure][jetpack_compose_library_structure]
- [Jetpack Compose - Github Repo][jetpack_compose_github_repo]
- [Jetpack Compose - Basics][jetpack_compose_basics_codelab]
- [Jetpack Compose - State][jetpack_compose_state_codelab]
- [Jetpack Compose - Samples][jetpack_compose_samples]
- [Google Codelabs - Github][android_compose_codelabs_code]
- [Jetpack Compose App - Collection Most Popular Tools & Demos][jetpack_compose_app]
- [Jetpack Compose Official Docs](https://developer.android.com/jetpack/compose/documentation)

### Blogs
- [Simple Android Animation][blogs_simple_android_animation]
- [Expandable Lists in Jetpack Compose](https://proandroiddev.com/expandable-lists-in-jetpack-compose-b0b78c767b4)
- [Creating a Repeating Button](https://proandroiddev.com/creating-a-repeating-button-with-jetpack-compose-b39c4f559f7d)
- [InteractionSource, Ripple and Stuff](https://proandroiddev.com/jetpack-compose-interactionsources-the-ripple-effect-and-you-f451b60fcd37)
- [Shopping Cart - Bubble Shape](https://twitter.com/HandstandSam/status/1426602112599920644?ref_src=twsrc%5Etfw)

---
[jetpack_compose_setup]: https://developer.android.com/jetpack/compose/setup
[jetpack_compose_pathway]: https://developer.android.com/courses/pathways/compose
[jetpack_compose_samples]: http://goo.gle/compose-samples
[jetpack_compose_codelabs]: http://goo.gle/compose-codelabs
[jetpack_compose_docs]: http://goo.gle/compose-docs
[jetpack_compose_feedback]: http://goo.gle/compose-feedback
[jetpack_compose_slack]: http://goo.gle/compose-slack

[jetpack_compose_basics_codelab]: https://codelabs.developers.google.com/codelabs/jetpack-compose-basics/
[jetpack_compose_state_codelab]: https://developer.android.com/codelabs/jetpack-compose-state
[android_compose_codelabs_code]: https://github.com/googlecodelabs/android-compose-codelabs
[jetpack_compose_library_structure]: https://developer.android.com/jetpack/androidx/releases/compose-compiler#structure
[jetpack_compose_github_repo]: https://github.com/androidx/androidx
[art_default_text_preview]: demo/art/default_text_preview.png "Default Text Preview"
[jetpack_compose_app]: https://www.jetpackcompose.app

[blogs_simple_android_animation]: https://medium.com/mobile-app-development-publication/simple-android-animation-with-jetpack-compose-6eb718928f9b

[art_button_slot]: art/button_slot.png "Button Slot"
[art_button_slot_filled]: art/button_slot_filled.png "Button Slot - Filled"
[art_topappbar_slot]: art/topappbar_slot.png "Top AppBar Slot"
[art_topappbar_slot_filled]: art/topappbar_slot_filled.png "Top AppBar Slot - Filled"
