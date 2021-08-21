# Theme in Compose


## Material Desing + Theme

- Jetpack Compose offers an implementation of `MaterialDesign` .
- The Material Design Components (*like Buttons, Cards, Switches etc.,) are built on top of Material Themeing

  ```kotlin
    fun MaterialTheme(
        colors: Colors = MaterialTheme.colors,
        typography: Typography = MaterialTheme.typography,
        shapes: Shapes = MaterialTheme.shapes,
        content: @Composable () -> Unit
    ){}

    /******************
     * Define Colors + Color Palette
     *****************/
    val Red200 = Color(0xfff297a2)
    val Red300 = Color(0xffea6d7e)
    val Red700 = Color(0xffdd0d3c)
    val Red800 = Color(0xffd00036)
    val Red900 = Color(0xffc20029)

    /**
     * Define Light Color Pallette 
     */
    val ThemeLightColors =  lightColors(
        primary = Red700,
        primaryVariant = Red900,
        onPrimary = Color.White,
        secondary = Red700,
        secondaryVariant = Red900,
        onSecondary = Color.White,
        error = Red800
    )

    /**
     * Define Dark Color Palette
     */

    val ThemeCLDarkColors: Colors = darkColors(
        primary = Red300,
        primaryVariant = Red700,
        onPrimary = Color.Black,
        secondary = Red300,
        onSecondary = Color.Black,
        error = Red200
    )

    /**************************
     * Define Font + Typography
     ***************************/
     
    /**
     * https://fonts.google.com/specimen/Montserrat
     *
     * DEV-NOTE: It's Important to ensure that there is
     * "only one `Font` object with a specific FontWeight".
     * Having multiple Font objects with same weight will cause Preview Rendering issues.
     */
    private val Montserrat = FontFamily(
        Font(R.font.montserrat_regular, FontWeight.Normal),
        Font(R.font.montserrat_medium, FontWeight.Medium),
        Font(R.font.montserrat_semi_bold, FontWeight.Bold)
    )

    /**
     * Downloaded from https://fonts.google.com/specimen/Domine
     */
    private val Domine = FontFamily(
        Font(R.font.domine_regular, FontWeight.Normal),
        Font(R.font.domine_medium, FontWeight.Medium),
        Font(R.font.domine_bold, FontWeight.Bold)
    )

    val ThemeTypography = Typography(
        h4 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 30.sp
        ),
        h5 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp
        ),
        h6 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = Companion.W600,
            fontSize = 20.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp
        ),
        body1 = TextStyle(
            fontFamily = Domine,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = Montserrat,
            fontSize = 14.sp
        ),
        button = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        overline = TextStyle(
            fontFamily = Montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 12.sp
        )
    )

    /*************************
     * Define Shapes for small,medium and large widgets
     **************************/

    val ThemeShapes = Shapes(
        small = CutCornerShape(topStart = 8.dp),
        medium = CutCornerShape(topStart = 24.dp),
        large = RoundedCornerShape(8.dp)
    )

     /**
      * Now instantiate the `MaterialTheme` composable with
      *  - color (if system dark them "DarkColors" else "LightColors")
      *  - typography
      *  - shape 
      */
  ```

- `MaterialTheme` composable internally creates a `CompositionLocalProvider` that provides following `compositionLocals`:    
    1. `LocalColors`
    2. `LocalTypography`
    3. `LocalShapes`
    4. `LocalContentAlpha`
    5. `LocalIndication`
    6. `LocalRippleTheme`
    7. `LocalTextSelectionColors`

    ```kotlin
      @Composable
      fun MaterialTheme(
          colors: Colors = MaterialTheme.colors,
          typography: Typography = MaterialTheme.typography,
          shapes: Shapes = MaterialTheme.shapes,
          content: @Composable () -> Unit
      ) {
             //... some code
             CompositionLocalProvider(
                    LocalColors provides rememberedColors,
                    LocalContentAlpha provides ContentAlpha.high,
                    LocalIndication provides rippleIndication,
                    LocalRippleTheme provides MaterialRippleTheme,
                    LocalShapes provides shapes,
                    LocalTextSelectionColors provides selectionColors,
                    LocalTypography provides typography
            ) {
                ProvideTextStyle(value = typography.body1, content = content)
                //LocalTextStyle.curent becomes body1 by default
            }
             //... more code
      }
    ```
  

## Surface + Content Colors (Working with Colors)

```kotlin
    Surface(
        color: Color = MaterialTheme.colors.surface,
        contentColor: Color = contentColorFor(color),
    ){}

    TopAppBar(
        backgroundColor: Color = MaterialTheme.colors.primarySurface,
        contentColor: Color = contentColorFor(backgroundColor),
    ){}
 ```

