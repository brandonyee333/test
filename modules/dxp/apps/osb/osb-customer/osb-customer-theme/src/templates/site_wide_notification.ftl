<#assign
	journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")

	journalArticle = journalArticleLocalService.fetchArticleByUrlTitle(theme_display.getCompanyGroupId(), "banner")!""
/>

<#if validator.isNotNull(journalArticle) && journalArticle.isApproved()>
	<#assign journalArticleDisplay = journalArticleLocalService.getArticleDisplay(journalArticle.getGroupId(), journalArticle.getArticleId(), "", theme_display.getLanguageId(), theme_display) />

	<div class="alert alert-danger alert-dismissible alert-fluid hide site-wide-notification" id="siteWideNotification" role="alert">
		<div>
			<svg class="lexicon-icon lexicon-icon-exclamation-full" viewBox="0 0 512 512">
				<use xlink:href="#exclamation-full" />
			</svg>

			<div class="notification-content">
				${journalArticleDisplay.getContent()}
			</div>
		</div>

		<button aria-label="Close" class="close" id="closeNotification" type="button">
			<svg aria-hidden="true" class="lexicon-icon lexicon-icon-times" viewBox="0 0 512 512">
				<use xlink:href="#times" />
			</svg>
		</button>
	</div>
</#if>