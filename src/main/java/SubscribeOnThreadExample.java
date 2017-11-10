import rx.Observable;
import rx.schedulers.Schedulers;
import util.DataGenerator;

import java.util.List;

public class SubscribeOnThreadExample {
    public static void main(String[] args) throws InterruptedException {

        // Create and sync on an object that we will use to make sure we don't
        // hit the System.exit(0) call before our threads have had a chance
        // to complete.
        Object waitMonitor = new Object();
        synchronized (waitMonitor) {
            System.out.println("----------------------------------------------");
            System.out.println("Creating an Observable that specifies a subscribeOn, but not observeOn Schedule");
            System.out.println("driving thread: " + Thread.currentThread().getName());
            System.out.println("----------------------------------------------");

            List<Integer> intList = DataGenerator.generateFibonacciList();
            Observable<Integer> observable = Observable.from(intList);

            observable
                    //make sure that the subscriber driver code executes on a new thread...
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(
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

            // wait until the onCompleted method wakes us up.
            waitMonitor.wait();
        }
        System.exit(0);
    }
}
