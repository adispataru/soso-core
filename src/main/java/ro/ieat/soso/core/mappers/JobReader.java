package ro.ieat.soso.core.mappers;

import ro.ieat.soso.core.config.Configuration;
import ro.ieat.soso.core.jobs.Job;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 06.01.2016.
 */
public class JobReader {

    public static List<Job> getJobsWithLogicJobName(String filePath, String logicJobName, long historyStart, long historyEnd) throws IOException {
        List<Job> result = new ArrayList<Job>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {


            String[] tokens = line.split(",");
            if(!logicJobName.equals(tokens[1]))
                continue;

            long submitTime = Long.parseLong(tokens[2]);

            if(submitTime < historyStart * Configuration.TIME_DIVISOR)
                continue;
            if (submitTime > historyEnd * Configuration.TIME_DIVISOR)
                break;


            long jobId = Long.parseLong(tokens[0]);

            Job j = new Job();
            j.setJobId(jobId);
            j.setLogicJobName(logicJobName);
            j.setSubmitTime(submitTime);
            j.setScheduleTime(Long.parseLong(tokens[3]));
            j.setFinishTime(Long.parseLong(tokens[4]));
            j.setStatus(tokens[5]);
            result.add(j);

        }

        return result;
    }
}
