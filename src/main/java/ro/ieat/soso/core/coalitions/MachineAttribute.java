package ro.ieat.soso.core.coalitions;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by adrian on 18.01.2016.
 * Class used to store machine attributes from google trace data.
 */
public class MachineAttribute {
    @Id
    private String name;
    private List<MachineAttributeValue> values;

    public List<MachineAttributeValue> getValues() {
        return values;
    }

    public void setValues(List<MachineAttributeValue> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Created by adrian on 18.01.2016.
     * Data structure for representing a mapping between attribute values and machines that
     * express them.
     */
    public static class MachineAttributeValue {
        private String value;
        private List<Long> machineList;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<Long> getMachineList() {
            return machineList;
        }

        public void setMachineList(List<Long> machineList) {
            this.machineList = machineList;
        }
    }
}
