package ecospark;

import ecospark.model.*;
import ecospark.scheduler.ECOSparkScheduler;
import ecospark.utils.Logger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Logger.log("=== ECO-Spark Hybrid Scheduler Simulation ===");

        List<VM> vms = Arrays.asList(
            new VM("L1", VM.Type.LOCAL, 8, 16, 0.5, 50, 120, 8),
            new VM("L2", VM.Type.LOCAL, 4, 8, 0.4, 45, 110, 4),
            new VM("C1", VM.Type.CLOUD, 16, 32, 1.0, 60, 150, 16)
        );

        List<Executor> execs = Arrays.asList(
            new Executor(1, 2, 4),
            new Executor(2, 2, 4),
            new Executor(3, 2, 4)
        );

        Job job = new Job(101, 50.0, execs);

        ECOSparkScheduler scheduler = new ECOSparkScheduler();
        boolean success = scheduler.scheduleJob(job, vms);

        Logger.log("Job " + job.getId() + " scheduling " + (success ? "SUCCESSFUL" : "FAILED"));
    }
}
