/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.web.internal.messaging;

import com.liferay.osb.customer.zendesk.documentation.web.internal.translator.ZendeskArticleTranslator;
import com.liferay.osb.customer.zendesk.documentation.web.internal.translator.ZendeskCategoryTranslator;
import com.liferay.osb.customer.zendesk.documentation.web.internal.translator.ZendeskSectionTranslator;
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
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = AutoTranslatorMessageListener.class)
public class AutoTranslatorMessageListener extends BaseMessageListener {

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
		Date createdAfterDate = new Date(
			System.currentTimeMillis() - (7 * Time.DAY));

		_zendeskArticleTranslator.autoTranslate(createdAfterDate);
		_zendeskCategoryTranslator.autoTranslate(createdAfterDate);
		_zendeskSectionTranslator.autoTranslate(createdAfterDate);
	}

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

	@Reference
	private ZendeskArticleTranslator _zendeskArticleTranslator;

	@Reference
	private ZendeskCategoryTranslator _zendeskCategoryTranslator;

	@Reference
	private ZendeskSectionTranslator _zendeskSectionTranslator;

}