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