- [contentColorOf][contentColorOf] retrieves the appropriate "on" color for any theme colors.   
   Eg.,
     | Color  | On Color | 
     | --- |  --- |
     | Primary | onPrimary| 
     | Secondary | onSecondary|
     | Error     | onError | 

     So the resultant usage will be like below

     ```kotlin
     @Composable
     fun CustomCompoent() {
        Surface(color = MaterialTheme.colors.primary) {
            Text(...) // default text color is 'onPrimary'
        }
        Surface(color = MaterialTheme.colors.error) {
            Icon(...) // default tint is 'onError'
        }
    }
     ```

 - When setting `color` of any element, prefer using `Surface` .
 - To retrieve color that `contrasts` with current background prefer
   using the combination of [LocalContentColor][LocalContentColor] and 
   [CompositionLocal][CompositionLocal].

    ```kotlin
        /**
        *  Text Content Alpha is medium in this sub-tree unless
        *  `Text(color=) is specified`
        */
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = text,
                /**
                * Content Color shouldn't be explicitly specified
                * as we have already asked the Text Component
                * to pick it from Local Provider
                */
                /*color = Color.Black,*/
                style = style,
                modifier = modifier
            )
        }
    ```

    >  Material Design recommends employing `different levels of opacity` to convey  different importance levels of contents.  
   
    > A common Material Design pattern is to color a **container** `primary color` in *light theme* and `surface color` in *dark themes*

      ```kotlin
        //  "MaterialTheme.colors.primarySurface" follows this guidance.
      ```


## Working with Text

### Composables involving Text

| Composable | Usage | 
|  -- | -- |
| Text | Display Text |
| TextField | Text Input |
| OutlinedTextField | Text Input |

- `TextStyle` - used to apply `single style` to our text
- `AnnotatedString` - used for applying `multiple styles` to text
- `ProvideTextStyle` internally creates a `ComposistionLocalProvider` that provides `LocalTextStyle`.
  
  ```kotlin
    @Composable
    fun ProvideTextStyle(
        value: TextStyle,
        content: @Composable () -> Unit
        ) {
            val mergedStyle = LocalTextStyle.current.merge(value)

            CompositionLocalProvider(
                LocalTextStyle provides mergedStyle,
                content = content
            )
    }

    // LocalTextStyle provided by  "ComposistionLocalProvider" can be accessed in the sub-tree
    // as LocalTextStyle.current
    @Composable
    fun Text(
        // many, many parameters
        style: TextStyle = LocalTextStyle.current // get the value set by ProvideTextStyle
    )
  ```

## Working with Shapes

- `Surface` has a `shape` parameter with which we can create how the outline is rendered.

- Few of the default `@Composable`s take the `LocalShapes.current (MaterialTheme.shapes)` and pick the suitable `Shape` from theme
  
    ```kotlin
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Button(
        //many parameters...
        shape: Shape = MaterialTheme.shapes.small,
        //many other parameters...
        content: @Composable RowScope.() -> Unit
    ) {
         Surface(
             //many parmeters...
             shape = shape /*picks the shape that is passed*/,
             //many other parameters...
         ) {
             CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha){
                 ProvideTextStyle(
                    value = MaterialTheme.typography.button
                ) {
                    Row(
                          horizontalArrangement = Arrangement.Center,
                          verticalAlignment = Alignment.CenterVertically,
                          /**
                           * - content is rendered on a "Surface" shaped with the passed `shape` param.
                           * - content is passed a "LocalContentAlpha" and "LocalTextStyle" 
                           */
                          content = content ,
                    )
                }
             }
         }

    }
    ```

- `Overriding the LocalShape` 

   ```kotlin
        @Composable
        fun FilledTextField(
            // other parameters
            shape: Shape = MaterialTheme.shapes.small.copy(
                bottomStart = ZeroCornerSize, // overrides small theme style
                bottomEnd = ZeroCornerSize // overrides small theme style
            )
        ) {

        }
   ```

   The corresponding Composable UI may look like below:  
    ![TextField with Overriden Corner shapes][theme_shape_override]


###  Composables / Modifiers involving Shape

- `Surface` Composable
- `Modifier.clip` - Clips the content to Shape
- `Modifier.background` - Draws a Shape with passes solid color behind the content.
- `Modifier.border` - Modify element to add border with appearance specified with a `border` and a `shape` and **clip it**.
- etc., 


## Summary

- `Compose` does not offer an explicit way to extract the styling of a component like Android View styles or css styles.  

- Instead, we need to create `our own library of customized components` and use these throughout our app.  
We can achieve this by  creating our own components that `wraps and customizes` a library component like below: 
    ```kotlin
        @Composable
        fun AcmeButton(
        // expose Button params consumers should be able to change
        ) {
        val acmeButtonShape: Shape = ...
        Button(
            shape = acmeButtonShape,
            // other params
        )
        }
    ```

----

[contentColorOf]: https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#contentColorFor(androidx.compose.ui.graphics.Color)
  
[LocalContentColor]: https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#LocalContentColor()

[CompositionLocal]: https://developer.android.com/reference/kotlin/androidx/compose/runtime/CompositionLocal

[theme_shape_override]: art/theme_shape_override.png