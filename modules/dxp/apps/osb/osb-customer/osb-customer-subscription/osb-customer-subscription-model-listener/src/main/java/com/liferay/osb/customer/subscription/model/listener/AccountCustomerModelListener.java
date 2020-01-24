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

package com.liferay.osb.customer.subscription.model.listener;

import com.liferay.osb.customer.subscription.model.listener.util.DXPCloudStatusPageSubscriptionUtil;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountCustomerModelListener
	extends BaseModelListener<AccountCustomer> {

	@Override
	public void onAfterCreate(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			if (_dxpCloudStatusPageSubscriptionUtil.hasActiveDXPCloud(
					accountCustomer.getAccountEntry())) {

				_dxpCloudStatusPageSubscriptionUtil.subscribe(
					accountCustomer.getUserId());
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onAfterRemove(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			_dxpCloudStatusPageSubscriptionUtil.unsubscribe(
				accountCustomer.getUserId());
		}
		catch (Exception e) {
			_log.error(e, e);
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
		AccountCustomerModelListener.class);

	@Reference
	private DXPCloudStatusPageSubscriptionUtil
		_dxpCloudStatusPageSubscriptionUtil;

}