/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBMessageIterator;

import java.util.List;

/**
 * @author Sergio González
 */
public class MBMessageIteratorImpl implements MBMessageIterator {

	public MBMessageIteratorImpl(List<MBMessage> messages, int from, int to) {
		_messages = messages;
		_from = from;
		_to = to;
	}

	@Override
	public int getIndexPage() {
		return _from;
	}

	@Override
	public boolean hasNext() {
		if (_from < _to) {
			return true;
		}

		return false;
	}

	@Override
	public MBMessage next() {
		MBMessage message = _messages.get(_from);

		_from++;

		return message;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private int _from;
	private final List<MBMessage> _messages;
	private final int _to;

}