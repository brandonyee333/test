/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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