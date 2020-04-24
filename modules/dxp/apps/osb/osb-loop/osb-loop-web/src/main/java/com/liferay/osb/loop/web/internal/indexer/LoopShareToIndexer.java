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
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.constants.LoopIndexerConstants;
import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopAuditEntryLocalServiceUtil;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Timothy Bell
 */
public class LoopShareToIndexer extends BaseIndexer<BaseModel<?>> {

	public static LoopShareToIndexer getInstance() {
		return _instance;
	}

	@Override
	public String getClassName() {
		return LoopIndexerConstants.SHARE_TO_INDEXER;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		super.postProcessContextBooleanFilter(
			contextBooleanFilter, searchContext);

		long classNameId = GetterUtil.getInteger(
			searchContext.getAttribute(Field.CLASS_NAME_ID));

		if (classNameId > 0) {
			contextBooleanFilter.addRequiredTerm(
				Field.CLASS_NAME_ID, classNameId);
		}

		boolean includeAuditEntries = GetterUtil.getBoolean(
			searchContext.getAttribute("includeAuditEntries"));

		if (!includeAuditEntries) {
			BooleanFilter booleanFilter = new BooleanFilter();

			booleanFilter.addRequiredTerm(
				Field.TYPE, LoopConstants.TYPE_LOOP_AUDIT_ENTRY);

			contextBooleanFilter.add(
				booleanFilter, BooleanClauseOccur.MUST_NOT);
		}

		boolean includeInactivePersons = GetterUtil.getBoolean(
			searchContext.getAttribute("includeInactivePersons"));

		if (!includeInactivePersons) {
			contextBooleanFilter.addRequiredTerm(
				Field.STATUS, WorkflowConstants.STATUS_APPROVED);
		}

		int queryType = GetterUtil.getInteger(
			searchContext.getAttribute("queryType"));

		if (queryType > 0) {
			contextBooleanFilter.addRequiredTerm("queryType", queryType);
		}

		long modifiedTime = GetterUtil.getLong(
			searchContext.getAttribute("modifiedTime"));

		if (modifiedTime > 0) {
			BooleanFilter booleanFilter = new BooleanFilter();

			booleanFilter.addRangeTerm(
				"modifiedTime_sortable", modifiedTime,
				System.currentTimeMillis());

			contextBooleanFilter.add(booleanFilter, BooleanClauseOccur.MUST);
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

		addSearchTerm(searchQuery, searchContext, Field.NAME, true);
		addSearchTerm(searchQuery, searchContext, "preferredName", true);
		addSearchTerm(searchQuery, searchContext, "topicName_sortable", true);
	}

	@Override
	protected void doDelete(BaseModel<?> baseModel) throws Exception {
		Document document = new DocumentImpl();

		document.addUID(
			LoopIndexerConstants.SHARE_TO_INDEXER,
			String.valueOf(baseModel.getPrimaryKeyObj()));

		AuditedModel auditedModel = (AuditedModel)baseModel;

		IndexWriterHelperUtil.deleteDocument(
			getSearchEngineId(), auditedModel.getCompanyId(),
			document.get(Field.UID), isCommitImmediately());
	}

	@Override
	protected Document doGetDocument(BaseModel<?> baseModel) throws Exception {
		Document document = new DocumentImpl();

		document.addUID(
			LoopIndexerConstants.SHARE_TO_INDEXER,
			String.valueOf(baseModel.getPrimaryKeyObj()));

		long classNameId = PortalUtil.getClassNameId(
			baseModel.getModelClassName());

		document.addKeyword(Field.CLASS_NAME_ID, classNameId);

		long classPK = GetterUtil.getLong(baseModel.getPrimaryKeyObj());

		document.addKeyword(Field.CLASS_PK, classPK);

		document.addKeyword(
			Field.ENTRY_CLASS_NAME, LoopIndexerConstants.SHARE_TO_INDEXER);

		int classNamePriority = 0;
		String name = StringPool.BLANK;
		String nameSortable = "name_sortable";
		long priority = 0;
		int queryType = 0;
		int status = WorkflowConstants.STATUS_APPROVED;
		int[] types = null;
		int userStatusPriority = 0;

		if (baseModel instanceof LoopAuditEntry) {
			LoopAuditEntry loopAuditEntry = (LoopAuditEntry)baseModel;

			name = loopAuditEntry.getName();

			types = new int[] {LoopConstants.TYPE_LOOP_AUDIT_ENTRY};
		}
		else if (baseModel instanceof LoopDivision) {
			LoopDivision loopDivision = (LoopDivision)baseModel;

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				loopDivision.getExtraData());

			document.addText(
				"preferredName", jsonObject.getString("preferredName"));

			classNamePriority = 2;

			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					loopDivision.getOrganizationId());

			name = organization.getName();

			queryType = LoopConstants.QUERY_TYPE_AT;
			types = new int[] {
				loopDivision.getType(), LoopConstants.TYPE_LOOP_DIVISION
			};
		}
		else if (baseModel instanceof LoopPerson) {
			LoopPerson loopPerson = (LoopPerson)baseModel;

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				loopPerson.getExtraData());

