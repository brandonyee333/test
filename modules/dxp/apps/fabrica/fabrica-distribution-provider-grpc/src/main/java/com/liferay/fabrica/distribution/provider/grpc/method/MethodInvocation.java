/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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