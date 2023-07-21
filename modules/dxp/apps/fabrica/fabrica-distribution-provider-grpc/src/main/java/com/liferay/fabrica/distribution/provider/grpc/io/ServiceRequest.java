/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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