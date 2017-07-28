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

package com.liferay.osb.support.util;

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Kyle Bischof
 */
public class TicketEntryThreadLocal {

	public static boolean isReindex() {
		return _reindex.get();
	}

	public static boolean isSyncToJIRA() {
		return _syncToJIRA.get();
	}

	public static void setReindex(Boolean reindex) {
		_reindex.set(reindex);
	}

	public static void setSyncToJIRA(Boolean syncToJIRA) {
		_syncToJIRA.set(syncToJIRA);
	}

	private static final ThreadLocal<Boolean> _reindex =
		new InitialThreadLocal<>(
			TicketEntryThreadLocal.class + "._reindex", true);
	private static final ThreadLocal<Boolean> _syncToJIRA =
		new InitialThreadLocal<>(
			TicketEntryThreadLocal.class + "._syncToJIRA", true);

}