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

package com.liferay.osb.loop.web.internal.indexer;

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.web.internal.util.LoopMarkdownUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopDivisionIndexer extends BaseLoopIndexer {

	public static LoopDivisionIndexer getInstance() {
		return _instance;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		super.postProcessContextBooleanFilter(
			contextBooleanFilter, searchContext);

		long createTimeMax = GetterUtil.getLong(
			searchContext.getAttribute("createTimeMax"));

		if (createTimeMax > 0) {
			BooleanFilter booleanFilter = new BooleanFilter();

			long createTimeMin = GetterUtil.getLong(
				searchContext.getAttribute("createTimeMin"));

			booleanFilter.addRangeTerm(
				"createDate_sortable", createTimeMin, createTimeMax);

			contextBooleanFilter.add(booleanFilter, BooleanClauseOccur.MUST);
		}

		long parentLoopDivisionId = GetterUtil.getLong(
			searchContext.getAttribute("parentLoopDivisionId"));

		if (parentLoopDivisionId > 0) {
			contextBooleanFilter.addRequiredTerm(
				"parentLoopDivisionId", parentLoopDivisionId);
		}

		int subtype = GetterUtil.getInteger(
			searchContext.getAttribute("subtype"));

		if (subtype > 0) {
			contextBooleanFilter.addRequiredTerm("subtype", subtype);
		}

		int type = GetterUtil.getInteger(
			searchContext.getAttribute(Field.TYPE));

		if (type > 0) {
			contextBooleanFilter.addRequiredTerm(Field.TYPE, type);
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.DESCRIPTION, true);
		addSearchTerm(searchQuery, searchContext, Field.NAME, true);
		addSearchTerm(
			searchQuery, searchContext, "parentLoopDivisionName", true);
		addSearchTerm(searchQuery, searchContext, "preferredName", true);
	}

	@Override
	protected Document doGetDocument(BaseModel<?> baseModel) throws Exception {
		LoopDivision loopDivision = (LoopDivision)baseModel;

		Document document = getBaseModelDocument(null, loopDivision);

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(
				loopDivision.getOrganizationId());

		String description = LoopMarkdownUtil.markdownToHtml(
			loopDivision.getCompanyId(), organization.getComments(), false,
			false);

		document.addText(Field.DESCRIPTION, HtmlUtil.extractText(description));

		Organization divisionOrganization =
			OrganizationLocalServiceUtil.getOrganization(
				loopDivision.getOrganizationId());

		String name = divisionOrganization.getName();

		document.addText(Field.NAME, name);
		document.addKeyword("name_sortable", name);

		document.addKeyword(Field.TYPE, loopDivision.getType());

		AlloyServiceInvoker loopDivisionAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopDivision.class.getName());

		List<LoopDivision> parentLoopDivisions =
			loopDivisionAlloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"companyId", loopDivision.getCompanyId(), "organizationId",
					divisionOrganization.getParentOrganizationId()
				});

		if (!parentLoopDivisions.isEmpty()) {
			LoopDivision parentLoopDivision = parentLoopDivisions.get(0);

			document.addKeyword(
				"parentLoopDivisionId", parentLoopDivision.getLoopDivisionId());

			Organization parentOrganization =
				OrganizationLocalServiceUtil.getOrganization(
					divisionOrganization.getParentOrganizationId());

			document.addText(
				"parentLoopDivisionName", parentOrganization.getName());
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			loopDivision.getExtraData());

		document.addText(
			"preferredName", jsonObject.getString("preferredName"));

		document.addKeyword("subtype", loopDivision.getSubtype());

		return document;
	}

	private LoopDivisionIndexer() {
		setClassName(LoopDivision.class.getName());
	}

	private static LoopDivisionIndexer _instance = new LoopDivisionIndexer();

}