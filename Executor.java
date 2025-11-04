package ecospark.model;

public class Executor {
    private int id;
    private double cpuDemand;
    private double memDemand;
    private VM assignedVM;

    public Executor(int id, double cpu, double mem) {
        this.id = id;
        this.cpuDemand = cpu;
        this.memDemand = mem;
    }

    public double getCpuDemand() { return cpuDemand; }
    public double getMemDemand() { return memDemand; }
    public void assignVM(VM vm) { this.assignedVM = vm; }
    public VM getAssignedVM() { return assignedVM; }
}
