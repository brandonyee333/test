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