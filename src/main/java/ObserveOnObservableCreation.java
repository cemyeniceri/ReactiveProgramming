import rx.Observable;
import rx.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class ObserveOnObservableCreation {
    public static void main(String[] args) throws InterruptedException {

        Observable<Integer> source = Observable.range(1, 10);

        source.map(i -> i * 100)
                .doOnNext(i -> System.out.println("Emitting " + i
                        + " on thread " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .map(i -> i * 10)
                .subscribe(i -> System.out.println("Received " + i + " on thread "
                        + Thread.currentThread().getName()));

        sleep(3000);
    }
}
