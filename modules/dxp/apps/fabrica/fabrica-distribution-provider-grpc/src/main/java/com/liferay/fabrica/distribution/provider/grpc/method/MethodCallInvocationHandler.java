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