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

package com.liferay.osb.asah.salesforce.extractor.util;

import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeGlobalSObjectResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.FieldType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class SchemaUtil {

	public static String[] getFieldNames(
		DescribeSObjectResult describeSObjectResult) {

		Map<String, Field> fields = getFields(describeSObjectResult);

		Set<String> fieldNames = fields.keySet();

		return fieldNames.toArray(new String[0]);
	}

	public static Map<String, Field> getFields(
		DescribeSObjectResult describeSObjectResult) {

		Map<String, Field> fields = new HashMap<>();

		for (Field field : describeSObjectResult.getFields()) {
			FieldType fieldType = field.getType();

			String fieldTypeName = fieldType.name();

			if (fieldTypeName.equals("address") ||
				fieldTypeName.equals("base64") ||
				fieldTypeName.equals("location")) {

				continue;
			}

			fields.put(field.getName(), field);
		}

		return fields;
	}

	public static String[] getTableNames(
		DescribeGlobalResult describeGlobalResult) {

		Set<String> tableNames = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

		for (DescribeGlobalSObjectResult describeGlobalSObjectResult :
				describeGlobalResult.getSobjects()) {

			if (_isIgnored(describeGlobalSObjectResult)) {
				continue;
			}

			tableNames.add(describeGlobalSObjectResult.getName());
		}

		if (_log.isInfoEnabled()) {
			_log.info("Return " + tableNames.size() + " table names");
		}

		return tableNames.toArray(new String[0]);
	}

	private static boolean _isIgnored(
		DescribeGlobalSObjectResult describeGlobalSObjectResult) {

		if (!describeGlobalSObjectResult.isQueryable() ||
			!describeGlobalSObjectResult.isReplicateable()) {

			return true;
		}

		return _ignoredTableNames.contains(
			describeGlobalSObjectResult.getName());
	}

	private static final Log _log = LogFactory.getLog(SchemaUtil.class);

	private static final Set<String> _ignoredTableNames = new HashSet<>(
		Arrays.asList(
			"ApexClass", "ApexComponent", "ApexPage", "ApexTrigger",
			"CollaborationGroupRecord", "FeedComment", "IdeaComment",
			"StaticResource", "Vote"));

}