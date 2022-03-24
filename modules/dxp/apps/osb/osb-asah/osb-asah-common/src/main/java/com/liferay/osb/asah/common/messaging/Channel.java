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

package com.liferay.osb.asah.common.messaging;

/**
 * @author Marcellus Tavares
 */
public enum Channel {

	ACTIVE_INDIVIDUAL_IDS, ANALYTICS_EVENTS, ANALYTICS_EVENTS_ACTIVITY,
	ANALYTICS_EVENTS_BLOG, ANALYTICS_EVENTS_CUSTOM_ASSET,
	ANALYTICS_EVENTS_DOCUMENT, ANALYTICS_EVENTS_FORM, ANALYTICS_EVENTS_JOURNAL,
	ANALYTICS_EVENTS_PAGE, ANALYTICS_EVENTS_SESSION, DATAPROC,
	DXP_ENTITIES_DEFAULT, DXP_ENTITIES_MESSAGE(true), DXP_ENTITIES_ORDER,
	DXP_ENTITIES_PRODUCT, IDENTITY_MESSAGE, SEARCH_QUERY_STRINGS;

	public boolean isOrderingEnabled() {
		return _orderingEnabled;
	}

	private Channel() {
		_orderingEnabled = false;
	}

	private Channel(boolean orderingEnabled) {
		_orderingEnabled = orderingEnabled;
	}

	private final boolean _orderingEnabled;

}