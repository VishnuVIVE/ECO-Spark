package ecospark.scheduler;

import ecospark.model.VM;

public class FitnessCalculator {
    public static double computeFitness(VM vm, double minCost, double maxCost,
                                        double minPower, double maxPower,
                                        double costWeight, double energyWeight,
                                        double densityBonus) {

        double costNorm = (vm.getCost() - minCost) / (maxCost - minCost);
        double powerNorm = (vm.getMaxPower() - minPower) / (maxPower - minPower);

        return Math.pow(costNorm, costWeight) * Math.pow(powerNorm, energyWeight) - densityBonus * 0.3;
    }
}
