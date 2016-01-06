package ro.ieat.soso.core.mappers;

import org.codehaus.jackson.map.ObjectMapper;
import ro.ieat.soso.core.coalitions.Coalition;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by adrian on 01.12.2015.
 * This class has the sole purpose of combining jobs with the same id (meaning they are tasks of the same job) into
 * a coalition representing the usage of that job.
 */
public class CoalitionMapper {

    public static Map<Long, Coalition> map(String coalitionFolder) throws IOException {
        Map<Long, Coalition> result = new TreeMap<Long, Coalition>();
        File dir = new File(coalitionFolder);
        System.out.println(dir);
        for(File file : dir.listFiles()){
            if(file.isDirectory())
                continue;
            String[] path = file.getAbsolutePath().split("/");
            String id = path[path.length - 1];
            ObjectMapper objectMapper = new ObjectMapper();
            Coalition coalition = objectMapper.reader(Coalition.class).readValue(file);
            coalition.setId(Long.parseLong(id));
            result.put(coalition.getId(), coalition);
        }
        return result;
    }

    public static Coalition readCoalition(String coalitionFolder, long id) throws IOException{
        String path = coalitionFolder + id;
        ObjectMapper objectMapper = new ObjectMapper();

        Coalition c =  objectMapper.reader(Coalition.class).readValue(new File(path));
        c.setId(id);
        return c;
    }
}
