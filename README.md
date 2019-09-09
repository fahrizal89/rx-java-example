# Rx-Java

Reactive Programming is a programming paradigm oriented around data flows and the propagation of change i.e. it is all about responding to value changes.
Reactive programming is asyncronous programming, and RxJava using reactive programming and observer pattern.

Observable: class that emits a stream of data or events. i.e. a class that can be used to perform some action, and publish the result.

Observer: class that receivers the events or data and acts upon it. i.e. a class that waits and watches the Observable, and reacts whenever the Observable publishes results.

The Observer has 4 interface methods to know the different states of the Observable.

onSubscribe(): This method is invoked when the Observer is subscribed to the Observable.

onNext(): This method is called when a new item is emitted from the Observable.

onError(): This method is called when an error occurs and the emission of data is not successfully completed.

onComplete(): This method is called when the Observable has successfully completed emitting all items.

Create observer:
```
private Observer getObserver() {
        return new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext: " + o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("----------------------onComplete----------------------");
            }
        };
    }
```
# Just

```
private List<String> stringList = new ArrayList<>();
...
stringList.add("a");
stringList.add("b");
stringList.add("c");
stringList.add("d");
stringList.add("e");
```

```
//just
Observable.just("1","2","3","4").subscribe(getObserver());
Observable.just(stringList).subscribe(getObserver());
```

Output:
```
onSubscribe
onNext: 1
onNext: 2
onNext: 3
onNext: 4
----------------------onComplete----------------------
onSubscribe
onNext: [a, b, c, d, e]
----------------------onComplete----------------------
```
# From
This operator creates an Observable from set of items using an Iterable, which means we can pass a list or an array of items to the Observable and each item is emitted one at a time. Some of the examples of the operators include fromCallable(), fromFuture(), fromIterable(), fromPublisher(), fromArray().
```
Observable.fromArray(stringArr).subscribe(getObserver());
Observable.fromArray(stringList).subscribe(getObserver());
Observable.fromIterable(stringList).subscribe(getObserver());
```

# Defer
This operator does not create the Observable until the Observer subscribes. The only downside to defer() is that it creates a new Observable each time you get a new Observer. create() can use the same function for each subscriber, so it’s more efficient.
```
Observable.defer(() -> Observable.just(stringList)).subscribe(getObserver());
```

# Map
This operator transforms the items emitted by an Observable by applying a function to each item. map() operator allows for us to modify the emitted item from the Observable and then emits the modified item.
```
Observable.fromIterable(stringList)
    .map((Function<Integer, Object>) s -> s * 2)
    .subscribe(getObserver());
```

# Flatmap
This operator transforms each item emitted by an Observable but instead of returning the modified item, it returns the Observable itself which can emit data again.
```
Observable.fromIterable(stringList)
    .flatMap((Function<Integer, ObservableSource<?>>) s -> getModifiedObservable(s))
    .subscribe(getObserver());
```

# Switchmap
Whenever a new item is emitted by the Observable, it will unsubscribe to the Observable that was generated from the previously emitted item and begin only mirroring the current one. In other words, it returns the latest Observable and emits the items from it.
```
Observable.fromIterable(stringList)
            .switchMap((Function<Integer, ObservableSource<?>>) s -> getModifiedObservable(s))
            .subscribe(getObserver());
```

# ConcatMap
This operator functions the same way as flatMap(), the difference being in concatMap() the order in which items are emitted are maintained. One disadvantage of concatMap() is that it waits for each observable to finish all the work until next one is processed.
```
Observable.fromIterable(stringList)
            .concatMap((Function<Integer, ObservableSource<?>>) s -> getModifiedObservable(s))
            .subscribe(getObserver());
```
