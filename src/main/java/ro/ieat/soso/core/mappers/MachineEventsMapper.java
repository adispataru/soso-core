package ro.ieat.soso.core.mappers;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adrian on 07.12.2015.
 */
public class MachineEventsMapper {
    public static final Map<Long, Pair<Double, Double>> MACHINES = new TreeMap<>();

    public static void map(FileReader fileReader, long start, long end) throws IOException, InterruptedException {

        BufferedReader br = new BufferedReader(fileReader);
        String line;
        String[] tokens;
        while ((line = br.readLine()) != null) {
            tokens = line.split(",");
            long timestamp = Long.parseLong(tokens[0]);
//            if(timestamp < start * 1000000)
//                continue;
            if(timestamp > end * 1000000)
                return;
            long machineId = Long.parseLong(tokens[1]);
            long event = Long.parseLong(tokens[2]);

            if(event == 1 || tokens.length < 6)
                continue;

            //System.out.println(line);
            Double cpu = Double.parseDouble(tokens[4]);
            Double mem = Double.parseDouble(tokens[5]);

            MACHINES.put(machineId, new Pair<>(cpu, mem));
        }
        br.close();
        fileReader.close();
    }
}
