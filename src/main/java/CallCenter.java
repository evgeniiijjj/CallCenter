import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    static final ConcurrentLinkedQueue<Call> calls = new ConcurrentLinkedQueue<>();// однонаправленная неблокирующая очередь вполне подходящее решение для применения в данной задаче
    static final int operatorsNum = 3;
    static final int callsNum = 60;
    static final int callGenerationTime = 1000;
    static final int callProcessingTime = 3000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Колл-центр приступил к работе");
        List<Thread> threads = new ArrayList<>();
        new Thread(new ATS(calls, callsNum, callGenerationTime)).start();
        for (int i = 0; i < operatorsNum; i++) {
            Thread.sleep(callGenerationTime);
            Thread thread = new Thread(new Operator(calls, callProcessingTime));
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Колл-центр завершил работу");
    }
}
