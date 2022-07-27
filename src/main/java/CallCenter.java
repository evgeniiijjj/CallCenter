import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    private static final ConcurrentLinkedQueue<Integer> calls = new ConcurrentLinkedQueue<>();// однонаправленная неблокирующая очередь вполне подходящее решение для применения в данной задаче
    public static final int NUMBER_OF_OPERATORS = 3;
    public static final int NUMBER_OF_CALLS = 60;
    public static final int CALL_GENERATION_TIME = 1000;
    public static final int CALL_PROCESSING_TIME = 3000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Колл-центр приступил к работе");
        for (int i = 1; i <= NUMBER_OF_OPERATORS; i++) {
            Thread thread = new Thread(new Operator(calls), "Оператор - " + i);
            thread.setDaemon(true);
            thread.start();
            Thread.sleep(CALL_GENERATION_TIME / 2);
        }
        System.out.println("АТС начала генерацию вызовов");
        for (int i = 0; i < CallCenter.NUMBER_OF_CALLS; i++) {
            calls.offer(i + 1);
            System.out.printf("АТС сгенерировала %d вызов\n", i + 1);
            try {
                Thread.sleep(CallCenter.CALL_GENERATION_TIME);
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println("АТC завершила генерацию вызовов");
        Thread.sleep(CALL_PROCESSING_TIME);
        System.out.println("Колл-центр завершил работу");
    }
}
