<#assign
	journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")
	fixPacksAssetCategoryUtil = serviceLocator.findService("com.liferay.osb.customer.release.tool.web.internal.util.FixPacksAssetCategoryUtil")

	releaseDateData = getterUtil.getString(releaseDate.getData())

	journalArticle = journalArticleLocalService.getArticle(scopeGroupId, .vars['reserved-article-id'].getData())

	fixPackAssetCategory = fixPacksAssetCategoryUtil.fetchFixPackAssetCategory(journalArticle.getResourcePrimKey())

	productAssetCategory = fixPackAssetCategory.getParentCategory()
/>

<tr class="journal-article-row">
	<td class="lfr-released-column">
		<#if validator.isNotNull(releaseDateData)>
			<#assign releaseDateDateObj = dateUtil.parseDate("yyyy-MM-dd", releaseDateData, locale) />

			${dateUtil.getDate(releaseDateDateObj, "MMM d, yyyy", locale)}
		</#if>
	</td>
	<td class="lfr-details-column">
		<div class="fix-pack-details">
			<h3 class="fix-pack-version">
				${.vars['reserved-article-title'].getData()}
			</h3>

			<span class="secondary-text-color">
				${productAssetCategory.getTitle(locale)}
			</span>
		</div>

		<#if validator.isNull(highlights)>
			<@liferay.language key="no-highlights-for-this-release" />
		<#else>
			<#if validator.isNotNull(highlights.keyHighlights.getData())>
				<div class="key-highlights">
					<h4 class="highlights-section-title">
						<@liferay.language key="key-highlights" />
					</h4>

					<p class="highlights-section">
						${highlights.keyHighlights.getData()}
					</p>
				</div>
			</#if>

			<#if validator.isNotNull(highlights.importantChanges.getData())>
				<div class="important-changes">
					<h4 class="highlights-section-title">
						<@liferay.language key="important-changes" />
					</h4>

					<p class="highlights-section">
						${highlights.importantChanges.getData()}
					</p>
				</div>
			</#if>

			<#if validator.isNotNull(highlights.knownIssues.getData())>
				<div class="known-issues">
					<h4 class="highlights-section-title">
						<@liferay.language key="known-issues" />
					</h4>

					<p class="highlights-section">
						${highlights.knownIssues.getData()}
					</p>
				</div>
			</#if>

			<#if validator.isNotNull(highlights.security.getData())>
				<div class="security">
					<h4 class="highlights-section-title">
						<@liferay.language key="security" />
					</h4>

					<p class="highlights-section">
						${highlights.security.getData()}
					</p>
				</div>
			</#if>
		</#if>
	</td>
</tr>