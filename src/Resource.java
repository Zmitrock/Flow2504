import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Resource {
    private List<Long> ids = new Vector<>();      //new ArrayList<>();

    public List<Long> getIds() {
        return ids;
    }
}

class ResourceUser implements Runnable {
    private Resource resource;

    ResourceUser(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.printf("Thread %s [%d] is Started... SIZE:%d \n",
                Thread.currentThread().getName(),
                Thread.currentThread().getId(),
                resource.getIds().size());

//        synchronized (resource) {
            resource.getIds().clear();
            for (int i = 0; i < 5; i++) {
                String list = resource.getIds().toString();
                System.out.printf("%s ID: %d, size: %d, %s \n",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getId(),
                        resource.getIds().size(),
                        list);
                resource.getIds().add(Thread.currentThread().getId());// main job
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }

        System.out.printf("Thread %s [%d] is Started... SIZE:%d \n",
                Thread.currentThread().getName(),
                Thread.currentThread().getId(),
                resource.getIds().size());


    }
}