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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class ZendeskArticle implements TranslatableModel {

	public ZendeskArticle() {
	}

	public String getBody() {
		return _body;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getHtmlUrl() {
		return _htmlUrl;
	}

	public Set<String> getLabelNames() {
		return _labelNames;
	}

	public long getSourceId() {
		return _zendeskArticleId;
	}

	public String getSourceLocale() {
		return _sourceLocale;
	}

	public String getSourceType() {
		return ZendeskTranslationConstants.SOURCE_TYPE_ARTICLE;
	}

	public String getTitle() {
		return _title;
	}

	public Date getUpdateDate() {
		return _updateDate;
	}

	public long getZendeskArticleId() {
		return _zendeskArticleId;
	}

	public List<ZendeskTranslation> getZendeskTranslations() {
		return _zendeskTranslations;
	}

	public boolean isDraft() {
		return _draft;
	}

	public void setBody(String body) {
		_body = body;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDraft(boolean draft) {
		_draft = draft;
	}

	public void setHtmlUrl(String htmlUrl) {
		_htmlUrl = htmlUrl;
	}

	public void setLabelNames(Set<String> labelNames) {
		_labelNames = labelNames;
	}

	public void setSourceLocale(String sourceLocale) {
		_sourceLocale = sourceLocale;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUpdateDate(Date updateDate) {
		_updateDate = updateDate;
	}

	public void setZendeskArticleId(long zendeskArticleId) {
		_zendeskArticleId = zendeskArticleId;
	}

	public void setZendeskTranslations(
		List<ZendeskTranslation> zendeskTranslations) {

		_zendeskTranslations = zendeskTranslations;
	}

	private String _body;
	private Date _createDate;
	private boolean _draft;
	private String _htmlUrl;
	private Set<String> _labelNames = new HashSet<>();
	private String _sourceLocale;
	private String _title;
	private Date _updateDate;
	private long _zendeskArticleId;
	private List<ZendeskTranslation> _zendeskTranslations = new ArrayList<>();

}