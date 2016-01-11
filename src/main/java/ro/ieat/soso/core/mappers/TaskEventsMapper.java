package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.jobs.Job;
import ro.ieat.soso.core.jobs.TaskHistory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by adrian on 18.11.2015.
 * This class has the sole purpose of reading the task_events files from google cluster data traces and map them on
 * the internal representation.
 */
public class TaskEventsMapper {

    public static void map(FileReader fileReader, Map<Long, Job> result, long startTime, long endTime) throws IOException, InterruptedException {

        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            long timestamp = Long.parseLong(tokens[0]);
            long jobId = Long.parseLong(tokens[2]);
            long taskIndex = Long.parseLong(tokens[3]);
            int event = Integer.parseInt(tokens[5]);

            if(timestamp < startTime * 1000000)
                continue;
            if(timestamp > endTime * 1000000)
                return;

            String status = " ";
            long submitTime = 0;
            long scheduleTime = 0;
            long finishTime = 0;
            //TODO You should not create coalitions if event is not successful
            switch (event) {
                case 0:
                    submitTime = timestamp;
                    break;
                case 1:
                    scheduleTime = timestamp;
                    break;
                default:
                    finishTime = timestamp;
                    switch (event) {
                        case 2:
                            status = "evict";
                            break;
                        case 3:
                            status = "fail";
                            break;
                        case 4:
                            status = "finish";
                            break;
                        case 5:
                            status = "kill";
                            break;
                        case 6:
                            status = "lost";
                            break;
                        default:
                            status = "update";
                    }
            }
            TaskHistory task = new TaskHistory(taskIndex, submitTime, scheduleTime,
                    finishTime, status);

            if (!result.containsKey(jobId))
                System.out.println(startTime + " " + endTime*1000000  + " " + timestamp + ";jobid: " + jobId);
            result.get(jobId).getTaskHistory().put(task.getTaskIndex(), task);

        }
        br.close();
        fileReader.close();
    }
}
