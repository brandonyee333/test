/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.mockito;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Miguel Pastor
 */
public class ReturnArgumentCalledAnswer<T> implements Answer<T> {

	public ReturnArgumentCalledAnswer(int position) {
		_position = position;
	}

	@Override
	public T answer(InvocationOnMock invocationOnMock) {
		return (T)invocationOnMock.getArguments()[_position];
	}

	private final int _position;

}