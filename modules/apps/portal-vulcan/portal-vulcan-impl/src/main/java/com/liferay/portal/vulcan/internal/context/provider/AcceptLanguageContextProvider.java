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

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.context.AcceptLanguage;
import com.liferay.portal.vulcan.internal.context.AcceptLanguageImpl;
import com.liferay.portal.vulcan.internal.context.provider.base.BaseContextProvider;

import io.vavr.CheckedFunction1;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ext.Provider;

/**
 * @author Cristina González
 */
@Provider
public class AcceptLanguageContextProvider
	extends BaseContextProvider<AcceptLanguage> {

	public AcceptLanguageContextProvider(
		CheckedFunction1<HttpServletRequest, User> userCheckedFunction1) {

		_userCheckedFunction1 = userCheckedFunction1;
	}

	@Override
	public AcceptLanguage createContext(HttpServletRequest request) {
		return new AcceptLanguageImpl(
			request, () -> _userCheckedFunction1.apply(request));
	}

	private final CheckedFunction1<HttpServletRequest, User>
		_userCheckedFunction1;

}