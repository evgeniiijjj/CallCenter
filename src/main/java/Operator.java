import java.util.concurrent.ConcurrentLinkedQueue;

public class Operator implements Runnable {
    private final ConcurrentLinkedQueue<Integer> calls;

    public Operator(ConcurrentLinkedQueue<Integer> calls) {
        this.calls = calls;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("%s приступил к обработке вызовов\n", name);
        int i = 0;
        while (true) {
            Integer call = calls.poll();
            if (call != null) {
                i = 0;
                System.out.printf("%s обработал вызов номер %d\n", name, call);
            } else i++;
            try {
                Thread.sleep(CallCenter.CALL_PROCESSING_TIME);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
