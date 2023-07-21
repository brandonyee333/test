/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.distribution.provider.grpc.io.marshaller;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import com.liferay.fabrica.distribution.provider.grpc.io.ServiceRequest;

import io.grpc.MethodDescriptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Miguel Pastor
 */
public class RequestMarshaller
	implements MethodDescriptor.Marshaller<ServiceRequest> {

	@Override
	public ServiceRequest parse(InputStream inputStream) {
		Input input = new Input(inputStream);

		return _kryo.readObject(input, ServiceRequest.class);
	}

	@Override
	public InputStream stream(ServiceRequest serviceRequest) {
		OutputStream outputStream = new ByteArrayOutputStream();

		Output output = new Output(outputStream);

		_kryo.writeClassAndObject(output, serviceRequest);

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			output.toBytes());

		output.close();

		return byteArrayInputStream;
	}

	private final Kryo _kryo = new Kryo();

}