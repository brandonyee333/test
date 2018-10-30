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

package com.liferay.osb.customer.zendesk.web.service.internal.util;

import com.liferay.osb.customer.zendesk.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;
import com.liferay.osb.customer.zendesk.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.model.ZendeskTicketComment;
import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import java.time.Instant;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskConverter.class)
public class ZendeskConverter {

	public ZendeskArticle toZendeskArticle(JSONObject jsonObject)
		throws PortalException {

		ZendeskArticle zendeskArticle = new ZendeskArticle();

		zendeskArticle.setBody(jsonObject.getString("body"));

		String createdAt = jsonObject.getString("created_at");

		try {
			Instant instant = Instant.parse(createdAt);

			zendeskArticle.setCreateDate(new Date(instant.toEpochMilli()));
		}
		catch (DateTimeParseException dtpe) {
			throw new PortalException(dtpe);
		}

		zendeskArticle.setDraft(jsonObject.getBoolean("draft"));

		JSONArray labelNamesJSONArray = jsonObject.getJSONArray("label_names");

		if (labelNamesJSONArray != null) {
			Set<String> labelNames = new HashSet<>();

			for (int i = 0; i < labelNamesJSONArray.length(); i++) {
				labelNames.add(labelNamesJSONArray.getString(i));
			}

			zendeskArticle.setLabelNames(labelNames);
		}

		zendeskArticle.setTitle(jsonObject.getString("title"));

		String updatedAt = jsonObject.getString("updated_at");

		try {
			Instant instant = Instant.parse(updatedAt);

			zendeskArticle.setUpdateDate(new Date(instant.toEpochMilli()));
		}
		catch (DateTimeParseException dtpe) {
			throw new PortalException(dtpe);
		}

		zendeskArticle.setZendeskArticleId(jsonObject.getLong("id"));

		JSONArray translationsJSONArray = jsonObject.getJSONArray(
			"translations");

		if (translationsJSONArray != null) {
			List<ZendeskTranslation> zendeskTranslations =
				toZendeskTranslations(translationsJSONArray);

			zendeskArticle.setZendeskTranslations(zendeskTranslations);
		}

		return zendeskArticle;
	}

	public List<ZendeskArticle> toZendeskArticles(JSONArray jsonArray)
		throws PortalException {

		List<ZendeskArticle> zendeskArticles = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			zendeskArticles.add(toZendeskArticle(jsonObject));
		}

		return zendeskArticles;
	}

	public List<ZendeskCategory> toZendeskCategories(JSONArray jsonArray)
		throws PortalException {

		List<ZendeskCategory> zendeskCategories = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			zendeskCategories.add(toZendeskCategory(jsonObject));
		}

		return zendeskCategories;
	}

	public ZendeskCategory toZendeskCategory(JSONObject jsonObject)
		throws PortalException {

		ZendeskCategory zendeskCategory = new ZendeskCategory();

		String createdAt = jsonObject.getString("created_at");

		try {
			Instant instant = Instant.parse(createdAt);

			zendeskCategory.setCreateDate(new Date(instant.toEpochMilli()));
		}
		catch (DateTimeParseException dtpe) {
			throw new PortalException(dtpe);
		}

		zendeskCategory.setDescription(jsonObject.getString("description"));
		zendeskCategory.setName(jsonObject.getString("name"));
		zendeskCategory.setZendeskCategoryId(jsonObject.getLong("id"));

		JSONArray jsonArray = jsonObject.getJSONArray("translations");

		if (jsonArray != null) {
			List<ZendeskTranslation> zendeskTranslations =
				toZendeskTranslations(jsonArray);

			zendeskCategory.setZendeskTranslations(zendeskTranslations);
		}

		return zendeskCategory;
	}

	public ZendeskOrganization toZendeskOrganization(JSONObject jsonObject) {
		ZendeskOrganization zendeskOrganization = new ZendeskOrganization();

		zendeskOrganization.setZendeskOrganizationId(jsonObject.getLong("id"));
		zendeskOrganization.setName(jsonObject.getString("name"));

		return zendeskOrganization;
	}

	public ZendeskSection toZendeskSection(JSONObject jsonObject)
		throws PortalException {

		ZendeskSection zendeskSection = new ZendeskSection();

		String createdAt = jsonObject.getString("created_at");

		try {
			Instant instant = Instant.parse(createdAt);

			zendeskSection.setCreateDate(new Date(instant.toEpochMilli()));
		}
		catch (DateTimeParseException dtpe) {
			throw new PortalException(dtpe);
		}

		zendeskSection.setDescription(jsonObject.getString("description"));
		zendeskSection.setName(jsonObject.getString("name"));
		zendeskSection.setZendeskSectionId(jsonObject.getLong("id"));

		JSONArray jsonArray = jsonObject.getJSONArray("translations");

		if (jsonArray != null) {
			List<ZendeskTranslation> zendeskTranslations =
				toZendeskTranslations(jsonArray);

			zendeskSection.setZendeskTranslations(zendeskTranslations);
		}

		return zendeskSection;
	}

	public List<ZendeskSection> toZendeskSections(JSONArray jsonArray)
		throws PortalException {

		List<ZendeskSection> zendeskSections = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			zendeskSections.add(toZendeskSection(jsonObject));
		}

		return zendeskSections;
	}

	public ZendeskTicket toZendeskTicket(JSONObject jsonObject) {
		ZendeskTicket zendeskTicket = new ZendeskTicket();

		zendeskTicket.setDescription(jsonObject.getString("description"));
		zendeskTicket.setZendeskTicketId(jsonObject.getLong("id"));
		zendeskTicket.setZendeskOrganizationId(
			jsonObject.getLong("organization_id"));
		zendeskTicket.setSubject(jsonObject.getString("subject"));

		return zendeskTicket;
	}

	public ZendeskTicketComment toZendeskTicketComment(JSONObject jsonObject) {
		ZendeskTicketComment zendeskTicketComment = new ZendeskTicketComment();

		zendeskTicketComment.setZendeskTicketCommentId(
			jsonObject.getLong("id"));

		return zendeskTicketComment;
	}

	public ZendeskTranslation toZendeskTranslation(JSONObject jsonObject) {
		ZendeskTranslation zendeskTranslation = new ZendeskTranslation();

		zendeskTranslation.setBody(jsonObject.getString("body"));
		zendeskTranslation.setLocale(jsonObject.getString("locale"));
		zendeskTranslation.setSourceId(jsonObject.getLong("source_id"));
		zendeskTranslation.setSourceType(jsonObject.getString("source_type"));
		zendeskTranslation.setTitle(jsonObject.getString("title"));
		zendeskTranslation.setZendeskTranslationId(jsonObject.getLong("id"));

		return zendeskTranslation;
	}

	public List<ZendeskTranslation> toZendeskTranslations(JSONArray jsonArray) {
		List<ZendeskTranslation> zendeskTranslations = new ArrayList<>(
			jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			zendeskTranslations.add(toZendeskTranslation(jsonObject));
		}

		return zendeskTranslations;
	}

	public ZendeskUser toZendeskUser(JSONObject jsonObject) {
		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmail(jsonObject.getString("email"));
		zendeskUser.setExternalId(jsonObject.getString("externalId"));
		zendeskUser.setLocale(jsonObject.getString("locale"));
		zendeskUser.setName(jsonObject.getString("name"));
		zendeskUser.setZendeskUserId(jsonObject.getLong("id"));

		return zendeskUser;
	}

}