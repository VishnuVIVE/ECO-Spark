package ecospark.model;

public class VM {
    public enum Type { LOCAL, CLOUD }

    private String id;
    private Type type;
    private double cpuFree;
    private double memFree;
    private double costPerHour;
    private double idlePower;
    private double maxPower;
    private int maxCores;

    public VM(String id, Type type, double cpuFree, double memFree,
              double costPerHour, double idlePower, double maxPower, int maxCores) {
        this.id = id;
        this.type = type;
        this.cpuFree = cpuFree;
        this.memFree = memFree;
        this.costPerHour = costPerHour;
        this.idlePower = idlePower;
        this.maxPower = maxPower;
        this.maxCores = maxCores;
    }

    public double getCPUFree() { return cpuFree; }
    public double getMemFree() { return memFree; }
    public Type getType() { return type; }
    public double getCost() { return costPerHour; }
    public double getIdlePower() { return idlePower; }
    public double getMaxPower() { return maxPower; }

    public void allocate(double cpu, double mem) {
        this.cpuFree -= cpu;
        this.memFree -= mem;
    }

    @Override
    public String toString() {
        return id + " [" + type + "]";
    }
}
