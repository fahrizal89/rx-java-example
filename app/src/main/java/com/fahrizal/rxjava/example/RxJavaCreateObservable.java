package com.fahrizal.rxjava.example;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Fahrizal Sentosa (fahrizal.sentosa@dana.id)
 * @version RxJavaCreateObservable, v 0.1 09/09/19 00.33 by Fahrizal Sentosa
 */
public class RxJavaCreateObservable {

    private List<String> stringList = new ArrayList<>();

    public static void main(String[] args) {
        RxJavaCreateObservable rxJavaCreateObservable = new RxJavaCreateObservable();
        rxJavaCreateObservable.run();
    }

    public void run() {
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("d");
        stringList.add("e");

        String[] stringArr = new String[]{"1", "2", "3", "4", "5"};

        //just
        Observable.just(stringList).subscribe(getObserver());
        Observable.just(stringArr).subscribe(getObserver());

        //from
//        Observable.fromArray(stringArr).subscribe(getObserver());
//        Observable.fromArray(stringList).subscribe(getObserver());
//        Observable.fromIterable(stringList).subscribe(getObserver());

        //defer
//        Observable.defer(() -> Observable.just(stringList)).subscribe(getObserver());

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
}
