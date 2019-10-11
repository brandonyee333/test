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

package com.liferay.osb.customer.metrics.impl.util;

import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;

import java.lang.reflect.Field;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsBaseModelUtil.class)
public class MetricsBaseModelUtil {

	public <T> List<BaseModel<?>> getModelList(MetricsModel metricsModel)
		throws Exception {

		Class<?> clazz = metricsModel.getModelClass();

		String localServiceUtilClassName = _getLocalServiceUtilClassName(
			clazz.getName());

		Class<?> localServiceUtilClass = Class.forName(
			localServiceUtilClassName, false, clazz.getClassLoader());

		String methodName =
			"get" + TextFormatter.formatPlural(metricsModel.getModelName());

		MethodKey methodKey = new MethodKey(
			localServiceUtilClass, methodName,
			new Class<?>[] {int.class, int.class});

		MethodHandler updateMethodHandler = new MethodHandler(
			methodKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return (List<BaseModel<?>>)updateMethodHandler.invoke();
	}

	public String getModelPrimaryKeyName(MetricsModel metricsModel)
		throws Exception {

		Class<?> modelImplClass = getModelImplClass(metricsModel);

		Field field = modelImplClass.getField("TABLE_SQL_CREATE");

		Object createTable = (Object)field.get(null);

		String createTableSQL = (String)createTable;

		int x = createTableSQL.indexOf("not null primary key");

		if (x == -1) {
			x = createTableSQL.indexOf("primary key");

			int y = createTableSQL.indexOf(StringPool.OPEN_PARENTHESIS, x);

			int z = createTableSQL.indexOf(StringPool.CLOSE_PARENTHESIS, y);

			return createTableSQL.substring(y, z + 1);
		}

		int y = createTableSQL.lastIndexOf(StringPool.COMMA, x);

		if (y == -1) {
			y = createTableSQL.lastIndexOf(StringPool.OPEN_PARENTHESIS, x);
		}

		int z = createTableSQL.indexOf(StringPool.SPACE, y);

		return createTableSQL.substring(y + 1, z);
	}

	protected Class getModelImplClass(MetricsModel metricsModel)
		throws Exception {

		Class<?> clazz = metricsModel.getModelClass();

		String localServiceUtilClassName = _getLocalServiceUtilClassName(
			clazz.getName());

		Class<?> localServiceUtilClass = Class.forName(
			localServiceUtilClassName, false, clazz.getClassLoader());

		String methodName = "create" + metricsModel.getModelName();

		MethodKey methodKey = new MethodKey(
			localServiceUtilClass, methodName, new Class<?>[] {long.class});

		MethodHandler updateMethodHandler = new MethodHandler(methodKey, 0);

		BaseModel<?> baseModel = (BaseModel<?>)updateMethodHandler.invoke();

		return baseModel.getClass();
	}

	private String _getLocalServiceUtilClassName(String modelClassName) {
		int pos = modelClassName.lastIndexOf(CharPool.PERIOD);

		String modelName = modelClassName.substring(pos + 1);

		pos = modelClassName.lastIndexOf(".model.");

		StringBundler sb = new StringBundler(4);

		sb.append(modelClassName.substring(0, pos));
		sb.append(".service.");
		sb.append(modelName);
		sb.append("LocalServiceUtil");

		return sb.toString();
	}

}