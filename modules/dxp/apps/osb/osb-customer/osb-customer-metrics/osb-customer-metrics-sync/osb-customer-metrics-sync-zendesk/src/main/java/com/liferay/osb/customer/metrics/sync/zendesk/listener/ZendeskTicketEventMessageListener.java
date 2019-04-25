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

package com.liferay.osb.customer.metrics.sync.zendesk.listener;

import com.liferay.osb.customer.metrics.sync.model.SyncState;
import com.liferay.osb.customer.metrics.sync.service.SyncStateLocalService;
import com.liferay.osb.customer.metrics.sync.zendesk.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskTicketEvent;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.Time;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ZendeskTicketEventMessageListener.class)
public class ZendeskTicketEventMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, 15, TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		SyncState syncState = _syncStateLocalService.fetchSyncState(
			ZendeskTicketEvent.class.getName());

		if (syncState == null) {
			syncState = _syncStateLocalService.addSyncState(
				ZendeskTicketEvent.class.getName());
		}

		Date now = new Date();

		long nowSeconds = now.getTime() / Time.SECOND;

		if (!_sync(nowSeconds, syncState.getLastRunTime())) {
			return;
		}

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.INCREMENTAL_TICKET_EVENTS;

		Map<String, String> parameters = new HashMap<>();

		parameters.put("include", "comment_events");
		parameters.put(
			"start_time", String.valueOf(syncState.getLastRunTime()));

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "get", parameters, null,
			"zendesk.metrics.ticket.event.update");

		_messagePublisherUtil.sendZendeskMessage(zendeskRequest);
	}

	private boolean _sync(long nowSeconds, long lastRunTime) {
		if ((nowSeconds - lastRunTime) > 60) {
			return true;
		}

		return false;
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private SyncStateLocalService _syncStateLocalService;

	@Reference
	private TriggerFactory _triggerFactory;

}