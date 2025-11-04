package ecospark.model;

public class EnergyModel {
    public static double computePower(VM vm, double utilization) {
        return vm.getIdlePower() + (vm.getMaxPower() - vm.getIdlePower()) * utilization;
    }

    public static double computeExecutorEnergy(VM vm, double cpuTime, double utilization) {
        double power = computePower(vm, utilization);
        return power * cpuTime;
    }
}
