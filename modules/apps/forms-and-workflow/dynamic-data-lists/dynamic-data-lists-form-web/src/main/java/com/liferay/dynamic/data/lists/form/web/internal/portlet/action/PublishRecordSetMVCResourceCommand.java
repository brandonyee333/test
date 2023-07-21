/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.form.web.internal.portlet.action;

import com.liferay.dynamic.data.lists.constants.DDLActionKeys;
import com.liferay.dynamic.data.lists.form.web.internal.constants.DDLFormPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetService;
import com.liferay.dynamic.data.mapping.form.values.query.DDMFormValuesQuery;
import com.liferay.dynamic.data.mapping.form.values.query.DDMFormValuesQueryFactory;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DDLFormPortletKeys.DYNAMIC_DATA_LISTS_FORM_ADMIN,
		"mvc.command.name=publishRecordSet"
	},
	service = MVCResourceCommand.class
)
public class PublishRecordSetMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long recordSetId = ParamUtil.getLong(resourceRequest, "recordSetId");

		boolean published = ParamUtil.getBoolean(resourceRequest, "published");

		DDLRecordSet recordSet = _ddlRecordSetService.getRecordSet(recordSetId);

		DDMFormValues settingsDDMFormValues =
			recordSet.getSettingsDDMFormValues();

		updatePublishedDDMFormFieldValue(settingsDDMFormValues, published);

		_ddlRecordSetService.updateRecordSet(
			recordSetId, settingsDDMFormValues);

		updateRecordSetPermission(published, recordSetId, resourceRequest);
	}

	protected DDMForm getDDMForm(DDLRecordSet recordSet)
		throws PortalException {

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		return ddmStructure.getDDMForm();
	}

	@Reference(unbind = "-")
	protected void setDDLRecordSetService(
		DDLRecordSetService ddlRecordSetService) {

		_ddlRecordSetService = ddlRecordSetService;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesQueryFactory(
		DDMFormValuesQueryFactory ddmFormValuesQueryFactory) {

		_ddmFormValuesQueryFactory = ddmFormValuesQueryFactory;
	}

	@Reference(unbind = "-")
	protected void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	@Reference(unbind = "-")
	protected void setRoleService(RoleService roleService) {
		_roleService = roleService;
	}

	protected void updatePublishedDDMFormFieldValue(
			DDMFormValues ddmFormValues, boolean published)
		throws PortalException {

		DDMFormValuesQuery ddmFormValuesQuery =
			_ddmFormValuesQueryFactory.create(ddmFormValues, "/published");

		DDMFormFieldValue ddmFormFieldValue =
			ddmFormValuesQuery.selectSingleDDMFormFieldValue();

		Value value = ddmFormFieldValue.getValue();

		value.addString(
			ddmFormValues.getDefaultLocale(), Boolean.toString(published));
	}

	protected void updateRecordSetPermission(
			boolean published, long recordSetId,
			ResourceRequest resourceRequest)
		throws PortalException {

		Role role = _roleService.getRole(
			_portal.getCompanyId(resourceRequest), RoleConstants.GUEST);

		ResourcePermission resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				role.getCompanyId(), DDLRecordSet.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(recordSetId),
				role.getRoleId());

		if (resourcePermission == null) {
			_resourcePermissionLocalService.setResourcePermissions(
				role.getCompanyId(), DDLRecordSet.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(recordSetId),
				role.getRoleId(), new String[] {DDLActionKeys.ADD_RECORD});

			resourcePermission =
				_resourcePermissionLocalService.fetchResourcePermission(
					role.getCompanyId(), DDLRecordSet.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(recordSetId), role.getRoleId());
		}

		if (published) {
			resourcePermission.addResourceAction(DDLActionKeys.ADD_RECORD);
		}
		else {
			resourcePermission.removeResourceAction(DDLActionKeys.ADD_RECORD);
		}

		_resourcePermissionLocalService.updateResourcePermission(
			resourcePermission);
	}

	private DDLRecordSetService _ddlRecordSetService;
	private DDMFormValuesQueryFactory _ddmFormValuesQueryFactory;

	@Reference
	private Portal _portal;

	private ResourcePermissionLocalService _resourcePermissionLocalService;
	private RoleService _roleService;

}