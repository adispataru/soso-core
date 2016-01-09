package ro.ieat.soso.core.mappers;

import ro.ieat.soso.core.coalitions.Usage;
import ro.ieat.soso.core.config.Configuration;
import ro.ieat.soso.core.jobs.TaskUsage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 06.01.2016.
 */
public class TaskUsageReader {

    public static List<TaskUsage> getTaskUsageForJobId(String filePath, long jobId, long historyStart, long historyEnd) throws IOException {
        List<TaskUsage> result = new ArrayList<TaskUsage>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        String[] tokens;
        while ((line = br.readLine()) != null) {

            tokens = line.split(",");
            long startTime = Long.parseLong(tokens[0]);
            long endTime = Long.parseLong(tokens[1]);
            if(startTime <   historyStart * Configuration.TIME_DIVISOR)
                continue;
            if(endTime > historyEnd * Configuration.TIME_DIVISOR)
                break;

            if(jobId != Long.parseLong(tokens[2]))
                continue;
            long taskIndex = Long.parseLong(tokens[3]);
            long machine = Long.parseLong(tokens[4]);


            double cpu = Double.parseDouble(tokens[5]);
            double mem = Double.parseDouble(tokens[6]);
            double disk = Double.parseDouble(tokens[12]);
            double maxCpu = Double.parseDouble(tokens[13]);
            double maxMemory = Double.parseDouble(tokens[10]);

            double maxDisk = .0;
            if(tokens[14].length() > 0)
                maxDisk = Double.parseDouble(tokens[14]);


            Usage usage = new Usage(startTime, endTime, cpu, mem, disk);
            usage.setMaxCpu(maxCpu);
            usage.setMaxMemory(maxMemory);
            usage.setMaxDisk(maxDisk);


            TaskUsage task = new TaskUsage(taskIndex, jobId, null);
            ArrayList<Usage> usages = new ArrayList<>();
            usages.add(usage);
            task.setUsageList(usages);

//            if (!result.containsKey(jobId))
//                result.put(jobId, new ArrayList<JobWritable>());
            //Assume it exitsts already.
            result.add(task);

        }
        br.close();

        return result;
    }
}
