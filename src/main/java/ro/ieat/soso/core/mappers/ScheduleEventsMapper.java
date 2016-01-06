package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.events.ScheduleEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 11.12.2015.
 */
public class ScheduleEventsMapper {

    public static List<ScheduleEvent> map(String file) throws IOException {

        List<ScheduleEvent> events = new ArrayList<>();
        FileReader f = new FileReader(file);
        BufferedReader br = new BufferedReader(f);
        String line;
        while((line = br.readLine()) != null){
            String[] tokens = line.split(",");
            ScheduleEvent se = new ScheduleEvent();
            se.setLogicJobName(tokens[0]);
            se.setJobId(Long.parseLong(tokens[1]));
            se.setCoalitionId(Long.parseLong(tokens[2]));
            se.setStart(Long.parseLong(tokens[3]));
            se.setEnd(Long.parseLong(tokens[4]));
            events.add(se);
        }
        return events;

    }
}
