package RealLabGoesHere;

import java.util.List;

public class Buffer {
    private int RAM;
    private List<Process> processes;
    private List<Resource> resources;
    private boolean inUse = false;

    public Buffer(int RAM, List<Process> processes, List<Resource> resources) {
        this.RAM = RAM;
        this.processes = processes;
        this.resources = resources;
    }

    public synchronized void substractRam(int ramToSubstract) {
        while (inUse) {
            waiting();
        }
        inUse = true;

        RAM -= ramToSubstract;

        inUse = false;
        notifyAll();
    }

    public synchronized void addRam(int ramToAdd) {
        while (inUse) {
            waiting();
        }
        RAM += ramToAdd;
    }

    private void waiting() {
        try {
            wait();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public List<Resource> getResources() {
        return resources;
    }
}
