package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.coalitions.Machine;
import ro.ieat.soso.core.config.Configuration;
import ro.ieat.soso.core.jobs.TaskUsage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 07.12.2015.
 * Class to map machine usage to internal representation
 *
 */
@Deprecated
public class MachineUsageMapper {

    /**
     *
     * @param file - file containing machine usage
     * @param mapping - the mapping into which to map @link{MachineProperties} to jobs.
     * @param endTime - end time (in seconds) representing last time of knowledge.
     * @throws IOException
     * @throws InterruptedException
     */
    //TODO Add end time
    public static void map(File file, List<Machine> mapping, long startTime, long endTime) throws IOException, InterruptedException {
        FileReader fileReader = new FileReader(file);

        BufferedReader br = new BufferedReader(fileReader);
        String line;
        String[] tokens;
        long lastTask = 0;
        Machine machineProperties = new Machine();
        machineProperties.setTaskUsageList(new ArrayList<>());
        long machineId = Long.parseLong(file.getName());
        machineProperties.setId(machineId);
        TaskUsage taskUsage = new TaskUsage();


        List<TaskUsage> taskUsageList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            tokens = line.split(",");
            long taskIndex = Long.parseLong(tokens[8]);
            long jobId = Long.parseLong(tokens[9]);
            String logic = tokens[10];


            if(Long.parseLong(tokens[0]) < startTime * 1000000)
                continue;
            if(Long.parseLong(tokens[1]) > endTime * 1000000)
                continue;


            if (lastTask != taskIndex){

                machineProperties.getTaskUsageList().add(taskUsage.getJobId());
                taskUsage = new TaskUsage();

                //mapping.add(machineProperties);


                taskUsage.setJobId(jobId);
                taskUsage.setLogicJobName(logic);
                taskUsage.setTaskIndex(taskIndex);
                //Store list statically
                taskUsageList = new ArrayList<>();

            }

            TaskUsage usage = new TaskUsage();
            usage.setStartTime(Long.parseLong(tokens[0]));
            usage.setEndTime(Long.parseLong(tokens[1]));
            usage.setCpu(Double.parseDouble(tokens[2]));
            usage.setMaxCpu(Double.parseDouble(tokens[3]));
            usage.setMemory(Double.parseDouble(tokens[4]));
            usage.setMaxMemory(Double.parseDouble(tokens[5]));
            usage.setDisk(Double.parseDouble(tokens[6]));
            usage.setMaxDisk(Double.parseDouble(tokens[7]));

            if(usage.getStartTime()/ Configuration.TIME_DIVISOR % Configuration.STEP != 0){
                for (TaskUsage usage2 : taskUsageList){
                    if(usage2.getEndTime() == usage.getStartTime()){
                        usage2.setEndTime(usage.getEndTime());
                        usage2.setCpu(Math.max(usage.getCpu(), usage2.getCpu()));
                        usage2.setMaxCpu(Math.max(usage.getMaxCpu(), usage2.getMaxCpu()));
                        usage2.setMemory(Math.max(usage.getMemory(), usage2.getMemory()));
                        usage2.setMaxMemory(Math.max(usage.getMaxMemory(), usage2.getMaxMemory()));
                        usage2.setDisk(Math.max(usage.getDisk(), usage2.getDisk()));
                        usage2.setMaxDisk(Math.max(usage.getMaxDisk(), usage2.getMaxDisk()));
                        break;
                    }
                }
            }else {
                taskUsageList.add(usage);
            }

        }
        mapping.add(machineProperties);
        br.close();
        fileReader.close();
    }

    public static Machine readOne(File file, long startTime, long endTime) throws IOException, InterruptedException {
        FileReader fileReader = new FileReader(file);

        BufferedReader br = new BufferedReader(fileReader);
        String line;
        String[] tokens;
        long lastTask = 0;
        Machine machineProperties = new Machine();
        machineProperties.setTaskUsageList(new ArrayList<>());
        long machineId = Long.parseLong(file.getName());
        machineProperties.setId(machineId);
        TaskUsage taskUsage = new TaskUsage();


        boolean inserted = true;

        List<TaskUsage> taskUsageList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            tokens = line.split(",");
            long taskIndex = Long.parseLong(tokens[8]);
            long jobId = Long.parseLong(tokens[9]);
            String logic = tokens[10];


            if(Long.parseLong(tokens[0]) < startTime * 1000000)
                continue;
            if(Long.parseLong(tokens[1]) > endTime * 1000000L)
                continue;


            if (lastTask != taskIndex ){

                machineProperties.getTaskUsageList().add(taskUsage.getJobId());
                taskUsage = new TaskUsage();

                //mapping.add(machineProperties);


                taskUsage.setJobId(jobId);
                taskUsage.setLogicJobName(logic);
                taskUsage.setTaskIndex(taskIndex);
                taskUsageList = new ArrayList<>();
                inserted = false;

            }

            TaskUsage usage = new TaskUsage();
            usage.setStartTime(Long.parseLong(tokens[0]));
            usage.setEndTime(Long.parseLong(tokens[1]));
            usage.setCpu(Double.parseDouble(tokens[2]));
            usage.setMaxCpu(Double.parseDouble(tokens[3]));
            usage.setMemory(Double.parseDouble(tokens[4]));
            usage.setMaxMemory(Double.parseDouble(tokens[5]));
            usage.setDisk(Double.parseDouble(tokens[6]));
            usage.setMaxDisk(Double.parseDouble(tokens[7]));


            if(usage.getStartTime() / Configuration.TIME_DIVISOR % Configuration.STEP != 0){
                for (TaskUsage usage2 : taskUsageList){
                    if(usage2.getEndTime() == usage.getStartTime()){
                        usage2.setEndTime(usage.getEndTime());
                        usage2.setCpu(Math.max(usage.getCpu(), usage2.getCpu()));
                        usage2.setMaxCpu(Math.max(usage.getMaxCpu(), usage2.getMaxCpu()));
                        usage2.setMemory(Math.max(usage.getMemory(), usage2.getMemory()));
                        usage2.setMaxMemory(Math.max(usage.getMaxMemory(), usage2.getMaxMemory()));
                        usage2.setDisk(Math.max(usage.getDisk(), usage2.getDisk()));
                        usage2.setMaxDisk(Math.max(usage.getMaxDisk(), usage2.getMaxDisk()));
                        break;
                    }
                }
            }else {
                taskUsageList.add(usage);
            }

        }
        if(!inserted)
            machineProperties.getTaskUsageList().add(taskUsage.getJobId());
        br.close();
        fileReader.close();

        return machineProperties;
    }
}
