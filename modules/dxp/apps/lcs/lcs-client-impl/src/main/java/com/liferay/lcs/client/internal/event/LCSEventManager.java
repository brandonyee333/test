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
						_LOG_PATTERN_EXISTING_REGISTRATION,
						String.valueOf(lcsEventListener), lcsEvent.name()));
			}

			return;
		}

		lcsEventListeners.add(lcsEventListener);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					_LOG_PATTERN_NEW_REGISTRATION,
					String.valueOf(lcsEventListener), lcsEvent.name()));
		}
	}

	@Activate
	protected void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	private static final String _LOG_PATTERN_EXISTING_REGISTRATION =
		"%s already registered for event %s";

	private static final String _LOG_PATTERN_NEW_REGISTRATION =
		"%s registered for event %s";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSEventManager.class);

	private final Map<LCSEvent, Set<LCSEventListener>>
		_lcsEventLCSEventListeners = new HashMap();

}