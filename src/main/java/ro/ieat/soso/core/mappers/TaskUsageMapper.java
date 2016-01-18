package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.jobs.TaskUsage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by adrian on 18.11.2015.
 * This class has the sole purpose of reading the task_usage files from google cluster data traces and map them on
 * the internal representation.
 */
public class TaskUsageMapper  {
    private static Pattern pattern = Pattern.compile(",");
    private static long counter = 0;

    public static List<TaskUsage> map(FileReader fileReader, long start, long end) throws IOException, InterruptedException {

        List<TaskUsage> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(fileReader);

        long startTime;
        long endTime;
        long jobId;
        long taskIndex;
        long machine;


        double cpu;
        double mem;
        double disk = .0;
        double maxCpu = .0;
        double maxMemory = .0;

        double maxDisk = .0;

        String[] tokens;
        for(String line; (line = br.readLine()) != null; ) {
            tokens = line.split(",");
            startTime = Long.parseLong(tokens[0]);
            endTime = Long.parseLong(tokens[1]);
            if(startTime <   start * 1000000)
                continue;
            if(endTime > end * 1000000)
                return result;

            jobId = Long.parseLong(tokens[2]);
            taskIndex = Long.parseLong(tokens[3]);
            machine = Long.parseLong(tokens[4]);


            cpu = Double.parseDouble(tokens[5]);
            mem = Double.parseDouble(tokens[6]);
            if(tokens.length > 9) {
                maxMemory = Double.parseDouble(tokens[10]);
                disk = Double.parseDouble(tokens[12]);
                maxCpu = Double.parseDouble(tokens[13]);


                maxDisk = .0;
                if (tokens[14].length() > 0)
                    maxDisk = Double.parseDouble(tokens[14]);

            }
            TaskUsage usage = new TaskUsage(startTime, endTime, cpu, mem, disk);
            usage.setMaxCpu(maxCpu);
            usage.setMaxMemory(maxMemory);
            usage.setMaxDisk(maxDisk);
            usage.setJobId(jobId);
            usage.setTaskIndex(taskIndex);
            usage.setMachineId(machine);


//            if (!result.containsKey(jobId))
//                result.put(jobId, new ArrayList<JobWritable>());
            //Assume it exitsts already.
            result.add(usage);

        }
        br.close();
        fileReader.close();
        return result;
    }
}
