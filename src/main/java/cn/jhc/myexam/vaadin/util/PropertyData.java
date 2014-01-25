package cn.jhc.myexam.vaadin.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.jhc.myexam.annotation.Description;
import cn.jhc.myexam.annotation.ImportColumn;

public class PropertyData implements Serializable {
	private static final long serialVersionUID = 4374520611899215576L;
	public static PropertyData createPropertyData(Class<?> clazz) {
		return new PropertyData(clazz);
	}

	private List<String> propertyNameList = new ArrayList<String>();
	private List<String> descriptionList = new ArrayList<String>();
	private List<String> importColumnList = new ArrayList<String>();

	private PropertyData(Class<?> clazz) {
		for(Field field : clazz.getDeclaredFields()) {
			Description description = field.getAnnotation(Description.class);
			ImportColumn importColumn = field.getAnnotation(ImportColumn.class);
			if(description != null) {
				propertyNameList.add(field.getName());
				descriptionList.add(description.value());
				if(importColumn != null)
					importColumnList.add(description.value());
			}
		}
	}

	public List<String> getPropertyNameList() {
		return propertyNameList;
	}

	public List<String> getDescriptionList() {
		return descriptionList;
	}

	public List<String> getImportColumnList() {
		return importColumnList;
	}
}