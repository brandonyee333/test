/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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