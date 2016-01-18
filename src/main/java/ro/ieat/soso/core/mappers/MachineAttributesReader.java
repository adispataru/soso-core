package ro.ieat.soso.core.mappers;


import ro.ieat.soso.core.coalitions.MachineAttribute;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by adrian on 18.11.2015.
 * This class has the sole purpose of reading the machine attributes files from google cluster data traces and map them on
 * the internal representation.
 */
public class MachineAttributesReader {

    public static void map(FileReader fileReader, Map<String, MachineAttribute> result, long startTime, long endTime) throws IOException, InterruptedException {

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

            MachineAttribute machineAttribute;
            if (!result.containsKey(attrName)){
                machineAttribute = new MachineAttribute();
                machineAttribute.setName(attrName);
                machineAttribute.setValues(new ArrayList<>());
                result.put(attrName, machineAttribute);
            }

            machineAttribute = result.get(attrName);
            boolean hasValue = false;
            for(MachineAttribute.MachineAttributeValue value : machineAttribute.getValues()){
                if(value.getValue().equals(attrValue)){
                    if(!value.getMachineList().contains(machineId))
                        value.getMachineList().add(machineId);
                    hasValue = true;
                }

            }
            if (!hasValue){
                MachineAttribute.MachineAttributeValue v = new MachineAttribute.MachineAttributeValue();
                v.setMachineList(new ArrayList<>());
                v.getMachineList().add(machineId);
                v.setValue(attrValue);
                machineAttribute.getValues().add(v);
            }
        }
        br.close();
        fileReader.close();
    }
}
