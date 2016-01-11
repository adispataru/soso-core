package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.coalitions.Usage;
import ro.ieat.soso.core.jobs.Job;
import ro.ieat.soso.core.jobs.TaskHistory;
import ro.ieat.soso.core.jobs.TaskUsage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by adrian on 18.11.2015.
 * This class has the sole purpose of reading the task_usage files from google cluster data traces and map them on
 * the internal representation.
 */
public class TaskUsageMapper  {
    private static Pattern pattern = Pattern.compile(",");

    public static void map(FileReader fileReader, Map<Long, Job> result, long start, long end) throws IOException, InterruptedException {


        BufferedReader br = new BufferedReader(fileReader);

        long startTime;
        long endTime;
        long jobId;
        long taskIndex;
        long machine;


        double cpu;
        double mem;
        double disk;
        double maxCpu;
        double maxMemory;

        double maxDisk;

        String[] tokens;
        for(String line; (line = br.readLine()) != null; ) {
            tokens = line.split(",");
            startTime = Long.parseLong(tokens[0]);
            endTime = Long.parseLong(tokens[1]);
            if(startTime <   start * 1000000)
                continue;
            if(endTime > end * 1000000)
                return;

            jobId = Long.parseLong(tokens[2]);
            taskIndex = Long.parseLong(tokens[3]);
            machine = Long.parseLong(tokens[4]);


            cpu = Double.parseDouble(tokens[5]);
            mem = Double.parseDouble(tokens[6]);
            disk = Double.parseDouble(tokens[12]);
            maxCpu = Double.parseDouble(tokens[13]);
            maxMemory = Double.parseDouble(tokens[10]);

            maxDisk = .0;
            if(tokens[14].length() > 0)
                maxDisk = Double.parseDouble(tokens[14]);


            Usage usage = new Usage(startTime, endTime, cpu, mem, disk);
            usage.setMaxCpu(maxCpu);
            usage.setMaxMemory(maxMemory);
            usage.setMaxDisk(maxDisk);


            TaskUsage task = new TaskUsage(taskIndex, jobId, result.get(jobId).getLogicJobName());
            ArrayList<Usage> usages = new ArrayList<>();
            usages.add(usage);
            task.setUsageList(usages);

//            if (!result.containsKey(jobId))
//                result.put(jobId, new ArrayList<JobWritable>());
            //Assume it exitsts already.
            for(TaskHistory t : result.get(jobId).getTaskHistory()){

                if(t.getTaskIndex() == taskIndex)
                    if(t.getTaskUsage() != null) {
                        t.getTaskUsage().combineUsage(task);
                    }else{
                        t.setTaskUsage(task);
                    }

            }
        }
        br.close();
        fileReader.close();

    }
}
