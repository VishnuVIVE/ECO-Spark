package ecospark.scheduler;

import ecospark.model.*;
import ecospark.utils.Logger;
import java.util.*;
import java.util.stream.Collectors;

public class ECOSparkScheduler {

    public boolean scheduleJob(Job job, List<VM> allVMs) {
        List<VM> localVMs = allVMs.stream().filter(v -> v.getType() == VM.Type.LOCAL).collect(Collectors.toList());
        List<VM> cloudVMs = allVMs.stream().filter(v -> v.getType() == VM.Type.CLOUD).collect(Collectors.toList());

        if (!clusterCapacityCheck(job, localVMs, cloudVMs)) {
            Logger.log("Job " + job.getId() + " failed: insufficient capacity.");
            return false;
        }

        if (attemptPlacement(job, localVMs)) {
            Logger.log("Job " + job.getId() + " placed on local VMs.");
            return true;
        } else if (attemptPlacement(job, cloudVMs)) {
            Logger.log("Job " + job.getId() + " placed on cloud VMs.");
            return true;
        } else {
            Logger.log("Job " + job.getId() + " failed placement.");
            return false;
        }
    }

    private boolean clusterCapacityCheck(Job job, List<VM> locals, List<VM> clouds) {
        double totalCap = 0;
        for (VM v : locals)
            totalCap += Math.min(v.getCPUFree(), v.getMemFree());
        for (VM v : clouds)
            totalCap += Math.min(v.getCPUFree(), v.getMemFree());
        return totalCap >= job.getExecutors().size();
    }

    private boolean attemptPlacement(Job job, List<VM> vms) {
        for (Executor e : job.getExecutors()) {
            for (VM vm : vms) {
                if (vm.getCPUFree() >= e.getCpuDemand() && vm.getMemFree() >= e.getMemDemand()) {
                    vm.allocate(e.getCpuDemand(), e.getMemDemand());
                    e.assignVM(vm);
                    break;
                }
            }
            if (e.getAssignedVM() == null) return false;
        }
        return true;
    }
}
