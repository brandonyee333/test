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

package com.liferay.osb.community.generator.layout;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

import org.osgi.framework.BundleContext;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 */
public abstract class BaseLayoutGenerator implements LayoutGenerator {

	@Override
	public void generate(long plid) throws Exception {
		if (layoutVersion.getLayoutVersion(plid) > getLayoutVersion()) {
			return;
		}

		doGenerate(plid);

		layoutVersion.setLayoutVersion(plid, getLayoutVersion());
	}

	@Override
	public String getLayoutDescription() {
		return _layoutDescription;
	}

	@Override
	public String getLayoutFriendlyURL() {
		return _layoutFriendlyURL;
	}

	@Override
	public boolean getLayoutHidden() {
		return _layoutHidden;
	}

	@Override
	public String getLayoutName() {
		return _layoutName;
	}

	@Override
	public String getLayoutTitle() {
		return _layoutTitle;
	}

	@Override
	public String getLayoutType() {
		return _layoutType;
	}

	public abstract int getLayoutVersion() throws Exception;

	protected void activate(
		BundleContext bundleContext, Map<String, Object> config) {

		this.bundleContext = bundleContext;

		_layoutDescription = GetterUtil.getString(
			config.get("osb.community.layout.description"));
		_layoutFriendlyURL = GetterUtil.getString(
			config.get("osb.community.layout.friendly.url"));
		_layoutHidden = GetterUtil.getBoolean(
			config.get("osb.community.layout.hidden"));
		_layoutName = GetterUtil.getString(
			config.get("osb.community.layout.name"));
		_layoutTitle = GetterUtil.getString(
			config.get("osb.community.layout.title"));
		_layoutType = GetterUtil.getString(
			config.get("osb.community.layout.type"));
	}

	protected abstract void doGenerate(long plid) throws Exception;

	protected BundleContext bundleContext;
	protected LayoutVersion layoutVersion;

	private String _layoutDescription;
	private String _layoutFriendlyURL;
	private boolean _layoutHidden;
	private String _layoutName;
	private String _layoutTitle;
	private String _layoutType;

}