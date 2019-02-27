<#assign
	journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")
	fixPacksAssetCategoryUtil = serviceLocator.findService("com.liferay.osb.customer.release.tool.web.internal.util.FixPacksAssetCategoryUtil")

	journalArticle = journalArticleLocalService.getArticle(scopeGroupId, .vars['reserved-article-id'].getData())

	fixPackAssetCategory = fixPacksAssetCategoryUtil.fetchFixPackAssetCategory(journalArticle.getResourcePrimKey())

	productAssetCategory = fixPackAssetCategory.getParentCategory()
/>

<div class="fix-pack-details">
	<h3 class="fix-pack-version">
		${.vars['reserved-article-title'].getData()}
	</h3>

	<span class="secondary-text-color">${productAssetCategory.getTitle(locale)}</span>
</div>

<#if validator.isNull(highlights)>
	<div class="no-highlights">
		<@liferay.language key="no-highlights-for-this-release" />
	</div>
<#else>
	<#if validator.isNotNull(highlights.keyHighlights.getData())>
		<div class="key-highlights">
			<h4 class="detail-title">
				<@liferay.language key="key-highlights" />
			</h4>

			<p class="detail-body">
				${highlights.keyHighlights.getData()}
			</p>
		</div>
	</#if>

	<#if validator.isNotNull(highlights.importantChanges.getData())>
		<div class="important-changes">
			<h4 class="detail-title">
				<@liferay.language key="important-changes" />
			</h4>

			<p class="detail-body">
				${highlights.importantChanges.getData()}
			</p>
		</div>
	</#if>

	<#if validator.isNotNull(highlights.knownIssues.getData())>
		<div class="known-issues">
			<h4 class="detail-title">
				<@liferay.language key="known-issues" />
			</h4>

			<p class="detail-body">
				${highlights.knownIssues.getData()}
			</p>
		</div>
	</#if>

	<#if validator.isNotNull(highlights.security.getData())>
		<div class="security">
			<h4 class="detail-title">
				<@liferay.language key="security" />
			</h4>

			<p class="detail-body">
				${highlights.security.getData()}
			</p>
		</div>
	</#if>
</#if>