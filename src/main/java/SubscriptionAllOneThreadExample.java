import rx.Observable;
import util.DataGenerator;

import java.util.List;

public class SubscriptionAllOneThreadExample {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------");
        System.out.println("Creating an Observable that does not specify a subscribeOn or an observeOn Schedule");
        System.out.println("driving thread: " + Thread.currentThread().getName());
        System.out.println("----------------------------------------------");

        List<Integer> intList = DataGenerator.generateFibonacciList();
        Observable<Integer> observable = Observable.from(intList);

        observable.subscribe(
                //onNext function
                (i) -> {
                    // Println the name of the current thread on entry and exist so that we
                    // can see a few interesting pieces of information...
                    System.out.println("onNext thread entr: " + Thread.currentThread().getName());
                    System.out.println(i);
                    System.out.println("onNext thread entr: " + Thread.currentThread().getName());
                    System.out.println("----------------------------------------------");
                },
                //onError function
                (t) -> t.printStackTrace(),
                //onCompleted function
                () -> System.out.println("onCompleted()")
        );

        System.exit(0);
    }
}
