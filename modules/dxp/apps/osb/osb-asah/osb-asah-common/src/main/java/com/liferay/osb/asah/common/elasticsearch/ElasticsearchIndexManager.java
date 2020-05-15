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

package com.liferay.osb.asah.common.elasticsearch;

import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Map;

import org.json.JSONArray;

/**
 * @author Marcellus Tavares
 */
public interface ElasticsearchIndexManager {

	public void addAlias(String indexAlias, String indexName);

	public void addTemplates();

	public boolean aliasExists(String indexAlias);

	public void checkIndices();

	public void clear(String... indexAliases);

	public void clearIndices();

	public void create(
		boolean addAlias, String configurationSource, String indexName);

	public void create(String configurationSource, String indexName);

	public void delete(String... indexNames);

	public boolean exists(String indexName);

	public Map<String, String> getAliases();

	public JSONArray getCollectionsJSONArray(
		WeDeployDataService weDeployDataService);

	public String getIndexName(String indexAlias);

	public String getIndexName(
		String collectionName, WeDeployDataService weDeployDataService);

	public String getIndexNamespace(WeDeployDataService weDeployDataService);

	public String readIndexConfiguration(
		String collectionName, WeDeployDataService weDeployDataService);

	public boolean updateMapping(
		String collectionName, String mappingSource, String mappingType,
		WeDeployDataService weDeployDataService);

}