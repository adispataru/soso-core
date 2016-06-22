package ro.ieat.soso.core.coalitions;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by adrian on 18.01.2016.
 * Class used to store machine attributes from google trace data.
 */
public class MachineAttribute {
    private String name;
    private String value;

    public MachineAttribute(String attrName, String attrValue) {
        this.name = attrName;
        this.value = attrValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
