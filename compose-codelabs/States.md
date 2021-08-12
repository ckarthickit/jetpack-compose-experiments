# State in Compose

## Uni-Directional Data Flow 

  - `State` – any value that can change over time
  - `Event` – notify a part of a program that something has happened
  - `Unidirectional data flow` - a **design** where events flow up and state flows down.

> For example, in a `ViewModel` `events are passed up` with method calls from the UI while `state flows down` using `LiveData`.

## Stateless & Stateful Composable


### Stateless Composable

- A stateless composable is a composable that `cannot directly change any state`.

- `State Hoisting`  -  a pattern of moving state up to make a component stateless.
  > When applied to composables, this often means introducing two parameters to the composable.
  > -   `value: T` – the current `value` to display
  > - `onValueChange: (T) -> Unit` – an `event` that requests the value to change, where T is the proposed new value

- Built-in composable-s are designed for unidirectional data flow. Most built-in composable-s provide `at least one stateless version for each API`. 
    > This avoids duplicated state between your application and the component. 

### Stateful Composable

- A stateful composable is a composable that `owns a piece of state that it can change over time`.

## SideEffects 

A `side-effect` is any `change that's visible outside execution of a composable function`.  
> It's basically a `Shared Mutation` . 

## Pure Functions

- Given Same Inputs -> Emits Same Outputs `All the time`.

## Recomposition

> **Recomposition** is the process of running the same composables again to update the tree when their data changes.  

> Recomposing a composable should be `side-effect free`.  

Let's consider a Composable Tree: 

```kotlin
    @Composable
    fun TodoScreen(
        items: List<TodoItem>,
        onAddItem: (TodoItem) -> Unit,
        onRemoveItem: (TodoItem) -> Unit
    ) {

        // Lazy Column of TodoRow items
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items = items) {
                TodoRow(
                    todo = it,
                    onItemClicked = { onRemoveItem(it) },
                    modifier = Modifier.fillParentMaxWidth()
                )
            }
        }

        // A Button to generate "random" TodoRow item.
        Button(
            onClick = { onAddItem(generateRandomTodoItem()) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text("Add random item")
        }
    }

    @Composable
    fun TodoRow(
        todo: TodoItem,
        onItemClicked: (TodoItem) -> Unit,
        modifier: Modifier = Modifier
        ) {
            Row(
                modifier = modifier
                    .clickable { onItemClicked(todo) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(todo.task)
                val iconAlpha = randomTint()
                Icon(
                    imageVector = todo.icon.imageVector,
                    tint = LocalContentColor.current.copy(alpha = iconAlpha),
                    contentDescription = stringResource(id = todo.icon.contentDescription)
                )
            }
    }

```

Pictorial representation of the tree looks like :

![Todo Screen + TodoRow with Side Effect][todo_screen_tree_1]

> Right now `TodoRow` has a hidden `side-effect on every recomposition` (The call to Random.nextFloat() updates the internal random variable used in a pseudo-random number generator).

### Introducing memory to composable functions

>`remember` gives a composable function memory. 

> A value computed by remember `will be stored in the composition tree`, and only be recomputed if the keys to remember change

> Values remembered in composition are `forgotten as soon as their calling composable is removed from the tree`.

TodoRow can be changed like below:  

```kotlin
@Composable
    fun TodoRow(
        todo: TodoItem,
        onItemClicked: (TodoItem) -> Unit,
        modifier: Modifier = Modifier
        ) {
            Row(
                modifier = modifier
                    .clickable { onItemClicked(todo) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(todo.task)
                /* Using remember here */
                val iconAlpha: Float = remember(todo.id) { randomTint() }
                Icon(
                    imageVector = todo.icon.imageVector,
                    tint = LocalContentColor.current.copy(alpha = iconAlpha),
                    contentDescription = stringResource(id = todo.icon.contentDescription)
                )
            }
    }
```

Now  the composistion tree looks like :

![Todo Screen + TodoRow without Side Effect][todo_screen_tree_2]

## Idempotent Composables

> An idempotent composable always produces the same result for the same inputs and has no side-effects on recomposition.  

> Composables should be `idempotent` to support recomposition.



## State Functions 

- `<T> mutableStateOf` 
- `<T> mutableStateListOf` 
- `LiveData<T>.observeAsState()` - observes a `LiveData` and returns a `State` object that is updated whenever the LiveData changes. 
  > It will automatically stop observing when the composable is removed from composition.


---
[todo_screen_tree_1]: art/todoscreen_tree_1.png "Todo Screen + TodoRow with Side Effect"
[todo_screen_tree_2]: art/todoscreen_tree_2.png "Todo Screen + TodoRow without Side Effect"