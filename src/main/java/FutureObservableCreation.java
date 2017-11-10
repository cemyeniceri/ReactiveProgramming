import rx.Observable;
import rx.schedulers.Schedulers;
import util.DataGenerator;

import java.util.List;
import java.util.concurrent.FutureTask;

public class FutureObservableCreation {
    public static void main(String[] args) {
        Observable<List<Integer>> observableFutureList;

        //Create a Future Task that returns a List<Integer>
        FutureTask<List<Integer>> future = new FutureTask<>(() -> DataGenerator.generateFibonacciList());

        // Construct an observable, note that this only creates the
        // observable wrapper around the future. The future still needs
        // to be executed using it's "run" method, or by scheduling it to
        // execute
        observableFutureList = Observable.from(future);

        // Schedule this future to run on the computation scheduler
        Schedulers.computation().createWorker().schedule(()-> future.run());

        // Subscribe to the list... whe n the list is ready through the
        // future, iterate and print each element
        observableFutureList.subscribe((list)-> list.forEach((i)-> System.out.println(i)));

        System.exit(0);
    }
}
