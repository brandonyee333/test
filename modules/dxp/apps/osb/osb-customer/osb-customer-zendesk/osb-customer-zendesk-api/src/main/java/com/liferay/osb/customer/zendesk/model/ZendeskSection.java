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

package com.liferay.osb.customer.zendesk.model;

import com.liferay.osb.customer.zendesk.constants.ZendeskTranslationConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ZendeskSection implements TranslatableModel {

	public ZendeskSection() {
	}

	public String getBody() {
		return _description;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getDescription() {
		return _description;
	}

	public String getHtmlUrl() {
		return _htmlUrl;
	}

	public String getName() {
		return _name;
	}

	public long getSourceId() {
		return _zendeskSectionId;
	}

	public String getSourceLocale() {
		return _sourceLocale;
	}

	public String getSourceType() {
		return ZendeskTranslationConstants.SOURCE_TYPE_SECTION;
	}

	public String getTitle() {
		return _name;
	}

	public long getZendeskSectionId() {
		return _zendeskSectionId;
	}

	public List<ZendeskTranslation> getZendeskTranslations() {
		return _zendeskTranslations;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setHtmlUrl(String htmlUrl) {
		_htmlUrl = htmlUrl;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setSourceLocale(String sourceLocale) {
		_sourceLocale = sourceLocale;
	}

	public void setZendeskSectionId(long zendeskSectionId) {
		_zendeskSectionId = zendeskSectionId;
	}

	public void setZendeskTranslations(
		List<ZendeskTranslation> zendeskTranslations) {

		_zendeskTranslations = zendeskTranslations;
	}

	private Date _createDate;
	private String _description;
	private String _htmlUrl;
	private String _name;
	private String _sourceLocale;
	private long _zendeskSectionId;
	private List<ZendeskTranslation> _zendeskTranslations = new ArrayList<>();

}