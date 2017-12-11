<#assign osbTemplateUtil = serviceLocator.findService("com.liferay.osb.customer.web.internal.util.OSBTemplateUtil") />

<#if !osbTemplateUtil.hasPermission(permissionChecker, "VIEW_JOURNAL_ARTICLE")>
	<style>
		.knowledge-base-article {
			display: none;
		}
	</style>
</#if>

<div class="clearfix lower-blocks">
	<#if section_header.getSiblings()?has_content>
		<#list section_header.getSiblings()?chunk(4) as current_category>
			<div class="col-md-6 col-sm-offset-3 row">
				<#list current_category as section>
					<#assign cssClassSize = "col-md-" + 12 / current_category?size />

					<div class="category-content ${cssClassSize} text-center">
						<#if section.getData() != "">
							<h2 class="header">${section.getData()}</h2>
						</#if>

						<div class="category-text">
							${section.section_content.getData()}
						</div>
					</div>
				</#list>
			</div>
		</#list>
	</#if>
</div>