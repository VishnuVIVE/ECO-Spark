package ecospark.scheduler;

import ecospark.model.VM;
import ecospark.model.Executor;

public class CostCalculator {

    /**
     * Computes the cost of executing a given executor on a VM.
     * Equation (16)–(17): C_exec = cost_rate × exec_time × weight_factor
     */
    public static double computeExecutorCost(VM vm, Executor exec, double execTime, double weightFactor) {
        return vm.getCost() * execTime * weightFactor;
    }

    /**
     * Aggregates total cost for a job across all assigned VMs.
     */
    public static double computeJobCost(java.util.List<Executor> executors, double weightFactor, double execTime) {
        double total = 0;
        for (Executor e : executors) {
            if (e.getAssignedVM() != null) {
                total += computeExecutorCost(e.getAssignedVM(), e, execTime, weightFactor);
            }
        }
        return total;
    }
}
