/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service.impl;

import com.liferay.mobile.device.rules.model.MDRRule;
import com.liferay.mobile.device.rules.service.base.MDRRuleServiceBaseImpl;
import com.liferay.mobile.device.rules.service.permission.MDRRuleGroupPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Locale;
import java.util.Map;

/**
 * @author Edward C. Han
 */
public class MDRRuleServiceImpl extends MDRRuleServiceBaseImpl {

	@Override
	public MDRRule addRule(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException {

		MDRRuleGroupPermission.check(
			getPermissionChecker(), ruleGroupId, ActionKeys.UPDATE);

		return mdrRuleLocalService.addRule(
			ruleGroupId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public MDRRule addRule(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettings, ServiceContext serviceContext)
		throws PortalException {

		MDRRuleGroupPermission.check(
			getPermissionChecker(), ruleGroupId, ActionKeys.UPDATE);

		return mdrRuleLocalService.addRule(
			ruleGroupId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public void deleteRule(long ruleId) throws PortalException {
		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermission.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.UPDATE);

		mdrRuleLocalService.deleteRule(rule);
	}

	@Override
	public MDRRule fetchRule(long ruleId) throws PortalException {
		MDRRule rule = mdrRuleLocalService.fetchRule(ruleId);

		if (rule != null) {
			MDRRuleGroupPermission.check(
				getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.VIEW);
		}

		return rule;
	}

	@Override
	public MDRRule getRule(long ruleId) throws PortalException {
		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermission.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.VIEW);

		return rule;
	}

	@Override
	public MDRRule updateRule(
			long ruleId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermission.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.UPDATE);

		return mdrRuleLocalService.updateRule(
			ruleId, nameMap, descriptionMap, type, typeSettings,
			serviceContext);
	}

	@Override
	public MDRRule updateRule(
			long ruleId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		MDRRuleGroupPermission.check(
			getPermissionChecker(), rule.getRuleGroupId(), ActionKeys.UPDATE);

		return mdrRuleLocalService.updateRule(
			ruleId, nameMap, descriptionMap, type, typeSettingsProperties,
			serviceContext);
	}

}