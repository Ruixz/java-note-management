package com.ray.jnm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassEditor;

import com.ray.jnm.entity.WorkGroup;

//class binding needs
public class WorkGroupClassEditor extends ClassEditor {
//	@Autowired
//	private static WorkGroup workGroup;
	
	/*
	private String value;
	public WGClassEditor(String value)
	{
		this.value = value;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAsText() {
		// TODO Auto-generated method stub
		return ((WorkGroup) this.getValue()).getName().toString();
	}

	@Override
	public Component getCustomEditor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJavaInitializationString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPaintable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void paintValue(Graphics gfx, Rectangle box) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsText(String value) throws IllegalArgumentException {
		WorkGroup wg = new WorkGroup();
		wg.setName(this.value);
		this.setValue(wg);
		
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsCustomEditor() {
		// TODO Auto-generated method stub
		return false;
	}*/

	
//	private String value;
//	public WGClassEditor(String value)
//	{
//		this.value = value;
//	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		WorkGroup workGroup = new WorkGroup();
		workGroup.setName(text);
		this.setValue(workGroup);
	}

	@Override
	public String getAsText() {
		// TODO Auto-generated method stub
		return super.getAsText();
		}
}
