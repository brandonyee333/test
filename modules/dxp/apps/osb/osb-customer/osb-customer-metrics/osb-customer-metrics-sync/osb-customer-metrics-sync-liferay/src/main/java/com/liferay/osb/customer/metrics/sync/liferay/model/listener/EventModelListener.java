/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model.listener;

import com.liferay.osb.customer.account.entry.details.model.Event;
import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class EventModelListener extends BaseMetricsModelListener<Event> {
}