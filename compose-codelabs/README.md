# Compose CodeLabs

# Layouts in Compose

## Alignment

Calculate the position of a **sized-box** inside an **available space**.
> Often used to define alignment of a layout inside parent layout.

`2D-Biased  "Layout Aware" Alignments` 

|   |  |  |
| --- | --- | --- | 
| Top**Start** |  Top**Center**  | Top**End** |
| Center**Start** |  **Center**  | Center**End** |
| Bottom**Start** |  Bottom**Center**r  | Bottom**End** |

> contentAlignment is used in  **Box** Layout

`1D-Biased "Layout Aware" Vertical Alignments` 

|   |
| --- |
| Top |
| CenterVertically |
| Bottom |

> verticalAlignment is used in **Row** Layout

`1D-Biased "Layout Aware" Horizontal Alignments` 

|   |  |  |
| --- | --- | --- | 
| Start | CenterHorizontally | End |

> horizontalAlignment is used in **Column** Layout

`2D-Biased  "Layout Un-Aware" Alignments` 

|   |  |  |
| --- | --- | --- | 
| Top**Left** |  Top**Right**  |
| Center**Left** | Center**Right** |
| Bottom**Left** |  Bottom**Right**  |

`1D-Biased "Layout Un-Aware" Horizontal Alignments` 

|   |   |
| --- | --- |
| Left | Right |

---

## Arrangement

Used to specify the arrangement of the **layout's children** in the **main axis** direction.  
> Used in **Row** and **Column** Layouts.  
> **Row** main-axis is `horizontal`.  
> **Column** main-axis is ` vertical`.  

`Horizontal Arrangement Types`

|      |     |
| ---  | --- |
| Start | End | 

`Vertical Arrangement Types`

|      |
| ---  |
| Top |
| Bottom | 

`Horizontal Or Vertical Arrangement Types` 

|     |  Visual Representation | Description |
| --- |   --- | --- |
| Center| `##`123`##` | Place children such that they are as close as possible to the **middle of the main axis** |
| Space Evenly | `#`1`#`2`#`3`#` |Place children such that they are spaced **evenly across the main axis, including free space before the first child and after the last child** |
| Space Between | 1`##`2`##`3 |Place children such that they are spaced **evenly across the main axis, without free space before the first child or after the last child** |
| Space Around | `#`1`##`2`##`3`#` |Place children such that they are spaced **evenly across the main axis, including free space before the first child and after the last child, but half the amount of space existing otherwise between two consecutive children** |

---

## Content Size using `Modifiers`

### Explicitly Setting Content Size

1. `height(height: Dp)` 
2. `width(width: Dp)`
3. `size(size: Dp)`
4. `size(width: Dp, height: Dp)`

### Use all available space of `Container` 

1. `fillMaxHeight()`
2. `fillMaxWidth()`
3. `fillMaxSize()`

---

## Layouts

- `Box` - stacks every child on **top of each other**. (similar to FrameLayout ?)
- `Row` - stacks children in a **horizontal sequence** (similar to LinearLayout with horizontal orientation). 
- `Column` - stacks children in a **vertical sequence** (similar to LinearLayout with vertical orientation).
- `ConstraintLayout` - layout **positions children according to the constraints between them** (similar to Constraint Layout in android). 
- `Scaffold` - contains a basic implementation of the material design app structure with following components
    - TopBar
    - BottomBar
    - FloatingActionButton
    - Drawer  
  > BackdropScaffold layout contains basic implementation of material design [backdrop][material_component_backdrop]
 
## Custom Layouts in Compose

1. Extend `@Composable Layout` for creating a custom Layout
2. Use the `layout modifier` to manually control how to measure and position an element
   ```kotlin
        fun Modifier.customLayoutModifier(...) = Modifier.layout { measurable, co nstraints ->
       // add your code here .
    })
   ```

> Compose UI does not permit multi-pass measurement

## References

- [Alex Zhukovich's Jetpack Compose Layouts][alexzh_compose_layouts]

---
[alexzh_compose_layouts]: https://alexzh.com/jetpack-compose-layouts/
[material_component_backdrop]: https://material.io/components/backdrop