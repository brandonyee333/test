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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.LCSSubscriptionEntry;
import com.liferay.osb.model.LCSSubscriptionEntryConstants;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.model.impl.LCSSubscriptionEntryImpl;
import com.liferay.osb.remote.lcs.LCSJSONWebServiceUtil;
import com.liferay.osb.service.base.LCSSubscriptionEntryLocalServiceBaseImpl;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class LCSSubscriptionEntryLocalServiceImpl
	extends LCSSubscriptionEntryLocalServiceBaseImpl {

	public List<LCSSubscriptionEntry> getLCSSubscriptionEntries(
			long accountEntryId)
		throws PortalException {

		Map<String, LCSSubscriptionEntry> lcsSubscriptionEntriesMap =
			new HashMap<>();

		List<OfferingEntry> offeringEntries =
			offeringEntryLocalService.getAccountEntryOfferingEntries(
				accountEntryId);

		for (OfferingEntry offeringEntry : offeringEntries) {
			if (offeringEntry.getStatus() !=
					OfferingEntryConstants.STATUS_ACTIVE) {

				continue;
			}

			LCSSubscriptionEntry lcsSubscriptionEntry =
				createLCSSubscriptionEntry(offeringEntry);

			if (lcsSubscriptionEntry == null) {
				continue;
			}

			String key = getKey(lcsSubscriptionEntry);

			LCSSubscriptionEntry curLCSSubscriptionEntry =
				lcsSubscriptionEntriesMap.get(key);

			if (curLCSSubscriptionEntry != null) {
				lcsSubscriptionEntry = mergeLCSSubscriptionEntries(
					lcsSubscriptionEntry, curLCSSubscriptionEntry);
			}

			lcsSubscriptionEntriesMap.put(key, lcsSubscriptionEntry);
		}

		return new ArrayList<>(lcsSubscriptionEntriesMap.values());
	}

	public void syncToLCS(long accountEntryId) throws PortalException {
		if (!PortletPropsValues.REMOTE_JSON_SERVICE_API_LCS_ENABLED) {
			return;
		}

		List<LCSSubscriptionEntry> lcsSubscriptionEntries =
			getLCSSubscriptionEntries(accountEntryId);

		String lcsSubscriptionEntriesJSON = JSONFactoryUtil.looseSerialize(
			lcsSubscriptionEntries);

		AccountEntry accountEntry = accountEntryLocalService.getAccountEntry(
			accountEntryId);

		CorpProject corpProject = corpProjectLocalService.getCorpProjectByUuid(
			accountEntry.getCorpProjectUuid());

		LCSJSONWebServiceUtil.sendLCSSubscriptionEntries(
			corpProject.getCorpProjectId(), lcsSubscriptionEntriesJSON);
	}

	protected LCSSubscriptionEntry createLCSSubscriptionEntry(
			OfferingEntry offeringEntry)
		throws PortalException {

		OrderEntry orderEntry = offeringEntry.getOrderEntry();

		if (orderEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) {
			return null;
		}

		Date startDate = orderEntry.getStartDate();

		if (startDate.getTime() > System.currentTimeMillis()) {
			return null;
		}

		ProductEntry productEntry = offeringEntry.getProductEntry();
		SupportResponse supportResponse = offeringEntry.getSupportResponse();

		if (!offeringEntry.isLicenses()) {
			return null;
		}

		String product = getProuct(productEntry, supportResponse);

		if (Validator.isNull(product)) {
			return null;
		}

		String type = getType(product);

		if (Validator.isNull(type)) {
			return null;
		}

		LCSSubscriptionEntry lcsSubscriptionEntry =
			new LCSSubscriptionEntryImpl();

		lcsSubscriptionEntry.setProduct(product);
		lcsSubscriptionEntry.setProductVersion(
			getBuildVersion(offeringEntry.getVersion()));
		lcsSubscriptionEntry.setType(type);
		lcsSubscriptionEntry.setPlatform(offeringEntry.getPlatform());
		lcsSubscriptionEntry.setPlatformVersion(
			offeringEntry.getPlatformVersion());
		lcsSubscriptionEntry.setServersAllowed(
			offeringEntry.getQuantity() - offeringEntry.getLicenseKeysCount());
		lcsSubscriptionEntry.setInstanceSize(offeringEntry.getSizing());
		lcsSubscriptionEntry.setStartDate(startDate);

		if (offeringEntry.getLicenseLifetime() ==
				OfferingDefinitionConstants.LIFETIME_INDEFINITE_VALUE) {

			lcsSubscriptionEntry.setEndDate(
				LCSSubscriptionEntryConstants.END_DATE_PERPETUAL);
		}
		else {
			Date endDate = new Date(
				startDate.getTime() + offeringEntry.getLicenseLifetime());

			lcsSubscriptionEntry.setEndDate(endDate);
		}

		lcsSubscriptionEntry.setSupportStartDate(startDate);
		lcsSubscriptionEntry.setSupportEndDate(
			offeringEntry.getSupportEndDate());

		return lcsSubscriptionEntry;
	}

	protected int getBuildVersion(int version) {
		if (version == ProductEntryConstants.PORTAL_MAJOR_VERSION_5) {
			return 5200;
		}
		else if (version == ProductEntryConstants.PORTAL_MAJOR_VERSION_6) {
			return 6200;
		}
		else if (version ==
					ProductEntryConstants.DIGITAL_ENTERPRISE_MAJOR_VERSION_7) {

			return 7000;
		}

		return 0;
	}

	protected String getKey(LCSSubscriptionEntry lcsSubscriptionEntry) {
		StringBundler sb = new StringBundler(5);

		sb.append(lcsSubscriptionEntry.getProduct());
		sb.append(StringPool.POUND);
		sb.append(lcsSubscriptionEntry.getProductVersion());
		sb.append(StringPool.POUND);

		Date endDate = lcsSubscriptionEntry.getEndDate();

		sb.append(endDate.getTime());

		return sb.toString();
	}

	protected String getProuct(
		ProductEntry productEntry, SupportResponse supportResponse) {

		String product = StringPool.BLANK;

		String productEntryName = productEntry.getName();

		if (productEntryName.contains(_DIGITAL_ENTERPRISE_BACKUP) ||
			productEntryName.contains(_PORTAL_BACKUP)) {

			product = _PORTAL_BACKUP;
		}
		else if (productEntryName.contains(_DIGITAL_ENTERPRISE_DEVELOPMENT) ||
				 productEntryName.contains(_PORTAL_DEVELOPMENT)) {

			product = _PORTAL_DEVELOPMENT;
		}
		else if (productEntryName.contains(_DIGITAL_ENTERPRISE_ENTERPRISE) ||
				 productEntryName.contains(_PORTAL_ENTERPRISE)) {

			product = _PORTAL_ENTERPRISE;
		}
		else if (productEntryName.contains(_DIGITAL_ENTERPRISE_LIMITED) ||
				 productEntryName.contains(_PORTAL_LIMITED)) {

			product = _PORTAL_LIMITED;
		}
		else if (productEntryName.contains(
					_DIGITAL_ENTERPRISE_NON_PRODUCTION) ||
				 productEntryName.contains(_PORTAL_NON_PRODUCTION)) {

			product = _PORTAL_NON_PRODUCTION;
		}
		else if (productEntryName.contains(_DIGITAL_ENTERPRISE_PRODUCTION) ||
				 productEntryName.contains(_PORTAL_PRODUCTION)) {

			product = _PORTAL_PRODUCTION;
		}
		else if (productEntryName.contains(_ELASTIC_ACTIVATION)) {
			product = _ELASTIC_ACTIVATION;
		}

		if (Validator.isNull(product)) {
			return null;
		}

		if (supportResponse.getSupportLevel() ==
				SupportResponseConstants.SUPPORT_LEVEL_GOLD) {

			product = "Gold " + product;
		}
		else if (supportResponse.getSupportLevel() ==
					SupportResponseConstants.SUPPORT_LEVEL_PLATINUM) {

			product = "Platinum " + product;
		}
		else if (supportResponse.getSupportLevel() ==
					SupportResponseConstants.SUPPORT_LEVEL_SILVER) {

			product = "Silver " + product;
		}

		return product;
	}

	protected String getType(String product) {
		if (product.contains(_DIGITAL_ENTERPRISE_BACKUP) ||
			product.contains(_PORTAL_BACKUP)) {

			return LicenseEntryConstants.TYPE_BACKUP;
		}
		else if (product.contains(_DIGITAL_ENTERPRISE_DEVELOPMENT) ||
				 product.contains(_PORTAL_DEVELOPMENT)) {

			return LicenseEntryConstants.TYPE_DEVELOPER;
		}
		else if (product.contains(_DIGITAL_ENTERPRISE_ENTERPRISE) ||
				 product.contains(_PORTAL_ENTERPRISE)) {

			return LicenseEntryConstants.TYPE_ENTERPRISE;
		}
		else if (product.contains(_DIGITAL_ENTERPRISE_LIMITED) ||
				 product.contains(_PORTAL_LIMITED)) {

			return LicenseEntryConstants.TYPE_LIMITED;
		}
		else if (product.contains(_DIGITAL_ENTERPRISE_NON_PRODUCTION) ||
				 product.contains(_PORTAL_NON_PRODUCTION)) {

			return LicenseEntryConstants.TYPE_NON_PRODUCTION;
		}
		else if (product.contains(_DIGITAL_ENTERPRISE_PRODUCTION) ||
				 product.contains(_PORTAL_PRODUCTION)) {

			return LicenseEntryConstants.TYPE_PRODUCTION;
		}
		else if (product.contains(_ELASTIC_ACTIVATION)) {
			return LicenseEntryConstants.TYPE_ELASTIC;
		}
		else {
			return StringPool.BLANK;
		}
	}

	protected LCSSubscriptionEntry mergeLCSSubscriptionEntries(
		LCSSubscriptionEntry lcsSubscriptionEntry,
		LCSSubscriptionEntry curLCSSubscriptionEntry) {

		if (lcsSubscriptionEntry.getServersAllowed() !=
				LCSSubscriptionEntryConstants.ALLOWED_SERVERS_UNLIMITED) {

			lcsSubscriptionEntry.setServersAllowed(
				lcsSubscriptionEntry.getServersAllowed() +
					curLCSSubscriptionEntry.getServersAllowed() -
						curLCSSubscriptionEntry.getServersUsed());
		}

		if (lcsSubscriptionEntry.getInstanceSize() <
				curLCSSubscriptionEntry.getInstanceSize()) {

			lcsSubscriptionEntry.setInstanceSize(
				curLCSSubscriptionEntry.getInstanceSize());
		}

		if (DateUtil.compareTo(
				lcsSubscriptionEntry.getSupportEndDate(),
				curLCSSubscriptionEntry.getSupportEndDate()) == 1) {

			lcsSubscriptionEntry.setSupportEndDate(
				curLCSSubscriptionEntry.getSupportEndDate());
		}

		String currencyCode = lcsSubscriptionEntry.getCurrencyCode();

		if (currencyCode.equals(curLCSSubscriptionEntry.getCurrencyCode())) {
			lcsSubscriptionEntry.setActualPrice(
				lcsSubscriptionEntry.getActualPrice() +
					curLCSSubscriptionEntry.getActualPrice());
		}

		return lcsSubscriptionEntry;
	}

	private static final String _DIGITAL_ENTERPRISE_BACKUP =
		"Digital Enterprise Backup";

	private static final String _DIGITAL_ENTERPRISE_DEVELOPMENT =
		"Digital Enterprise Development";

	private static final String _DIGITAL_ENTERPRISE_ENTERPRISE =
		"Digital Enterprise Unlimited Enterprise-Wide";

	private static final String _DIGITAL_ENTERPRISE_LIMITED =
		"Digital Enterprise Limited";

	private static final String _DIGITAL_ENTERPRISE_NON_PRODUCTION =
		"Digital Enterprise Non-Production";

	private static final String _DIGITAL_ENTERPRISE_PRODUCTION =
		"Digital Enterprise Production";

	private static final String _ELASTIC_ACTIVATION = "Elastic Activation";

	private static final String _PORTAL_BACKUP = "Portal Backup";

	private static final String _PORTAL_DEVELOPMENT = "Portal Development";

	private static final String _PORTAL_ENTERPRISE = "Portal Enterprise";

	private static final String _PORTAL_LIMITED = "Portal Limited";

	private static final String _PORTAL_NON_PRODUCTION =
		"Portal Non-Production";

	private static final String _PORTAL_PRODUCTION = "Portal Production";

}