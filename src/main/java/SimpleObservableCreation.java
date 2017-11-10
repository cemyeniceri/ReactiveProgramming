import rx.Observable;
import util.DataGenerator;

public class SimpleObservableCreation {
    public static void main(String[] args) {
        Observable<Integer> observable;


        System.out.println("--------------------------------------");
        System.out.println("Observable Creation from a Single Value");
        System.out.println("--------------------------------------");

        observable = Observable.just(Integer.valueOf(42));
        observable.subscribe((Integer i) -> System.out.println(i));

        System.out.println("--------------------------------------");
        System.out.println("Observable Creation from an Iterable");
        System.out.println("--------------------------------------");
        observable = Observable.from(DataGenerator.generateFibonacciList());
        observable.subscribe((Integer i) -> System.out.println(i));

        System.out.println("--------------------------------------");
        System.out.println("Observable Creation from an Array");
        System.out.println("--------------------------------------");
        observable = Observable.from(DataGenerator.generateFibonacciArray());
        observable.subscribe((Integer i) -> System.out.println(i));

        System.exit(0);
    }
}
