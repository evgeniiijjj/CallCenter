import java.util.concurrent.ConcurrentLinkedQueue;

public class ATS implements Runnable {
    private final ConcurrentLinkedQueue<Call> calls;
    private final int callsNum;
    private final int callGenerationTime;

    public ATS(ConcurrentLinkedQueue<Call> calls, int callNum, int callGenerationTime) {
        this.calls = calls;
        this.callsNum = callNum;
        this.callGenerationTime = callGenerationTime;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("АТС %s начала генерацию вызовов\n", name);
        for (int i = 0; i < callsNum; i++) {
            try {
                Thread.sleep(callGenerationTime / 2);
            } catch (InterruptedException ignored) {
            }
            calls.offer(new Call(i + 1));
            System.out.printf("АТС %s сгенерировала %d вызов\n", name, i + 1);
            try {
                Thread.sleep(callGenerationTime / 2);
            } catch (InterruptedException ignored) {
            }
        }
        System.out.printf("АТС %s завершила генерацию вызовов\n", name);
    }
}
