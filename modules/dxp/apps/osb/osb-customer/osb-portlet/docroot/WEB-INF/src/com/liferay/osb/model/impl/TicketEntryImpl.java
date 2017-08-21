/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.model.TicketInformation;
import com.liferay.osb.model.TicketInformationConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.service.TicketFlagLocalServiceUtil;
import com.liferay.osb.service.TicketInformationLocalServiceUtil;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TicketEntryImpl extends TicketEntryBaseImpl {

	public static double getWork(int weight) {
		if (weight == TicketEntryConstants.WEIGHT_NORMAL) {
			return 1;
		}

		PortletPreferences preferences = SupportUtil.getPortletPreferences();

		return GetterUtil.getDouble(
			preferences.getValue(
				TicketEntryConstants.getWeightLabel(weight), null),
			1);
	}

	public TicketEntryImpl() {
	}

	public AccountEntry getAccountEntry() throws PortalException {
		return AccountEntryLocalServiceUtil.getAccountEntry(
			getAccountEntryId());
	}

	public int getAccountTier() throws PortalException {
		AccountEntry accountEntry = getAccountEntry();

		return accountEntry.getTier();
	}

	public String getComponentIcon() {
		StringBundler sb = new StringBundler(3);

		sb.append("component_");

		String componentLabel = getComponentLabel();

		sb.append(
			componentLabel.replace(StringPool.DASH, StringPool.UNDERLINE));

		sb.append(".png");

		return sb.toString();
	}

	public String getComponentLabel() {
		return TicketEntryConstants.getComponentLabel(getComponent());
	}

	public String getDisplayId() throws PortalException {
		AccountEntry accountEntry = getAccountEntry();

		return accountEntry.getCode().concat(StringPool.DASH).concat(
			String.valueOf(getTicketId()));
	}

	@Override
	public int getEnvAS() {
		return GetterUtil.getInteger(
			getTicketInformationData(TicketInformationConstants.FIELD_ENV_AS));
	}

	public String getEnvASLabel() {
		return TicketEntryConstants.getEnvLabel(getEnvAS());
	}

	@Override
	public int getEnvBrowser() {
		return GetterUtil.getInteger(
			getTicketInformationData(
				TicketInformationConstants.FIELD_ENV_BROWSER));
	}

	public String getEnvBrowserLabel() {
		return TicketEntryConstants.getEnvLabel(getEnvBrowser());
	}

	@Override
	public int getEnvCS() {
		return GetterUtil.getInteger(
			getTicketInformationData(TicketInformationConstants.FIELD_ENV_CS));
	}

	public String getEnvCSLabel() {
		return TicketEntryConstants.getEnvLabel(getEnvCS());
	}

	@Override
	public int getEnvDB() {
		return GetterUtil.getInteger(
			getTicketInformationData(TicketInformationConstants.FIELD_ENV_DB));
	}

	public String getEnvDBLabel() {
		return TicketEntryConstants.getEnvLabel(getEnvDB());
	}

	@Override
	public int getEnvJVM() {
		return GetterUtil.getInteger(
			getTicketInformationData(TicketInformationConstants.FIELD_ENV_JVM));
	}

	public String getEnvJVMLabel() {
		return TicketEntryConstants.getEnvLabel(getEnvJVM());
	}

	@Override
	public int getEnvLFR() {
		return GetterUtil.getInteger(
			getTicketInformationData(TicketInformationConstants.FIELD_ENV_LFR));
	}

	public String getEnvLFRLabel() {
		return TicketEntryConstants.getEnvLabel(getEnvLFR());
	}

	@Override
	public String getEnvName() {
		return GetterUtil.getString(
			getTicketInformationData(
				TicketInformationConstants.FIELD_ENV_NAME));
	}

	@Override
	public int getEnvOS() {
		return GetterUtil.getInteger(
			getTicketInformationData(TicketInformationConstants.FIELD_ENV_OS));
	}

	public String getEnvOSLabel() {
		return TicketEntryConstants.getEnvLabel(getEnvOS());
	}

	@Override
	public String getEnvSearch() {
		return GetterUtil.getString(
			getTicketInformationData(
				TicketInformationConstants.FIELD_ENV_SEARCH));
	}

	public List<String> getEnvSearchLabels() {
		int[] envSearches = StringUtil.split(
			getEnvSearch(), StringPool.NEW_LINE, 0);

		List<String> envSearchLabels = new ArrayList<>();

		for (int envSearch : envSearches) {
			envSearchLabels.add(TicketEntryConstants.getEnvLabel(envSearch));
		}

		return envSearchLabels;
	}

	public String getEscalationLevelLabel() {
		return TicketEntryConstants.getEscalationLevelLabel(
			getEscalationLevel());
	}

	public String getLanguageLabel() {
		return AccountEntryConstants.getLanguageLabel(getLanguageId());
	}

	public OfferingEntry getOfferingEntry() throws PortalException {
		return OfferingEntryLocalServiceUtil.getOfferingEntry(
			getOfferingEntryId());
	}

	public OrderEntry getOrderEntry() throws PortalException {
		return OrderEntryLocalServiceUtil.getOrderEntry(getOrderEntryId());
	}

	public ProductEntry getProductEntry() throws PortalException {
		return ProductEntryLocalServiceUtil.getProductEntry(
			getProductEntryId());
	}

	public String getResolutionLabel() {
		return TicketEntryConstants.getResolutionLabel(getResolution());
	}

	public String getSeverityLabel() {
		return TicketEntryConstants.getSeverityLabel(getSeverity());
	}

	public String getStatusLabel() {
		return TicketEntryConstants.getStatusLabel(getStatus());
	}

	public String getSubcomponentLabel() {
		return TicketEntryConstants.getSubcomponentLabel(getSubcomponent());
	}

	public String getSupportPhaseLabel() {
		return ProductEntryConstants.getSupportPhaseLabel(getEnvLFR());
	}

	public SupportRegion getSupportRegion() throws PortalException {
		return SupportRegionLocalServiceUtil.getSupportRegion(
			getSupportRegionId());
	}

	public SupportResponse getSupportResponse() throws PortalException {
		return SupportResponseLocalServiceUtil.getSupportResponse(
			getSupportResponseId());
	}

	public List<TicketAttachment> getTicketAttachments() {
		return TicketAttachmentLocalServiceUtil.getTicketAttachments(
			getTicketEntryId());
	}

	public List<TicketAttachment> getTicketAttachments(
		int[] types, int[] visibilities) {

		return TicketAttachmentLocalServiceUtil.getTicketAttachments(
			getTicketEntryId(), types, visibilities,
			WorkflowConstants.STATUS_APPROVED);
	}

	public int getTicketAttachmentsCount(int[] visibilities) {
		int[] types = {
			TicketAttachmentConstants.TYPE_HOTFIX,
			TicketAttachmentConstants.TYPE_LARGE_FILE,
			TicketAttachmentConstants.TYPE_LARGE_HOTFIX,
			TicketAttachmentConstants.TYPE_NONE
		};

		return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount(
			getTicketEntryId(), types, visibilities);
	}

	public Map<Long, String> getTicketInformationFieldsMap()
		throws PortalException {

		if (_ticketInformationFieldsMap == null) {
			_ticketInformationFieldsMap =
				TicketInformationLocalServiceUtil.getFieldsMap(
					getTicketEntryId());
		}

		return _ticketInformationFieldsMap;
	}

	@JSON
	public List<TicketInformation> getTicketInformationList() {
		return TicketInformationLocalServiceUtil.getTicketInformationList(
			getTicketEntryId());
	}

	public String getWeightLabel() {
		return TicketEntryConstants.getWeightLabel(getWeight());
	}

	public double getWork() {
		return getWork(getWeight());
	}

	public boolean isPendingCustomer() {
		if (TicketFlagLocalServiceUtil.getTicketFlagsCount(
				getTicketEntryId(), TicketFlagConstants.TYPE_PENDING_CUSTOMER,
				TicketFlagConstants.FLAG_TRUE) > 0) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPendingLiferay() {
		if (TicketFlagLocalServiceUtil.getTicketFlagsCount(
				getTicketEntryId(), TicketFlagConstants.TYPE_PENDING_LIFERAY,
				TicketFlagConstants.FLAG_TRUE) > 0) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPendingPartner() {
		if (TicketFlagLocalServiceUtil.getTicketFlagsCount(
				getTicketEntryId(), TicketFlagConstants.TYPE_PENDING_PARTNER,
				TicketFlagConstants.FLAG_TRUE) > 0) {

			return true;
		}
		else {
			return false;
		}
	}

	protected String getTicketInformationData(long fieldId) {
		try {
			Map<Long, String> ticketInformationFieldsMap =
				getTicketInformationFieldsMap();

			return ticketInformationFieldsMap.get(fieldId);
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	private Map<Long, String> _ticketInformationFieldsMap;

}