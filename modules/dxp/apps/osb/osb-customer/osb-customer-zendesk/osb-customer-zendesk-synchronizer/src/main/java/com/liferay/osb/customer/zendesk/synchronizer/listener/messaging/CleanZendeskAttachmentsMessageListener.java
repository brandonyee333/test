/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.synchronizer.listener.messaging;

import com.liferay.osb.customer.zendesk.model.ZendeskAttachment;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.model.ZendeskTicketComment;
import com.liferay.osb.customer.zendesk.web.service.ZendeskAttachmentWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketCommentWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.petra.string.StringBundler;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "days.solved=27",
	service = CleanZendeskAttachmentsMessageListener.class
)
public class CleanZendeskAttachmentsMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_daysSolved = GetterUtil.getInteger(properties.get("days.solved"));
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

	protected void deleteZendeskAttachments(List<ZendeskTicket> zendeskTickets)
		throws Exception {

		for (ZendeskTicket zendeskTicket : zendeskTickets) {
			List<ZendeskTicketComment> zendeskTicketComments =
				_zendeskTicketCommentWebService.getZendeskTicketComments(
					zendeskTicket.getZendeskTicketId());

			for (ZendeskTicketComment zendeskTicketComment :
					zendeskTicketComments) {

				for (ZendeskAttachment zendeskAttachment :
						zendeskTicketComment.getZendeskAttachments()) {

					if (isKeepAttachment(zendeskAttachment.getFileName())) {
						continue;
					}

					if (_log.isDebugEnabled()) {
						_log.debug(
							StringBundler.concat(
								"Deleting ", zendeskAttachment.getFileName(),
								" for ticket ",
								String.valueOf(
									zendeskTicket.getZendeskTicketId())));
					}

					_asyncZendeskAttachmentWebService.deleteZendeskAttachment(
						zendeskTicket.getZendeskTicketId(),
						zendeskTicketComment.getZendeskTicketCommentId(),
						zendeskAttachment.getZendeskAttachmentId());
				}
			}
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		Set<String> criteria = new HashSet<>();

		criteria.add("status:solved");

		Date solveDate = new Date(
			System.currentTimeMillis() - (_daysSolved * Time.DAY));

		criteria.add("solved<=" + _simpleDateFormat.format(solveDate));

		List<ZendeskTicket> zendeskTickets =
			_zendeskTicketWebService.getZendeskTickets(criteria);

		deleteZendeskAttachments(zendeskTickets);
	}

	protected boolean isKeepAttachment(String fileName) {
		if (fileName.startsWith("activation-key-") &&
			fileName.endsWith(".xml")) {

			return true;
		}

		if ((fileName.startsWith("liferay-fix-pack-") ||
			 fileName.startsWith("liferay-hotfix-") ||
			 fileName.startsWith("liferay-security-")) &&
			fileName.endsWith(".zip")) {

			return true;
		}

		if (fileName.startsWith("liferay-inc-") && fileName.endsWith(".json")) {
			return true;
		}

		if (fileName.endsWith(".groovy")) {
			return true;
		}

		if (fileName.endsWith(".support")) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CleanZendeskAttachmentsMessageListener.class);

	@Reference(target = "(async=true)")
	private ZendeskAttachmentWebService _asyncZendeskAttachmentWebService;

	private int _daysSolved;

	@Reference
	private FastDateFormatFactory _fastDateFormatFactory;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	private Format _simpleDateFormat;

	@Reference
	private TriggerFactory _triggerFactory;

	@Reference
	private ZendeskTicketCommentWebService _zendeskTicketCommentWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

}