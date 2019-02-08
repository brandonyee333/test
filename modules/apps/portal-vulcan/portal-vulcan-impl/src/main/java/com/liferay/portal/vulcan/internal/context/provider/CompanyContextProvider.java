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

package com.liferay.portal.vulcan.internal.context.provider;

import com.liferay.portal.kernel.model.Company;

import io.vavr.CheckedFunction1;

import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

/**
 * Allows JAX-RS resources to provide {@link Company} objects in method
 * parameters, fields or setters by annotating them with {@code
 * javax.ws.rs.core.Context}.
 *
 * @author Alejandro Hernández
 * @author Cristina González
 * @review
 */
@Provider
public class CompanyContextProvider implements ContextProvider<Company> {

	public CompanyContextProvider(
		CheckedFunction1<HttpServletRequest, Company>
			companyProviderCheckedFunction1) {

		_companyProviderFunction = companyProviderCheckedFunction1.unchecked();
	}

	@Override
	public Company createContext(Message message) {
		return _companyProviderFunction.compose(
			ContextProviderUtil::getHttpServletRequest
		).apply(
			message
		);
	}

	private final Function<HttpServletRequest, Company>
		_companyProviderFunction;

}