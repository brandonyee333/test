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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WatsonReport service. Represents a row in the &quot;WatsonReport&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonReportModel
 * @see com.liferay.watson.model.impl.WatsonReportImpl
 * @see com.liferay.watson.model.impl.WatsonReportModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonReportImpl")
@ProviderType
public interface WatsonReport extends WatsonReportModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonReportImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonReport, Long> WATSON_REPORT_ID_ACCESSOR = new Accessor<WatsonReport, Long>() {
			@Override
			public Long get(WatsonReport watsonReport) {
				return watsonReport.getWatsonReportId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonReport> getTypeClass() {
				return WatsonReport.class;
			}
		};
}