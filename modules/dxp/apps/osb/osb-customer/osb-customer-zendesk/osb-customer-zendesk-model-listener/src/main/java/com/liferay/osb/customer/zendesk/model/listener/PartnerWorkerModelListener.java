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

package com.liferay.osb.customer.zendesk.model.listener;

import com.liferay.osb.customer.zendesk.model.listener.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.PartnerWorkerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.UserSynchronizer;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

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
			_partnerWorkerSynchronizer.add(partnerWorker);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterRemove(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			_partnerWorkerSynchronizer.remove(partnerWorker);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if (((_oldRole.get() == PartnerWorkerConstants.ROLE_WATCHER) &&
				 (partnerWorker.getRole() !=
					 PartnerWorkerConstants.ROLE_WATCHER)) ||
				((_oldRole.get() != PartnerWorkerConstants.ROLE_WATCHER) &&
				 (partnerWorker.getRole() ==
					 PartnerWorkerConstants.ROLE_WATCHER))) {

				_partnerWorkerSynchronizer.updateRole(partnerWorker);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			PartnerWorker oldPartnerWorker =
				PartnerWorkerLocalServiceUtil.getPartnerWorker(
					partnerWorker.getPartnerWorkerId());

			_oldRole.set(oldPartnerWorker.getRole());
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PartnerWorkerModelListener.class);

	private static final ThreadLocal<Integer> _oldRole =
		new CentralizedThreadLocal<>(
			PartnerWorkerModelListener.class + "._oldRole");

	@Reference
	private PartnerWorkerSynchronizer _partnerWorkerSynchronizer;

	@Reference
	private UserSynchronizer _userSynchronizer;

}