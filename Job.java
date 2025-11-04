package ecospark.model;

import java.util.List;

public class Job {
    private int id;
    private double deadline;
    private List<Executor> executors;

    public Job(int id, double deadline, List<Executor> executors) {
        this.id = id;
        this.deadline = deadline;
        this.executors = executors;
    }

    public int getId() { return id; }
    public double getDeadline() { return deadline; }
    public List<Executor> getExecutors() { return executors; }
}
