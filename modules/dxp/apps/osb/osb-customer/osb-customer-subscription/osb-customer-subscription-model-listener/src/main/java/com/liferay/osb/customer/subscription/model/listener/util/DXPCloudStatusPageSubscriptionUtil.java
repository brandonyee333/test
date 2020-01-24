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

package com.liferay.osb.customer.subscription.model.listener.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.service.DXPCloudStatusPageWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = DXPCloudStatusPageSubscriptionUtil.class)
public class DXPCloudStatusPageSubscriptionUtil {

	public boolean hasActiveDXPCloud(AccountEntry accountEntry)
		throws Exception {

		List<OfferingEntry> offeringEntries = accountEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			if (offeringEntry.getStatus() !=
					OfferingEntryConstants.STATUS_ACTIVE) {

				continue;
			}

			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.isDXPCloud()) {
				return true;
			}
		}

		return false;
	}

	public void subscribe(AccountEntry accountEntry) throws Exception {
		Map<String, String> subscribers = _getSubscribers();

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			_subscribe(accountCustomer.getUserId(), subscribers);
		}
	}

	public void subscribe(long userId) throws Exception {
		_subscribe(userId, _getSubscribers());
	}

	public void unsubscribe(AccountEntry accountEntry) throws Exception {
		Map<String, String> subscribers = _getSubscribers();

		List<AccountCustomer> accountCustomers =
			accountEntry.getAccountCustomers();

		for (AccountCustomer accountCustomer : accountCustomers) {
			_unsubscribe(accountCustomer.getUserId(), subscribers);
		}
	}

	public void unsubscribe(long userId) throws Exception {
		_unsubscribe(userId, _getSubscribers());
	}

	private Map<String, String> _getSubscribers() throws Exception {
		JSONArray jsonArray = _dxpCloudStatusPagesWebService.getSubscribers();

		Map<String, String> subscribers = new HashMap<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject subscriberJSONObject = jsonArray.getJSONObject(i);

			String emailAddress = subscriberJSONObject.getString("email");
			String id = subscriberJSONObject.getString("id");

			subscribers.put(emailAddress, id);
		}

		return subscribers;
	}

	private void _subscribe(long userId, Map<String, String> subscribers)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		if (subscribers.containsKey(user.getEmailAddress())) {
			return;
		}

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		boolean osbDXPCloudSubscription = GetterUtil.getBoolean(
			expandoBridge.getAttribute("osbDXPCloudSubscription", false));

		if (!osbDXPCloudSubscription) {
			_dxpCloudStatusPagesWebService.postSubscriber(
				user.getEmailAddress());

			expandoBridge.setAttribute("osbDXPCloudSubscription", true, false);
		}
	}

	private void _unsubscribe(long userId, Map<String, String> subscribers)
		throws Exception {

		User user = _userLocalService.getUser(userId);

		String subscriberId = subscribers.get(user.getEmailAddress());

		if (Validator.isNull(subscriberId)) {
			return;
		}

		List<AccountCustomer> accountCustomers =
			AccountCustomerLocalServiceUtil.getUserAccountCustomers(userId);

		for (AccountCustomer accountCustomer : accountCustomers) {
			if (hasActiveDXPCloud(accountCustomer.getAccountEntry())) {
				return;
			}
		}

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		expandoBridge.setAttribute("osbDXPCloudSubscription", false, false);

		_dxpCloudStatusPagesWebService.deleteSubscriber(subscriberId);
	}

	@Reference
	private DXPCloudStatusPageWebService _dxpCloudStatusPagesWebService;

	@Reference
	private UserLocalService _userLocalService;

}