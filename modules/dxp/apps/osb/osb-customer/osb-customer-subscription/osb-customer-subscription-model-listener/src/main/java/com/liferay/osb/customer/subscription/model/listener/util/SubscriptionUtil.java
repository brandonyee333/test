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

import com.liferay.osb.customer.service.DXPCloudBaseWebService;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = SubscriptionUtil.class)
public class SubscriptionUtil {

	public boolean hasActiveDXPCloud(AccountEntry accountEntry)
		throws Exception {

		List<OfferingEntry> offeringEntries = accountEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.isDXPCloud() &&
				(offeringEntry.getStatus() ==
					OfferingEntryConstants.STATUS_ACTIVE)) {

				return true;
			}
		}

		return false;
	}

	public boolean hasActiveDXPCloud(List<AccountCustomer> userAccountCustomers)
		throws Exception {

		for (AccountCustomer accountCustomer : userAccountCustomers) {
			boolean activeDXPCloudOffering = hasActiveDXPCloud(
				accountCustomer.getAccountEntry());

			if (activeDXPCloudOffering) {
				return true;
			}
		}

		return false;
	}

	public void subscribeToDXPCloud(
			AccountCustomer accountCustomer, Map<String, String> subscribers)
		throws Exception {

		if (subscribers.isEmpty()) {
			subscribers = getDXPCloudSubscribers();
		}

		User user = _userLocalService.fetchUser(accountCustomer.getUserId());

		String value = subscribers.get(user.getEmailAddress());

		if (Validator.isNull(value)) {
			_dxpCloudRESTWebService.postSubscriber(
				user.getEmailAddress(), true);
		}
	}

	public void subscribeToDXPCloud(AccountEntry accountEntry)
		throws Exception {

		Map<String, String> subscribers = getDXPCloudSubscribers();

		for (AccountCustomer accountCustomer :
				accountEntry.getAccountCustomers()) {

			subscribeToDXPCloud(accountCustomer, subscribers);
		}
	}

	public void unsubscribeToDXPCloud(
			AccountCustomer accountCustomer, Map<String, String> subscribers)
		throws Exception {

		if (subscribers.isEmpty()) {
			subscribers = getDXPCloudSubscribers();
		}

		List<AccountCustomer> userAccountCustomers =
			AccountCustomerLocalServiceUtil.getUserAccountCustomers(
				accountCustomer.getUserId());

		boolean activeDXPCloud = hasActiveDXPCloud(userAccountCustomers);

		User user = _userLocalService.fetchUser(accountCustomer.getUserId());

		String value = subscribers.get(user.getEmailAddress());

		if (!activeDXPCloud && Validator.isNotNull(value)) {
			_dxpCloudRESTWebService.deleteSubscriber(value);
		}
	}

	public void unsubscribeToDXPCloud(AccountEntry accountEntry)
		throws Exception {

		Map<String, String> subscribers = getDXPCloudSubscribers();

		List<AccountCustomer> accountCustomers =
			accountEntry.getAccountCustomers();

		for (AccountCustomer accountCustomer : accountCustomers) {
			unsubscribeToDXPCloud(accountCustomer, subscribers);
		}
	}

	protected Map<String, String> getDXPCloudSubscribers() throws Exception {
		JSONArray jsonArray = _dxpCloudRESTWebService.getSubscribers();

		Map<String, String> subscribers = new HashMap<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject subscriberJSONObject = jsonArray.getJSONObject(i);

			String emailAddress = subscriberJSONObject.getString("email");
			String id = subscriberJSONObject.getString("id");

			subscribers.put(emailAddress, id);
		}

		return subscribers;
	}

	@Reference
	private DXPCloudBaseWebService _dxpCloudRESTWebService;

	@Reference
	private UserLocalService _userLocalService;

}