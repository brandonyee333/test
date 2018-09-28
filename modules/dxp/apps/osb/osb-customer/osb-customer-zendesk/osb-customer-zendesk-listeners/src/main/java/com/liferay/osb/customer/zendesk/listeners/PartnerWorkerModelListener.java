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

import com.liferay.osb.customer.zendesk.listeners.util.PartnerWorkerUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class PartnerWorkerModelListener
	extends BaseModelListener<PartnerWorker> {

	@Override
	public void onAfterCreate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				_partnerWorkerUtil.addPartnerWorker(
					partnerWorker, getZendeskOrganizationIds(partnerWorker));
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				_partnerWorkerUtil.removePartnerWorker(
					partnerWorker, getZendeskOrganizationIds(partnerWorker));
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				_partnerWorkerUtil.addPartnerWorker(
					partnerWorker, getZendeskOrganizationIds(partnerWorker));
			}
			else {
				_partnerWorkerUtil.removePartnerWorker(
					partnerWorker, getZendeskOrganizationIds(partnerWorker));
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected long[] getZendeskOrganizationIds(PartnerWorker partnerWorker)
		throws PortalException {

		PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

		List<AccountEntry> accountEntries = partnerEntry.getAccountEntries();

		List<Long> zendeskOrganizationIds = new ArrayList<>();

		for (AccountEntry accountEntry : accountEntries) {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			if (zendeskOrganizationId != 0) {
				zendeskOrganizationIds.add(zendeskOrganizationId);
			}
		}

		return ArrayUtil.toLongArray(zendeskOrganizationIds);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private PartnerWorkerUtil _partnerWorkerUtil;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}