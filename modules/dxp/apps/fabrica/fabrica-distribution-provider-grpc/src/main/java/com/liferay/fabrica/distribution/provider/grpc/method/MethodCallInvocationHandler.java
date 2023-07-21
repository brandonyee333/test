/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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