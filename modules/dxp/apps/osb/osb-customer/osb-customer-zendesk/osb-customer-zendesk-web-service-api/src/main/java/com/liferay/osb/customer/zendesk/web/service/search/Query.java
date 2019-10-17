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

package com.liferay.osb.customer.zendesk.web.service.search;

import java.util.Map;

/**
 * @author Amos Fong
 */
public interface Query {

	public void addCriterion(String criterion);

	public void addParameter(String key, String value);

	public void addSideload(String sideload);

	public int getPage();

	public Map<String, String> getParameters();

	public void setPage(int page);

	public void setSortBy(String sortBy);

	public void setSortOrder(boolean asc);

}