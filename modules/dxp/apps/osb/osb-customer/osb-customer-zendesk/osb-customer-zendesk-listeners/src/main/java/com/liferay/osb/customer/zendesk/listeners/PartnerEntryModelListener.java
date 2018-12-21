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

package com.liferay.osb.customer.zendesk.listeners;

import com.liferay.osb.customer.zendesk.listeners.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.listeners.synchronizer.AccountEntrySynchronizer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class PartnerEntryModelListener extends BaseModelListener<PartnerEntry> {

	@Override
	public void onAfterUpdate(PartnerEntry partnerEntry)
		throws ModelListenerException {

		try {
			if (partnerEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				return;
			}

			for (AccountEntry accountEntry :
					partnerEntry.getPartnerManagedAccountEntries()) {

				_accountEntrySynchronizer.update(accountEntry);
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PartnerEntryModelListener.class);

	@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;

}