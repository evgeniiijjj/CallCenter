import java.util.concurrent.ConcurrentLinkedQueue;

public class Operator implements Runnable {
    private final ConcurrentLinkedQueue<Call> calls;
    private final int callProcessingTime;

    public Operator(ConcurrentLinkedQueue<Call> calls, int callProcessingTime) {
        this.calls = calls;
        this.callProcessingTime = callProcessingTime;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Оператор %s приступил к обработке вызовов\n", name);
        int i = 0;
        while (i < 2) {
            try {
                Thread.sleep(callProcessingTime / 2);
            } catch (InterruptedException ignored) {
            }
            Call call = calls.poll();
            if (call != null) {
                i = 0;
                System.out.printf("Оператор %s обработал вызов номер %d\n", name, call.id);
                try {
                    Thread.sleep(callProcessingTime / 2);
                } catch (InterruptedException ignored) {
                }
            } else i++;
        }
        System.out.printf("Оператор %s завершил работу\n", name);
    }
}
