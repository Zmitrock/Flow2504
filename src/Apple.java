import java.util.concurrent.Callable;

public class Apple implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {//функция для старта, такая же как и run

        return 10;
    }
}
