/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.event;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSEventManager.class)
public class LCSEventManager {

	public void publish(LCSEvent lcsEvent) {
		Set<LCSEventListener> lcsEventListeners =
			_lcsEventLCSEventListeners.get(lcsEvent);

		if (lcsEventListeners == null) {
			return;
		}

		for (LCSEventListener lcsEventListener : lcsEventListeners) {
			try {
				lcsEventListener.onLCSEvent(lcsEvent);
			}
			catch (Throwable t) {
				_log.error("Unable to notify listener", t);
			}
		}
	}

	public void subscribe(
		LCSEvent lcsEvent, LCSEventListener lcsEventListener) {

		Set<LCSEventListener> lcsEventListeners =
			_lcsEventLCSEventListeners.get(lcsEvent);

		if (lcsEventListeners == null) {
			lcsEventListeners = new HashSet<>();

			_lcsEventLCSEventListeners.put(lcsEvent, lcsEventListeners);
		}

		if (lcsEventListeners.contains(lcsEventListener)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						_LOG_PATTERN_EXISTING_SUBSCRIPTION,
						String.valueOf(lcsEventListener), lcsEvent.name()));
			}

			return;
		}

		lcsEventListeners.add(lcsEventListener);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					_LOG_PATTERN_NEW_SUBSCRIPTION,
					String.valueOf(lcsEventListener), lcsEvent.name()));
		}
	}

	public void unsubscribe(LCSEventListener lcsEventListener) {
		_lcsEventLCSEventListeners.forEach(
			(key, value) -> value.remove(lcsEventListener));
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Deactivate
	protected void deactivate() {
		_lcsEventLCSEventListeners.clear();

		if (_log.isTraceEnabled()) {
			_log.trace("Deactivated " + this);
		}
	}

	private static final String _LOG_PATTERN_EXISTING_SUBSCRIPTION =
		"%s already subscribed for event %s";

	private static final String _LOG_PATTERN_NEW_SUBSCRIPTION =
		"%s subscribed for event %s";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSEventManager.class);

	private final Map<LCSEvent, Set<LCSEventListener>>
		_lcsEventLCSEventListeners = new HashMap();

}