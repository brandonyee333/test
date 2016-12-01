/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.aspectj.hibernate.unexpected.row.count;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.SuppressAjWarnings;

import org.hibernate.StaleStateException;
import org.hibernate.jdbc.AbstractBatcher;
import org.hibernate.jdbc.BatchingBatcher;

/**
 * @author Preston Crary
 */
@Aspect
@SuppressAjWarnings("adviceDidNotMatch")
public class HibernateUnexpectedRowCountAspect {

	@AfterThrowing(
		throwing = "sse",
		value = "execution(void org.hibernate.jdbc.BatchingBatcher.checkRowCounts(int[], java.sql.PreparedStatement)) && this(batchingBatcher)"
	)
	public void logUpdateSQL(
		BatchingBatcher batchingBatcher, StaleStateException sse) {

		try {
			_log.error(
				"batchUpdateSQL = " + _batchUpdateSQLField.get(batchingBatcher),
				sse);
		}
		catch (Exception e) {
			sse.addSuppressed(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HibernateUnexpectedRowCountAspect.class);

	private static final Field _batchUpdateSQLField;

	static {
		try {
			_batchUpdateSQLField = ReflectionUtil.getDeclaredField(
				AbstractBatcher.class, "batchUpdateSQL");
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

}