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

package com.liferay.portal.workflow.kaleo.designer.internal.activator;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(immediate = true, service = KaleoDesignerServiceActivator.class)
public class KaleoDesignerServiceActivator {

	@Activate
	protected void activate() throws Exception {
		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			ServiceContext serviceContext = _createServiceContext(company);

			_addMissingWorkflowDraftDefinitions(company, serviceContext);
		}
	}

	private KaleoDraftDefinition _addMissingKaleoDraftDefinition(
			String name, int version, String title, String content,
			ServiceContext serviceContext)
		throws PortalException {

		int kaleoDraftDefinitionsCount =
			_kaleoDraftDefinitionLocalService.getKaleoDraftDefinitionsCount(
				name, version, serviceContext);

		KaleoDraftDefinition kaleoDraftDefinition = null;

		if (kaleoDraftDefinitionsCount == 0) {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				title);

			kaleoDraftDefinition =
				_kaleoDraftDefinitionLocalService.addKaleoDraftDefinition(
					serviceContext.getUserId(),
					serviceContext.getScopeGroupId(), name, titleMap, content,
					version, 1, serviceContext);
		}
		else {
			kaleoDraftDefinition =
				_kaleoDraftDefinitionLocalService.getLatestKaleoDraftDefinition(
					name, version, serviceContext);
		}

		return kaleoDraftDefinition;
	}

	private void _addMissingWorkflowDraftDefinitions(
			Company company, ServiceContext serviceContext)
		throws PortalException, WorkflowException {

		List<WorkflowDefinition> workflowDefinitions =
			_workflowDefinitionManager.getWorkflowDefinitions(
				company.getCompanyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null);

		for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
			_addMissingKaleoDraftDefinition(
				workflowDefinition.getName(), workflowDefinition.getVersion(),
				workflowDefinition.getTitle(), workflowDefinition.getContent(),
				serviceContext);
		}
	}

	private ServiceContext _createServiceContext(Company company)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(company.getCompanyId());
		serviceContext.setScopeGroupId(0);

		User user = company.getDefaultUser();

		serviceContext.setUserId(user.getUserId());

		return serviceContext;
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private KaleoDraftDefinitionLocalService _kaleoDraftDefinitionLocalService;

	@Reference(target = "(proxy.bean=false)")
	private WorkflowDefinitionManager _workflowDefinitionManager;

}