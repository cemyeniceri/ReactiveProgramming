import rx.Observable;
import rx.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class ObservableComputationTest {
    public static void main(String[] args) throws InterruptedException {

        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        Observable<Integer> lengths = source
                .subscribeOn(Schedulers.computation())
                .map(String::length);

        lengths.subscribe(sum -> System.out.println("Received " + sum +
                " on thread " + Thread.currentThread().getName()));

        sleep(3000);
    }

}
