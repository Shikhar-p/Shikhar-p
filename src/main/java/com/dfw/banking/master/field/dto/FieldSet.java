package com.dfw.banking.master.field.dto;
import java.util.ArrayList;
import java.util.List;

public class FieldSet {	
	private String fieldSetId;
    private String fieldSetName;
    private List<Field> fields = new ArrayList<Field>();
    public String getFieldSetId() {
        return fieldSetId;
    }
    public void setFieldSetId(String fieldSetId) {
        this.fieldSetId = fieldSetId;
    }
    public String getFieldSetName() {
        return fieldSetName;
    }
    public void setFieldSetName(String fieldSetName) {
        this.fieldSetName = fieldSetName;
    }
    public List<Field> getFields() {
        return fields;
    }
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}



    

