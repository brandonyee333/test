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

package com.liferay.osb.customer.zendesk.synchronizer.listener.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	service = AutoSynchronizeAccountEntriesMessageListener.class
)
public class AutoSynchronizeAccountEntriesMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
	}

	/*@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		if (ZendeskSynchronizerConfigurationValues.
				ZENDESK_ACCOUNT_ENTRY_SYNC_INTERVAL <= 0) {

			return;
		}

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null,
			ZendeskSynchronizerConfigurationValues.
				ZENDESK_ACCOUNT_ENTRY_SYNC_INTERVAL,
			TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		if (ZendeskSynchronizerConfigurationValues.
				ZENDESK_ACCOUNT_ENTRY_SYNC_INTERVAL <= 0) {

			return;
		}

		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		List<AccountEntry> accountEntries = _accountEntryLocalService.search(
			null, null, 0,
			ZendeskSynchronizerConfigurationValues.
				ZENDESK_ACCOUNT_ENTRY_SYNC_BATCH,
			new AccountEntryLastZendeskAuditDateComparator());

		for (AccountEntry accountEntry : accountEntries) {
			Message synchronizeMessage = new Message();

			synchronizeMessage.put(
				"accountEntryId", accountEntry.getAccountEntryId());

			MessageBusUtil.sendMessage(
				ZendeskDestinationNames.ACCOUNT_ENTRY_SYNC, synchronizeMessage);
		}
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;*/

}