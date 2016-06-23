package ro.ieat.soso.core.mappers;



import ro.ieat.soso.core.coalitions.Machine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adrian on 07.12.2015.
 */
public class MachineEventsMapper {


    public static void map(FileReader fileReader, long start, long end, Map<Long, Machine> result) throws IOException, InterruptedException {

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
            int event = Integer.parseInt(tokens[2]);

            if(tokens.length < 6)
                continue;

            //System.out.println(line);
            Double cpu = Double.parseDouble(tokens[4]);
            Double mem = Double.parseDouble(tokens[5]);

            switch (event){
                case 0:
                    if(result.get(machineId) == null) {
                        result.put(machineId, new Machine(machineId, cpu, mem, timestamp));
                    }else{
                        result.get(machineId).turnOn(timestamp);
                    }
                    break;
                case 1:
                    result.get(machineId).turnOff(timestamp);
                    break;
                case 2:
                    if(result.containsKey(machineId)) {
                        result.get(machineId).setCpu(cpu);
                        result.get(machineId).setMemory(mem);
                    }else{
                        result.put(machineId, new Machine(machineId, cpu, mem, timestamp));
                    }
                    break;
            }
        }
        br.close();
        fileReader.close();
    }
}
