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
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
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
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class OfferingEntryModelListener
	extends BaseModelListener<OfferingEntry> {

	@Override
	public void onAfterCreate(OfferingEntry offeringEntry)
		throws ModelListenerException {

		try {
			if (offeringEntry.getStatus() !=
					OfferingEntryConstants.STATUS_ACTIVE) {

				return;
			}

			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (!productEntry.isDXPCloud()) {
				return;
			}

			_dxpCloudStatusPageSubscriptionUtil.subscribe(
				offeringEntry.getAccountEntry());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onAfterRemove(OfferingEntry offeringEntry)
		throws ModelListenerException {

		try {
			if (offeringEntry.getStatus() !=
					OfferingEntryConstants.STATUS_ACTIVE) {

				return;
			}

			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (!productEntry.isDXPCloud()) {
				return;
			}

			_dxpCloudStatusPageSubscriptionUtil.unsubscribe(
				offeringEntry.getAccountEntry());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onAfterUpdate(OfferingEntry offeringEntry)
		throws ModelListenerException {

		try {
			OfferingEntry oldOfferingEntry = _oldOfferingEntry.get();

			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (!productEntry.isDXPCloud()) {
				return;
			}

			if (oldOfferingEntry.getStatus() != offeringEntry.getStatus()) {
				if (offeringEntry.getStatus() ==
						OfferingEntryConstants.STATUS_ACTIVE) {

					_dxpCloudStatusPageSubscriptionUtil.subscribe(
						offeringEntry.getAccountEntry());
				}
				else {
					_dxpCloudStatusPageSubscriptionUtil.unsubscribe(
						offeringEntry.getAccountEntry());
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onBeforeUpdate(OfferingEntry offeringEntry)
		throws ModelListenerException {

		try {
			OfferingEntry oldOfferingEntry =
				OfferingEntryLocalServiceUtil.getOfferingEntry(
					offeringEntry.getOfferingEntryId());

			_oldOfferingEntry.set(oldOfferingEntry);
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
		OfferingEntryModelListener.class);

	private static final ThreadLocal<OfferingEntry> _oldOfferingEntry =
		new CentralizedThreadLocal<>(
			OfferingEntryModelListener.class + "._oldOfferingEntry");

	@Reference
	private DXPCloudStatusPageSubscriptionUtil
		_dxpCloudStatusPageSubscriptionUtil;

}