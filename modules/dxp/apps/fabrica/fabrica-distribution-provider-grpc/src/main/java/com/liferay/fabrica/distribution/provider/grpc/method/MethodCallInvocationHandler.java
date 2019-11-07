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

package com.liferay.fabrica.distribution.provider.grpc.method;

import com.liferay.fabrica.distribution.provider.grpc.io.ServiceRequest;
import com.liferay.fabrica.distribution.provider.grpc.io.marshaller.RequestMarshaller;
import com.liferay.fabrica.distribution.provider.grpc.io.marshaller.ResponseMarshaller;

import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.stub.ClientCalls;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Miguel Pastor
 */
public class MethodCallInvocationHandler implements InvocationHandler {

	public MethodCallInvocationHandler(
		String serverName, ManagedChannel managedChannel) {

		_serverName = serverName;
		_managedChannel = managedChannel;
	}

	@Override
	public Object invoke(Object object, Method method, Object[] args)
		throws Throwable {

		MethodDescriptor<ServiceRequest, Object> methodDescriptor =
			MethodDescriptor.create(
				MethodDescriptor.MethodType.UNARY,
				_serverName + "/" + method.getName(), new RequestMarshaller(),
				new ResponseMarshaller());

		ClientCall<ServiceRequest, Object> clientCall = _managedChannel.newCall(
			methodDescriptor, CallOptions.DEFAULT);

		return ClientCalls.blockingUnaryCall(
			clientCall, new ServiceRequest(args));
	}

	private final ManagedChannel _managedChannel;
	private final String _serverName;

}