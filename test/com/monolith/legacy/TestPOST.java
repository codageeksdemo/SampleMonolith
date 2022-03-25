package com.monolith.legacy;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPOST {

	DependeeClass d ;
	String stringValue = "This is String";
	ClassA<String> a;
	@Before
	public void setup()
	{
		d = new DependeeClass();
		String stringValue = "This is String";
		a = new ClassA<String>();
		
	}
	@Test
	public void testInternals() throws UnsupportedEncodingException,  InterruptedException, ExecutionException, InstantiationException, IllegalAccessException,  InvocationTargetException {
		d.setInternals(1, stringValue, 1.1f, 'c', 1.0d);
		String value = d.getInternals(1);
		String expected="1,This is String,1.1,c,1.0";
		Assert.assertEquals(expected,value);
		
		
	}
	
	@Test
	public void testRefs() throws UnsupportedEncodingException,  InterruptedException, ExecutionException, InstantiationException, IllegalAccessException,  InvocationTargetException {
		
		a.setValue(stringValue);
		d.setA(a);
		ClassA<String> newA ;
		newA = d.getA();
		Object currentValue = newA.getValue();
		Assert.assertEquals(stringValue, currentValue);
		
		stringValue = "Object B";
		Assert.assertNotEquals(stringValue, d.getA().getValue());
		
		a.setValue(stringValue);
		d.setA(a);
		Assert.assertEquals(stringValue, d.getA().getValue());
		
	}

}
