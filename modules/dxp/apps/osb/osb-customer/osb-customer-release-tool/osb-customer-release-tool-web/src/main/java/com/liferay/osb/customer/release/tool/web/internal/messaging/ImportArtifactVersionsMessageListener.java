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

package com.liferay.osb.customer.release.tool.web.internal.messaging;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.osb.customer.release.tool.configuration.ReleaseToolConfigurationValues;
import com.liferay.osb.customer.release.tool.constants.ArtifactVersionConstants;
import com.liferay.osb.customer.release.tool.service.ArtifactVersionLocalService;
import com.liferay.osb.customer.release.tool.web.internal.constants.ReleaseAssetCategoryProperty;
import com.liferay.osb.customer.release.tool.web.internal.util.ReleasesAssetCategoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, service = ImportArtifactVersionsMessageListener.class
)
public class ImportArtifactVersionsMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, 1, TimeUnit.DAY);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		List<AssetCategory> assetCategories =
			_releasesAssetCategoryUtil.getProductAssetCategories();

		for (AssetCategory assetCategory : assetCategories) {
			List<Long> releaseAssetCategoryIds =
				_releasesAssetCategoryUtil.getNoArtifactAssetCategories(
					assetCategory.getCategoryId());

			if (releaseAssetCategoryIds.isEmpty()) {
				continue;
			}

			File file = null;
			ZipReader zipReader = null;

			try {
				file = downloadMetadataFile(assetCategory.getCategoryId());

				if (file == null) {
					continue;
				}

				zipReader = ZipReaderFactoryUtil.getZipReader(file);

				importArtifacts(zipReader, releaseAssetCategoryIds);
			}
			finally {
				if (zipReader != null) {
					zipReader.close();
				}

				FileUtil.delete(file);
			}
		}
	}

	protected File downloadMetadataFile(long assetCategoryId) throws Exception {
		String metadataFile = _releasesAssetCategoryUtil.getPropertyValue(
			assetCategoryId, ReleaseAssetCategoryProperty.METADATA_FILE);

		if (Validator.isNull(metadataFile)) {
			return null;
		}

		Http.Options options = new Http.Options();

		options.setLocation(
			ReleaseToolConfigurationValues.RELEASE_METADATA_URL + metadataFile);

		byte[] bytes = _http.URLtoByteArray(options);

		File file = FileUtil.createTempFile("zip");

		FileUtil.write(file, bytes);

		return file;
	}

	protected void importArtifacts(
			ZipReader zipReader, List<Long> assetCategoryIds)
		throws IOException, PortalException {

		for (long assetCategoryId : assetCategoryIds) {
			String releaseId = _releasesAssetCategoryUtil.getPropertyValue(
				assetCategoryId, ReleaseAssetCategoryProperty.RELEASE_ID);

			if (Validator.isNull(releaseId)) {
				continue;
			}

			try (InputStream inputStream = zipReader.getEntryAsInputStream(
					"bootstrap-" + releaseId + ".txt")) {

				importBootstrapArtifacts(inputStream, assetCategoryId);
			}

			try (InputStream inputStream = zipReader.getEntryAsInputStream(
					"bundleinfo-" + releaseId + ".txt")) {

				importBundleInfoArtifacts(inputStream, assetCategoryId);
			}

			try (InputStream inputStream = zipReader.getEntryAsInputStream(
					"bundleinfo-" + releaseId + "-private.txt")) {

				importBundleInfoArtifacts(inputStream, assetCategoryId);
			}

			try (InputStream inputStream = zipReader.getEntryAsInputStream(
					"dependencies-" + releaseId + ".txt")) {

				importDependencyArtifacts(inputStream, assetCategoryId);
			}
		}
	}

	protected void importBootstrapArtifacts(
			InputStream inputStream, long assetCategoryId)
		throws IOException, PortalException {

		if (inputStream == null) {
			return;
		}

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new InputStreamReader(inputStream))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				String[] bootstrapParts = StringUtil.split(line);

				if (bootstrapParts.length < 3) {
					continue;
				}

				_artifactVersionLocalService.addArtifactVersion(
					assetCategoryId, ArtifactVersionConstants.OWNER_LIFERAY,
					ArtifactVersionConstants.REPOSITORY_PUBLIC,
					bootstrapParts[0], bootstrapParts[1], bootstrapParts[2],
					ArtifactVersionConstants.PACKAGING_JAR);
			}
		}
	}

	protected void importBundleInfoArtifacts(
			InputStream inputStream, long assetCategoryId)
		throws IOException, PortalException {

		if (inputStream == null) {
			return;
		}

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new InputStreamReader(inputStream))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				String[] bundleParts = StringUtil.split(line);

				if (bundleParts.length < 3) {
					continue;
				}

				_artifactVersionLocalService.addArtifactVersion(
					assetCategoryId, ArtifactVersionConstants.OWNER_LIFERAY,
					ArtifactVersionConstants.getRepository(bundleParts[3]),
					bundleParts[0], bundleParts[1], bundleParts[2],
					bundleParts[6]);
			}
		}
	}

	protected void importDependencyArtifacts(
			InputStream inputStream, long assetCategoryId)
		throws IOException, PortalException {

		if (inputStream == null) {
			return;
		}

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(new InputStreamReader(inputStream))) {

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				String[] dependencyParts = StringUtil.split(line);

				if (dependencyParts.length < 4) {
					continue;
				}

				_artifactVersionLocalService.addArtifactVersion(
					assetCategoryId, ArtifactVersionConstants.OWNER_THIRD_PARTY,
					ArtifactVersionConstants.REPOSITORY_THIRD_PARTY,
					dependencyParts[1], dependencyParts[2], dependencyParts[3],
					ArtifactVersionConstants.PACKAGING_JAR);
			}
		}
	}

	@Reference
	private ArtifactVersionLocalService _artifactVersionLocalService;

	@Reference
	private Http _http;

	@Reference
	private ReleasesAssetCategoryUtil _releasesAssetCategoryUtil;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}