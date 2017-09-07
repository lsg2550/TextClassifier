/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.benchmarking;

/**
 *
 * @author Luis
 */
public class MemoryUsage {

    public static long memoryUsage() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long memoryUsageInKBytes() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
    }

    public static long memoryUsageInMBytes() {
        return memoryUsageInKBytes() / 1024;
    }
    
}
