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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ZendeskCategory {

	public ZendeskCategory() {
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getDescription() {
		return _description;
	}

	public String getName() {
		return _name;
	}

	public long getZendeskCategoryId() {
		return _zendeskCategoryId;
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

	public void setName(String name) {
		_name = name;
	}

	public void setZendeskCategoryId(long zendeskCategoryId) {
		_zendeskCategoryId = zendeskCategoryId;
	}

	public void setZendeskTranslations(
		List<ZendeskTranslation> zendeskTranslations) {

		_zendeskTranslations = zendeskTranslations;
	}

	private Date _createDate;
	private String _description;
	private String _name;
	private long _zendeskCategoryId;
	private List<ZendeskTranslation> _zendeskTranslations = new ArrayList<>();

}