			document.addText(
				"preferredName", jsonObject.getString("preferredName"));

			classNamePriority = 1;

			User user = UserLocalServiceUtil.getUser(
				loopPerson.getPersonUserId());

			name = user.getFullName();

			queryType = LoopConstants.QUERY_TYPE_AT;
			status = user.getStatus();

			if (user.getStatus() == WorkflowConstants.STATUS_INACTIVE) {
				userStatusPriority = 1;
			}

			types = new int[] {LoopConstants.TYPE_LOOP_PERSON};
		}
		else if (baseModel instanceof LoopTopic) {
			classNamePriority = 3;

			LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
				classPK);

			name = loopTopic.getName();

			nameSortable = "topicName_sortable";

			queryType = LoopConstants.QUERY_TYPE_HASHTAG;
			types = new int[] {LoopConstants.TYPE_LOOP_TOPIC};
		}

		document.addText(Field.NAME, name);
		document.addKeyword(nameSortable, name, true);
		document.addNumber(Field.PRIORITY, priority);
		document.addKeyword(Field.STATUS, status);
		document.addKeyword(Field.TYPE, types);
		document.addNumber("classNamePriority", classNamePriority);
		document.addKeyword("queryType", queryType);
		document.addNumber("userStatusPriority", userStatusPriority);

		if (baseModel instanceof AuditedModel) {
			AuditedModel auditedModel = (AuditedModel)baseModel;

			Date date = auditedModel.getModifiedDate();

			document.addNumber("modifiedTime", date.getTime());
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return createSummary(document);
	}

	@Override
	protected void doReindex(BaseModel<?> baseModel) throws Exception {
		Document document = getDocument(baseModel);

		AuditedModel auditedModel = (AuditedModel)baseModel;

		IndexWriterHelperUtil.updateDocument(
			getSearchEngineId(), auditedModel.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		BaseModel<?> baseModel = null;

		if (className.equals(LoopAuditEntry.class.getName())) {
			baseModel = LoopAuditEntryLocalServiceUtil.getLoopAuditEntry(
				classPK);
		}
		else if (className.equals(LoopDivision.class.getName())) {
			baseModel = LoopDivisionLocalServiceUtil.getLoopDivision(classPK);
		}
		else if (className.equals(LoopPerson.class.getName())) {
			baseModel = LoopPersonLocalServiceUtil.getLoopPerson(classPK);
		}
		else if (className.equals(LoopTopic.class.getName())) {
			baseModel = LoopTopicLocalServiceUtil.getLoopTopic(classPK);
		}

		doReindex(baseModel);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexModels(companyId);
	}

	protected void reindexModels(long companyId) throws Exception {
		for (String className : _CLASS_NAMES) {
			reindexModels(companyId, new AlloyServiceInvoker(className));
		}
	}

	protected void reindexModels(
			long companyId, AlloyServiceInvoker alloyServiceInvoker)
		throws Exception {

		long count = alloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {"companyId", companyId});

		int pages = Math.toIntExact(count) / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = i * Indexer.DEFAULT_INTERVAL;

			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexModels(companyId, alloyServiceInvoker, start, end);
		}
	}

	protected void reindexModels(
			long companyId, AlloyServiceInvoker alloyServiceInvoker, int start,
			int end)
		throws Exception {

		List<BaseModel<?>> baseModels = alloyServiceInvoker.executeDynamicQuery(
			new Object[] {"companyId", companyId}, start, end);

		if (baseModels.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<>(baseModels.size());

		for (BaseModel<?> baseModel : baseModels) {
			Document document = getDocument(baseModel);

			documents.add(document);
		}

		IndexWriterHelperUtil.updateDocuments(
			getSearchEngineId(), companyId, documents, isCommitImmediately());
	}

	private static final String[] _CLASS_NAMES = {
		LoopAuditEntry.class.getName(), LoopDivision.class.getName(),
		LoopPerson.class.getName(), LoopTopic.class.getName()
	};

	private static final LoopShareToIndexer _instance =
		new LoopShareToIndexer();

}