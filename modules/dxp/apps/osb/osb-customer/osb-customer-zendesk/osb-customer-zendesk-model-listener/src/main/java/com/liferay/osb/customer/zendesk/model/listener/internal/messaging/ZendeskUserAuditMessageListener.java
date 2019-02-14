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

package com.liferay.osb.customer.zendesk.model.listener.internal.messaging;

import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.listener.configuration.ZendeskModelListenerConfigurationValues;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;

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
@Component(immediate = true, service = ZendeskUserAuditMessageListener.class)
public class ZendeskUserAuditMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
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

	@Override
	protected void doReceive(Message message) throws Exception {
		Query query = _queryFactory.createQuery();

		query.addCriterion("external_id:none");
		query.addCriterion("-email:none");
		query.addCriterion("-user:no-reply@*");
		query.setPage(1);

		SearchHits<ZendeskUser> searchHits =
			_zendeskUserWebService.getZendeskUsers(query);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		String body = ContentUtil.get(
			ZendeskUserAuditMessageListener.class.getClassLoader(),
			"com/liferay/osb/customer/zendesk/model/listener/dependencies" +
				"/email_review_users_body.tmpl");

		List<ZendeskUser> zendeskUsers = searchHits.getResults();

		StringBundler sb = new StringBundler(zendeskUsers.size() * 6);

		for (ZendeskUser zendeskUser : zendeskUsers) {
			sb.append("<a href=\"");
			sb.append(ZendeskModelListenerConfigurationValues.ZENDESK_USER_URL);
			sb.append(zendeskUser.getZendeskUserId());
			sb.append("\">");
			sb.append(zendeskUser.getEmail());
			sb.append("</a><br />");
		}

		body = StringUtil.replace(body, "[$ZENDESK_USERS$]", sb.toString());

		subscriptionSender.setBody(body);

		subscriptionSender.setCompanyId(_portal.getDefaultCompanyId());
		subscriptionSender.setFrom(
			"noreply@liferay.com", "Liferay Help Center Team");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("review_users");
		subscriptionSender.setReplyToAddress("noreply@liferay.com");

		String subject = ContentUtil.get(
			ZendeskUserAuditMessageListener.class.getClassLoader(),
			"com/liferay/osb/customer/zendesk/model/listener/dependencies" +
				"/email_review_users_subject.tmpl");

		subscriptionSender.setSubject(subject);

		subscriptionSender.addRuntimeSubscribers(
			ZendeskModelListenerConfigurationValues.ZENDESK_ADMIN_EMAIL_ADDRESS,
			"Zendesk Admin");

		subscriptionSender.flushNotificationsAsync();
	}

	@Reference
	private Portal _portal;

	@Reference
	private QueryFactory _queryFactory;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}