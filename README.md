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

- We can chain Modifiers
- Modifiers are **applied in the order in which we chain them**.

### Problems + Solutions

- Jetpack Compose Preview is not visible
>  1. The `compose-compiler` and `kotlin` versions are incompatible
>  2. You don't have an `@Preview` annotated @Composable in the corresponding Source File

### References

- [Jetpack Compose - Setup][jetpack_compose_setup]
- [Jetpack Compose - Library Structure][jetpack_compose_library_structure]
- [Jetpack Compose - Github Repo][jetpack_compose_github_repo]
- [Jetpack Compose - Basics][jetpack_compose_basics_codelab]
- [Jetpack Compose - State][jetpack_compose_state_codelab]
- [Jetpack Compose - Samples][jetpack_compose_samples]
- [Google Codelabs - Github][android_compose_codelabs_code]

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
[art_default_text_preview]: art/default_text_preview.png "Default Text Preview"