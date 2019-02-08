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

package com.liferay.portal.vulcan.internal.context.provider.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

/**
 * Base class for those Vulcan's {@code ContextProvider} that only need the HTTP
 * request.
 *
 * @author Alejandro Hernández
 * @review
 */
public abstract class BaseContextProvider<T> implements ContextProvider<T> {

	@Override
	public T createContext(Message message) {
		Object request = message.getContextualProperty("HTTP.REQUEST");

		return createContext((HttpServletRequest)request);
	}

	protected abstract T createContext(HttpServletRequest request);

}