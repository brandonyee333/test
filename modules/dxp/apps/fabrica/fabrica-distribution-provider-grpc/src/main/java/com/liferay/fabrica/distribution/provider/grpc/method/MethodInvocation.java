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

import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Miguel Pastor
 */
public class MethodInvocation
	implements ServerCalls.UnaryMethod<ServiceRequest, Object> {

	public MethodInvocation(Object service, Method method) {
		_service = service;
		_method = method;
	}

	@Override
	public void invoke(
		ServiceRequest serviceRequest, StreamObserver<Object> streamObserver) {

		try {
			Object o = _method.invoke(_service, serviceRequest.getArguments());

			streamObserver.onNext(o);
		}
		catch (InvocationTargetException ite) {
			streamObserver.onError(ite);
		}
		catch (IllegalAccessException iae) {
			streamObserver.onError(iae);
		}
		finally {
			streamObserver.onCompleted();
		}
	}

	private final Method _method;
	private final Object _service;

}