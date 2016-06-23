package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.coalitions.Machine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by adrian on 18.11.2015.
 * This class has the sole purpose of reading the machine attributes files from google cluster data traces and map them on
 * the internal representation.
 */
public class MachineAttributesReader {


    public void map(FileReader fileReader, Map<Long, Machine> result, long startTime, long endTime) throws IOException, InterruptedException {

        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(",");
            long timestamp = Long.parseLong(tokens[0]);
            long machineId = Long.parseLong(tokens[1]);
            String attrName = tokens[2];
            String attrValue = tokens[3];

            if(timestamp < startTime * 1000000)
                continue;
            if(timestamp > endTime * 1000000)
                return;

            if(result.containsKey(machineId)){
                result.get(machineId).addAttribute(attrName, attrValue);
            }


        }
        br.close();
        fileReader.close();
    }

}
