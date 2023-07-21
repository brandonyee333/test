/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.event;

import com.liferay.sync.engine.document.library.handler.Handler;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public interface Event extends Runnable {

	public void cancel();

	public Handler<Void> getHandler();

	public Map<String, Object> getParameters();

	public Object getParameterValue(String key);

	public long getSyncAccountId();

	public String getURLPath();

	public boolean isCancelled();

}