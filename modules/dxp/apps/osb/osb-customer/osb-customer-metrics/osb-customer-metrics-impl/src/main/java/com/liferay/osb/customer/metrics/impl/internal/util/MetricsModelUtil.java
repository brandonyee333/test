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

package com.liferay.osb.customer.metrics.impl.internal.util;

import com.liferay.osb.customer.metrics.api.model.MetricsModelRegistry;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ClassLoaderPool;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;

import java.lang.reflect.Field;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModelUtil.class)
public class MetricsModelUtil {

	public BaseModel<?> getBaseModel(String modelClassName) throws Exception {
		String servletContextName = getServletContextName(modelClassName);

		ClassLoader classLoader = ClassLoaderPool.getClassLoader(
			servletContextName);

		String modelImplClassName = _getModelImplClassName(modelClassName);

		_modelImplClass = Class.forName(modelImplClassName, false, classLoader);

		BaseModel<?> baseModel = (BaseModel<?>)_modelImplClass.newInstance();

		return baseModel;
	}

	public Class getModelImplClass(BaseModel model) throws Exception {
		String modelClassName = model.getModelClassName();

		String servletContextName = getServletContextName(modelClassName);

		ClassLoader classLoader = ClassLoaderPool.getClassLoader(
			servletContextName);

		String modelImplClassName = _getModelImplClassName(modelClassName);

		return Class.forName(modelImplClassName, false, classLoader);
	}

	public <T> List<BaseModel<?>> getModelList(BaseModel<?> model)
		throws Exception {

		String servletContextName = getServletContextName(
			model.getModelClassName());

		ClassLoader classLoader = ClassLoaderPool.getClassLoader(
			servletContextName);

		String localServiceUtilClassName = _getLocalServiceUtilClassName(
			model.getModelClassName());

		_localServiceUtilClass = Class.forName(
			localServiceUtilClassName, false, classLoader);

		String modelName = getModelName(model.getModelClassName());

		String methodName = "get" + TextFormatter.formatPlural(modelName);

		_methodKey = new MethodKey(
			_localServiceUtilClass, methodName,
			new Class<?>[] {int.class, int.class});

		MethodHandler updateMethodHandler = new MethodHandler(
			_methodKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return (List<BaseModel<?>>)updateMethodHandler.invoke();
	}

	public String getModelName(String modelClassName) {
		int pos = modelClassName.lastIndexOf(CharPool.PERIOD);

		String modelName = modelClassName.substring(pos + 1);

		return modelName;
	}

	public String getModelPrimaryKeyName(BaseModel model) throws Exception {
		Class<?> modelImplClass = getModelImplClass(model);

		Field field = modelImplClass.getField("TABLE_SQL_CREATE");

		Object createTable = (Object)field.get(null);

		String createTableSQL = (String)createTable;

		int x = createTableSQL.indexOf("not null primary key");

		if (x == -1) {
			x = createTableSQL.indexOf("primary key");

			int y = createTableSQL.indexOf(StringPool.OPEN_PARENTHESIS, x);

			int z = createTableSQL.indexOf(StringPool.CLOSE_PARENTHESIS, y);

			_modelPrimaryKeyName = createTableSQL.substring(y, z + 1);
		}
		else {
			int y = createTableSQL.lastIndexOf(StringPool.COMMA, x);

			if (y == -1) {
				y = createTableSQL.lastIndexOf(StringPool.OPEN_PARENTHESIS, x);
			}

			int z = createTableSQL.indexOf(StringPool.SPACE, y);

			_modelPrimaryKeyName = createTableSQL.substring(y + 1, z);
		}

		return _modelPrimaryKeyName;
	}

	public String getServletContextName(String modelClassName) {
		String servletContextName = _metricsModelRegistry.getServletContextName(
			modelClassName);

		return servletContextName;
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

	private String _getModelImplClassName(String modelClassName) {
		int pos = modelClassName.lastIndexOf(CharPool.PERIOD);

		StringBundler sb = new StringBundler(4);

		sb.append(modelClassName.substring(0, pos + 1));
		sb.append("impl.");
		sb.append(modelClassName.substring(pos + 1));
		sb.append("Impl");

		return sb.toString();
	}

	private Class<?> _localServiceUtilClass;
	private MethodKey _methodKey;

	@Reference
	private MetricsModelRegistry _metricsModelRegistry;

	private Class<?> _modelImplClass;
	private String _modelPrimaryKeyName;

}