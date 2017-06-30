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

package com.liferay.osb.hook.service.impl;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.util.DDMStructureConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.StructureXsdException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureLink;
import com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLinkLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceWrapper;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Calvin Keum
 */
public class OSBDDMStructureLocalServiceImpl
	extends DDMStructureLocalServiceWrapper {

	public OSBDDMStructureLocalServiceImpl(
		DDMStructureLocalService ddmStructureLocalService) {

		super(ddmStructureLocalService);
	}

	@Override
	public DDMStructure updateStructure(
			long structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsd,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMStructure ddmStructure = getStructure(structureId);

		if (ddmStructure.getClassNameId() == PortalUtil.getClassNameId(
				TrainingEvent.class)) {

			List<DDMStorageLink> ddmStorageLinks =
				DDMStorageLinkLocalServiceUtil.getStructureStorageLinks(
					structureId);

			if (ddmStorageLinks.isEmpty()) {
				return super.updateStructure(
					structureId, nameMap, descriptionMap, xsd, serviceContext);
			}

			try {
				xsd = DDMXMLUtil.formatXML(xsd);
			}
			catch (Exception e) {
				throw new StructureXsdException();
			}

			if (StringUtil.equalsIgnoreCase(xsd, ddmStructure.getXsd())) {
				return super.updateStructure(
					structureId, nameMap, descriptionMap, xsd, serviceContext);
			}

			ddmStructure.setType(DDMStructureConstants.TYPE_ARCHIVED);

			super.updateDDMStructure(ddmStructure, false);

			DDMStructure newDDMStructure = super.addStructure(
				serviceContext.getUserId(), ddmStructure.getGroupId(),
				ddmStructure.getClassNameId(), null, nameMap, descriptionMap,
				xsd, ddmStructure.getStorageType(),
				DDMStructureConstants.TYPE_DEFAULT, serviceContext);

			List<DDMStructureLink> ddmStructureLinks =
				DDMStructureLinkLocalServiceUtil.getStructureLinks(
					structureId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (DDMStructureLink ddmStructureLink : ddmStructureLinks) {
				DDLRecordSet ddlRecordSet =
					DDLRecordSetLocalServiceUtil.getRecordSet(
						ddmStructureLink.getClassPK());

				ddlRecordSet.setDDMStructureId(
					newDDMStructure.getStructureId());

				DDLRecordSetLocalServiceUtil.updateDDLRecordSet(
					ddlRecordSet, false);

				ddmStructureLink.setStructureId(
					newDDMStructure.getStructureId());

				DDMStructureLinkLocalServiceUtil.updateDDMStructureLink(
					ddmStructureLink, false);
			}

			return newDDMStructure;
		}

		return super.updateStructure(
			structureId, nameMap, descriptionMap, xsd, serviceContext);
	}

}