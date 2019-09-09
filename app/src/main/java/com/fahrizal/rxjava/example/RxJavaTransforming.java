package com.fahrizal.rxjava.example;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Fahrizal Sentosa (fahrizal.sentosa@dana.id)
 * @version RxJavaCreateObservable, v 0.1 09/09/19 00.33 by Fahrizal Sentosa
 */
public class RxJavaTransforming {

    private List<Integer> stringList = new ArrayList<>();

    public static void main(String[] args) {
        RxJavaTransforming rxJavaCreateObservable = new RxJavaTransforming();
        rxJavaCreateObservable.run();
    }

    public void run() {
        stringList.add(1);
        stringList.add(2);
        stringList.add(3);
        stringList.add(4);
        stringList.add(5);

        //buffer
//        Observable.fromArray(stringArr).buffer(2).subscribe(getObserver());

        Observable.fromIterable(stringList)
            .map((Function<Integer, Object>) s -> s * 2)
            .subscribe(getObserver());
//
        Observable.fromIterable(stringList)
            .flatMap((Function<Integer, ObservableSource<?>>) s -> getModifiedObservable(s))
            .subscribe(getObserver());

        Observable.fromIterable(stringList)
            .switchMap((Function<Integer, ObservableSource<?>>) s -> getModifiedObservable(s))
            .subscribe(getObserver());

        Observable.fromIterable(stringList)
            .concatMap((Function<Integer, ObservableSource<?>>) s -> getModifiedObservable(s))
            .subscribe(getObserver());
    }

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

    private Observable<Integer> getModifiedObservable(final Integer integer) {
        return Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext((integer * 2));
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
    }
}
