/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.fabrica.distribution.provider.grpc.io.marshaller;

import com.liferay.fabrica.distribution.provider.grpc.io.ServiceRequest;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Pastor
 */
public class MarshallerTest {

	@Test
	public void testMarshallComplexCustomObjectArgument() {
		SimpleCustomObject simpleCustomObject = new SimpleCustomObject(
			11, 11311L, "Foo");

		List<SimpleCustomObject> simpleCustomObjects = new ArrayList<>();

		simpleCustomObjects.add(simpleCustomObject);
		simpleCustomObjects.add(simpleCustomObject);

		ComplexCustomObject complexCustomObject = new ComplexCustomObject(
			"complex", simpleCustomObjects);

		Object[] objects = _streamAndParse(complexCustomObject);

		Assert.assertEquals(objects.toString(), 1, objects.length);

		complexCustomObject = (ComplexCustomObject)objects[0];

		Assert.assertEquals("complex", complexCustomObject.s);

		simpleCustomObjects = complexCustomObject.simpleCustomObjects;

		Assert.assertEquals(
			simpleCustomObjects.toString(), 2, simpleCustomObjects.size());

		simpleCustomObject = simpleCustomObjects.get(0);

		Assert.assertEquals(11, simpleCustomObject.i);
		Assert.assertEquals(11311L, simpleCustomObject.l);
		Assert.assertEquals("Foo", simpleCustomObject.s);

		simpleCustomObject = simpleCustomObjects.get(1);

		Assert.assertEquals(11, simpleCustomObject.i);
		Assert.assertEquals(11311L, simpleCustomObject.l);
		Assert.assertEquals("Foo", simpleCustomObject.s);
	}

	@Test
	public void testMarshallNoArguments() {
		Object[] objects = _streamAndParse();

		Assert.assertEquals(objects.toString(), 0, objects.length);
	}

	@Test
	public void testMarshallPrimitiveIntegerArgument() {
		Object[] objects = _streamAndParse(1);

		Assert.assertEquals(objects.toString(), 1, objects.length);
		Assert.assertEquals(1, objects[0]);
	}

	@Test
	public void testMarshallPrimitiveIntegerArguments() {
		Object[] objects = _streamAndParse(1, 2, 3);

		Assert.assertEquals(objects.toString(), 3, objects.length);
		Assert.assertEquals(1, objects[0]);
		Assert.assertEquals(2, objects[1]);
		Assert.assertEquals(3, objects[2]);
	}

	@Test
	public void testMarshallSimpleCustomObjectArgument() {
		SimpleCustomObject simpleCustomObject = new SimpleCustomObject(
			11, 11311L, "Foo");

		Object[] objects = _streamAndParse(simpleCustomObject);

		Assert.assertEquals(objects.toString(), 1, objects.length);

		simpleCustomObject = (SimpleCustomObject)objects[0];

		Assert.assertEquals(11, simpleCustomObject.i);
		Assert.assertEquals(11311L, simpleCustomObject.l);
		Assert.assertEquals("Foo", simpleCustomObject.s);
	}

	@Test
	public void testMarshallSimpleCustomObjectArguments() {
		SimpleCustomObject simpleCustomObject1 = new SimpleCustomObject(
			11, 11311L, "Foo");
		SimpleCustomObject simpleCustomObject2 = new SimpleCustomObject(
			12, 11312L, "Bar");

		Object[] objects = _streamAndParse(
			simpleCustomObject1, simpleCustomObject2);

		Assert.assertEquals(objects.toString(), 2, objects.length);

		SimpleCustomObject simpleCustomObject = (SimpleCustomObject)objects[0];

		Assert.assertEquals(11, simpleCustomObject.i);
		Assert.assertEquals(11311L, simpleCustomObject.l);
		Assert.assertEquals("Foo", simpleCustomObject.s);

		simpleCustomObject = (SimpleCustomObject)objects[1];

		Assert.assertEquals(12, simpleCustomObject.i);
		Assert.assertEquals(11312L, simpleCustomObject.l);
		Assert.assertEquals("Bar", simpleCustomObject.s);
	}

	@Test
	public void testMarshallStringArgument() {
		Object[] objects = _streamAndParse("Foo");

		Assert.assertEquals(objects.toString(), 1, objects.length);
		Assert.assertEquals("Foo", objects[0]);
	}

	@Test
	public void testMarshallStringArguments() {
		Object[] objects = _streamAndParse("Foo", "Bar");

		Assert.assertEquals(objects.toString(), 2, objects.length);
		Assert.assertEquals("Foo", objects[0]);
		Assert.assertEquals("Bar", objects[1]);
	}

	private Object[] _streamAndParse(Object... arguments) {
		ServiceRequest serviceRequest = new ServiceRequest(arguments);

		InputStream inputStream = _requestMarshaller.stream(serviceRequest);

		serviceRequest = (ServiceRequest)_responseMarshaller.parse(inputStream);

		return serviceRequest.getArguments();
	}

	private final RequestMarshaller _requestMarshaller =
		new RequestMarshaller();
	private final ResponseMarshaller _responseMarshaller =
		new ResponseMarshaller();

	private static final class ComplexCustomObject {

		public ComplexCustomObject() {
			simpleCustomObjects = Collections.emptyList();
		}

		public ComplexCustomObject(
			String s, List<SimpleCustomObject> simpleCustomObjects) {

			this.s = s;
			this.simpleCustomObjects = simpleCustomObjects;
		}

		public String s;
		public List<SimpleCustomObject> simpleCustomObjects;

	}

	private static final class SimpleCustomObject {

		public SimpleCustomObject() {
		}

		public SimpleCustomObject(int i, long l, String s) {
			this.i = i;
			this.l = l;
			this.s = s;
		}

		public int i;
		public long l;
		public String s;

	}

}