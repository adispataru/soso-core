package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.jobs.Job;
import ro.ieat.soso.core.jobs.TaskConstraint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by adrian on 18.11.2015.
 * This class has the sole purpose of reading the task_constraints files from google cluster data traces and map them on
 * the internal representation.
 */
public class TaskConstraintReader {
    private static Pattern pattern = Pattern.compile(",");
    private static long counter = 0;

    public static List<TaskConstraint> map(FileReader fileReader, Map<Long, Job> jobs, long start, long end) throws IOException, InterruptedException {

        List<TaskConstraint> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(fileReader);

        long startTime;
        long jobId;
        long taskIndex;

        String[] tokens;
        for(String line; (line = br.readLine()) != null; ) {
            tokens = line.split(",");
            startTime = Long.parseLong(tokens[0]);

            if(startTime <   start * 1000000)
                continue;
            if(startTime > end * 1000000)
                return result;

            jobId = Long.parseLong(tokens[1]);
            taskIndex = Long.parseLong(tokens[2]);
            int op = Integer.parseInt(tokens[3]);
            String attrName = tokens[4];
            String attrValue = tokens.length > 5 ? tokens[5] : "";


            TaskConstraint taskConstraint = new TaskConstraint();
            taskConstraint.setAttributeName(attrName);
            taskConstraint.setAttributeValue(attrValue);
            taskConstraint.setComparisonOperator(op);
            taskConstraint.setJobId(jobId);
            taskConstraint.setTaskIndex(taskIndex);
            taskConstraint.setTimestamp(startTime);


            jobs.get(jobId).getTaskHistory().get(taskIndex).getConstraints().add(taskConstraint);
//            result.add(taskConstraint);

        }
        br.close();
        fileReader.close();
        return result;
    }
}
