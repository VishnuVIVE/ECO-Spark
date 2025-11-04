package ecospark.scheduler;

import ecospark.model.VM;
import ecospark.model.Executor;

public class EnergyEstimator {

    /**
     * Computes instantaneous power using Eq. (18): 
     * P_i = P_idle + (P_max - P_idle) × U_cpu
     */
    public static double computePower(VM vm, double utilization) {
        return vm.getIdlePower() + (vm.getMaxPower() - vm.getIdlePower()) * utilization;
    }

    /**
     * Computes total energy per executor: Eq. (19)–(20)
     * E_exec = P_i × exec_time
     */
    public static double computeExecutorEnergy(VM vm, double utilization, double execTime) {
        double power = computePower(vm, utilization);
        return power * execTime;
    }

    /**
     * Aggregates total energy across all assigned executors in a job.
     */
    public static double computeJobEnergy(java.util.List<Executor> executors, double execTime, double utilization) {
        double total = 0;
        for (Executor e : executors) {
            if (e.getAssignedVM() != null) {
                total += computeExecutorEnergy(e.getAssignedVM(), utilization, execTime);
            }
        }
        return total;
    }
}
