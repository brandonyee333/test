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

package com.liferay.fabrica.distribution.provider.grpc.io;

import java.io.Serializable;

/**
 * @author Miguel Pastor
 */
public class ServiceRequest implements Serializable {

	public ServiceRequest() {
		this(new Object[0]);
	}

	public ServiceRequest(Object... arguments) {
		_arguments = arguments;
	}

	public Object[] getArguments() {
		return _arguments;
	}

	private final Object[] _arguments;

}