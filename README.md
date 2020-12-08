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


### Problems + Solutions

- Jetpack Compose Preview is not visible
> This is probably because the `compose-compiler` and `kotlin` versions are incompatible

### References

- [Jetpack Compose - Library Structure][jetpack_compose_library_structure]
- [Jetpack Compose - Basics][jetpack_compose_basics_codelab]
- [Jetpack Compose - State][jetpack_compose_state_codelab]
- [Google Codelabs - Github][android_compose_codelabs_code]

---
[jetpack_compose_basics_codelab]: https://codelabs.developers.google.com/codelabs/jetpack-compose-basics/
[jetpack_compose_state_codelab]: https://developer.android.com/codelabs/jetpack-compose-state
[android_compose_codelabs_code]: https://github.com/googlecodelabs/android-compose-codelabs
[jetpack_compose_library_structure]: https://developer.android.com/jetpack/androidx/releases/compose-compiler#structure
[art_default_text_preview]: art/default_text_preview.png "Default Text Preview"