import rx.Observable;

public class ObservableTest {
    public static void main(String[] args) {

        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        Observable<Integer> lengths = source.map(String::length);

        lengths.subscribe(l -> System.out.println("Received " + l +
                " on thread " + Thread.currentThread().getName()));
    }

}
