package ro.ieat.soso.core.mappers;

import ro.ieat.soso.core.jobs.Job;
import ro.ieat.soso.core.jobs.TaskHistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adrian on 23.11.2015.
 */
public class JobCombiner {

    public static void reduce (Long key, Job iterator) throws InterruptedException, IOException{
        Map<Long, TaskHistory> tasks = new TreeMap<Long, TaskHistory>();

        for (TaskHistory job : iterator.getTaskHistory()){
            //TODO Add task usages to taskHistories. when reading.
                long taskIndex = job.getTaskIndex();
                if(tasks.containsKey(taskIndex)){
                    tasks.put(taskIndex, TaskHistory.combineTasks(job, tasks.get(taskIndex)));
                }else{
                    tasks.put(taskIndex, job);
                }
        }

        iterator.setTaskHistory(new ArrayList<>(tasks.values()));


    }

}
