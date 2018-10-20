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

package com.liferay.osb.customer.account.entry.details.web.internal.messaging;

import com.liferay.osb.customer.ticket.constants.TicketAttachmentConstants;
import com.liferay.osb.customer.ticket.service.TicketAttachmentLocalService;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.osb.customer.zendesk.web.service.search.TicketQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Time;

import java.text.Format;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "days.closed=30",
	service = CleanTicketAttachmentsMessageListener.class
)
public class CleanTicketAttachmentsMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_daysClosed = GetterUtil.getInteger(properties.get("days.closed"));
		_simpleDateFormat = _fastDateFormatFactory.getSimpleDateFormat(
			"yyyy-MM-dd");

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, 1, TimeUnit.DAY);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	protected void deleteTicketAttachments(List<ZendeskTicket> zendeskTickets)
		throws PortalException {

		for (ZendeskTicket zendeskTicket : zendeskTickets) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Deleting attachments for ticket " +
						zendeskTicket.getZendeskTicketId());
			}

			_ticketAttachmentLocalService.deleteTicketAttachments(
				zendeskTicket.getZendeskTicketId(),
				new int[] {TicketAttachmentConstants.TYPE_REGULAR});
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		TicketQuery ticketQuery = _queryFactory.createTicketQuery();

		ticketQuery.addCriterion("status:closed");

		Date startDate = new Date(
			System.currentTimeMillis() - ((_daysClosed + 7) * Time.DAY));
		Date endDate = new Date(
			System.currentTimeMillis() - (_daysClosed * Time.DAY));

		ticketQuery.addCriterion(
			"updated>" + _simpleDateFormat.format(startDate));
		ticketQuery.addCriterion(
			"updated<" + _simpleDateFormat.format(endDate));

		int page = 1;

		while (page > 0) {
			ticketQuery.setPage(page);

			SearchHits<ZendeskTicket> searchHits =
				_zendeskTicketWebService.search(ticketQuery);

			deleteTicketAttachments(searchHits.getResults());

			page = searchHits.getNextPage();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CleanTicketAttachmentsMessageListener.class);

	private int _daysClosed;

	@Reference
	private FastDateFormatFactory _fastDateFormatFactory;

	@Reference
	private QueryFactory _queryFactory;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	private Format _simpleDateFormat;

	@Reference
	private TicketAttachmentLocalService _ticketAttachmentLocalService;

	@Reference
	private TriggerFactory _triggerFactory;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}