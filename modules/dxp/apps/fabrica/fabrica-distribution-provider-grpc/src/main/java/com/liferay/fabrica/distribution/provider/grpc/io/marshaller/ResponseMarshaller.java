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

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import io.grpc.MethodDescriptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Miguel Pastor
 */
public class ResponseMarshaller implements MethodDescriptor.Marshaller<Object> {

	@Override
	public Object parse(InputStream inputStream) {
		Input input = new Input(inputStream);

		Object object = _kryo.readClassAndObject(input);

		input.close();

		return object;
	}

	@Override
	public InputStream stream(Object object) {
		OutputStream outputStream = new ByteArrayOutputStream();

		Output output = new Output(outputStream);

		_kryo.writeClassAndObject(output, object);

		return new ByteArrayInputStream(output.toBytes());
	}

	private final Kryo _kryo = new Kryo();

}