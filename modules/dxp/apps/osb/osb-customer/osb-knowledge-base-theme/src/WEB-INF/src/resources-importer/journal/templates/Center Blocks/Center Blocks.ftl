<#assign
	osbTemplateUtil = serviceLocator.findService("com.liferay.osb.customer.web.internal.util.OSBTemplateUtil")

	hasJournalView = osbTemplateUtil.hasPermission(permissionChecker, "VIEW_JOURNAL_ARTICLE")
/>

<div class="center-blocks clearfix">
	<#if section_header.getSiblings()?has_content>
		<#assign categorySize = hasJournalView?string('4', '3')?number />

		<#list section_header.getSiblings()?chunk(categorySize) as current_category>
			<div class="offset1 row-fluid span10">
				<#list current_category as section>
					<#if hasJournalView || current_category_has_next>
						<#assign cssClassSize = "span" + 12 / current_category?size />

						<div class="category-content ${cssClassSize} text-center">
							<#if section.getData() != "">
								<h2 class="header">
									<#if section.section_page_link.getData() != "">
										<a href="${section.section_page_link.getFriendlyUrl()}">
									</#if>

									${section.getData()}

									<#if section.section_page_link.getData() != "">
										</a>
									</#if>
								</h2>
							</#if>

							<#if section.section_image.getData() != "">
								<#if section.section_page_link.getData() != "">
									<a href="${section.section_page_link.getFriendlyUrl()}">
								</#if>

								<img alt="${section.getData()}" class="category-icon" src="${section.section_image.getData()}" />

								<#if section.section_page_link.getData() != "">
									</a>
								</#if>
							</#if>

							<#if section.section_page_link.getData() != "">
								</a>
							</#if>

							<div class="category-text">
								${section.section_content.getData()}
							</div>
						</div>
					</#if>
				</#list>
			</div>
		</#list>
	</#if>
</div>