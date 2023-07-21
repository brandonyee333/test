/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Raymond Augé
 */
public class PortletDataException extends PortalException {

	public static final int DEFAULT = 1;

	public static final int END_DATE_IS_MISSING_START_DATE = 1;

	public static final int FUTURE_END_DATE = 2;

	public static final int FUTURE_START_DATE = 3;

	public static final int INVALID_GROUP = 4;

	public static final int MISSING_DEPENDENCY = 5;

	public static final int START_DATE_AFTER_END_DATE = 6;

	public static final int START_DATE_IS_MISSING_END_DATE = 7;

	public static final int STATUS_IN_TRASH = 8;

	public static final int STATUS_UNAVAILABLE = 9;

	public PortletDataException() {
	}

	public PortletDataException(int type) {
		_type = type;
	}

	public PortletDataException(String msg) {
		super(msg);
	}

	public PortletDataException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PortletDataException(Throwable cause) {
		super(cause);
	}

	public StagedModel getStagedModel() {
		return _stagedModel;
	}

	public String getStagedModelClassName() {
		return _stagedModelClassName;
	}

	public String getStagedModelClassPK() {
		return _stagedModelClassPK;
	}

	public String getStagedModelDisplayName() {
		return _stagedModelDisplayName;
	}

	public int getType() {
		return _type;
	}

	public void setStagedModel(StagedModel stagedModel) {
		_stagedModel = stagedModel;
	}

	public void setStagedModelClassName(String stagedModelClassName) {
		_stagedModelClassName = stagedModelClassName;
	}

	public void setStagedModelClassPK(String stagedModelClassPK) {
		_stagedModelClassPK = stagedModelClassPK;
	}

	public void setStagedModelDisplayName(String stagedModelDisplayName) {
		_stagedModelDisplayName = stagedModelDisplayName;
	}

	public void setType(int type) {
		_type = type;
	}

	private StagedModel _stagedModel;
	private String _stagedModelClassName = StringPool.BLANK;
	private String _stagedModelClassPK = StringPool.BLANK;
	private String _stagedModelDisplayName = StringPool.BLANK;
	private int _type = DEFAULT;

}