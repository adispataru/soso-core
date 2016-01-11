package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.jobs.Job;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by adrian on 18.11.2015.
 * This class has the sole purpose of reading the job_events files from google cluster data traces and map them on
 * the internal representation.
 */
public class JobEventsMapper  {



    public static void map(FileReader fileInputStream, Map<Long, Job> result, long startTime, long endTime) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(fileInputStream);
        String line;
        while ((line = br.readLine()) != null) {


            String[] tokens = line.split(",");
            Job job;
            //Job events file
            long timestamp = Long.parseLong(tokens[0]);


            long jobId = Long.parseLong(tokens[2]);

            //Job events must be known!
//            if(timestamp < (startTime - 600) * 1000000)
//                continue;
            if(timestamp > endTime * 1000000)
                return;

            int event = Integer.parseInt(tokens[3]);
            String logicJobName = tokens[7];
            String scheduleClass = tokens[5];
            String status = " ";
            long submitTime = 0;
            long scheduleTime = 0;
            long finishTime = 0;
            switch (event) {
                case 0:
                    submitTime = timestamp;
                    break;
                case 1:
                    scheduleTime = timestamp;
                    break;
                default:
                    switch (event) {
                        case 2:
                            status = "evict";
                            finishTime = timestamp;
                            break;
                        case 3:
                            status = "fail";
                            finishTime = timestamp;
                            break;
                        case 4:
                            status = "finish";
                            finishTime = timestamp;
                            break;
                        case 5:
                            status = "kill";
                            finishTime = timestamp;
                            break;
                        case 6:
                            status = "lost";
                            finishTime = timestamp;
                            break;
                        default:
                            status = "update";
                    }
            }
            job = new Job(jobId,logicJobName,
                    submitTime, scheduleTime, finishTime, status);
            job.setScheduleClass(Long.parseLong(scheduleClass));


            if (!result.containsKey(jobId)) {
                result.put(jobId, job);
            }else {
                Job jobWritable = result.get(jobId);
                jobWritable.setFinishTime(jobWritable.getFinishTime() > finishTime ? jobWritable.getFinishTime() : finishTime);
                jobWritable.setSubmitTime(jobWritable.getSubmitTime() > submitTime ? jobWritable.getSubmitTime() : submitTime);
                jobWritable.setScheduleTime(jobWritable.getScheduleTime() > scheduleTime ? jobWritable.getScheduleTime() : scheduleTime);
                jobWritable.setStatus(status);
            }

        }
        br.close();
        fileInputStream.close();

    }
}
