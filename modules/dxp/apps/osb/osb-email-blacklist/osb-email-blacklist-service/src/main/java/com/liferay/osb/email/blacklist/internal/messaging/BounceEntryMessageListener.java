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

package com.liferay.osb.email.blacklist.internal.messaging;

import com.liferay.osb.email.blacklist.internal.configuration.EmailBlacklistServiceConfigurationUtil;
import com.liferay.osb.email.blacklist.model.BounceEntry;
import com.liferay.osb.email.blacklist.service.BounceEntryLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author James Kennedy
 * @author Jamie Sammons
 */
@Component(
	immediate = true, property = "cron.expression=0 0 0 * * ?",
	service = BounceEntryMessageListener.class
)
public class BounceEntryMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties)
		throws SchedulerException {

		String cronExpression = GetterUtil.getString(
			properties.get("cron.expression"), _DEFAULT_CRON_EXPRESSION);

		String className = getClass().getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, new Date(), null, cronExpression);

		_schedulerEntryImpl = new SchedulerEntryImpl(
			getClass().getName(), trigger);

		if (_initialized) {
			deactivate();
		}

		_schedulerEngineHelper.register(
			this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

		_initialized = true;
	}

	@Deactivate
	protected void deactivate() {
		if (_initialized) {
			try {
				if (_log.isDebugEnabled()) {
					_log.debug("Unscheduling bounce entry message listener");
				}

				_schedulerEngineHelper.unschedule(
					_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);
			}
			catch (SchedulerException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to unscheduling bounce entry message listener",
						se);
				}
			}

			_schedulerEngineHelper.unregister(this);
		}

		_initialized = false;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Running bounce entry scheduled task");
		}

		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.add(
			Calendar.DATE,
			-1 *
				EmailBlacklistServiceConfigurationUtil.
					getBounceLimitWithinDays());

		List<BounceEntry> bounceEntries =
			_bounceEntryLocalService.getBounceEntries(
				CalendarUtil.getLTDate(calendar), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (BounceEntry bounceEntry : bounceEntries) {
			_bounceEntryLocalService.deleteBounceEntry(bounceEntry);
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
		SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	private static final String _DEFAULT_CRON_EXPRESSION = "0 0 0 * * ?";

	private static final Log _log = LogFactoryUtil.getLog(
		BounceEntryMessageListener.class);

	@Reference
	private BounceEntryLocalService _bounceEntryLocalService;

	private volatile boolean _initialized;
	private SchedulerEngineHelper _schedulerEngineHelper;
	private SchedulerEntryImpl _schedulerEntryImpl;
	private TriggerFactory _triggerFactory;

}