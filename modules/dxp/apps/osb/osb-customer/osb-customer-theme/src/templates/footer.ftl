<#assign footer_navigation_layout = layout_local_service.fetchLayoutByFriendlyURL(group_id, false, "/footer-navigation")! />

<div class="row">
	<div class="col-sm-3">
		<#if getterUtil.getBoolean(theme_settings["footer-logo"])>
			<svg alt="Liferay" class="liferay-logo">
				<use xlink:href="${images_folder}/custom/help_center_icons.svg#liferay-logo" />
			</svg>
		</#if>
	</div>

	<#if footer_navigation_layout?has_content>
		<#list footer_navigation_layout.getChildren() as footer_navigation_item>
			<div class="col-sm-2">
				<h5>
					${footer_navigation_item.getName(locale)}
				</h5>

				<ul class="footer-links">
					<#list footer_navigation_item.getChildren() as nav_layout>
						<li>
							<a href="${nav_layout.getRegularURL(request)}">${nav_layout.getName(locale)}</a>
						</li>
					</#list>
				</ul>
			</div>
		</#list>
	</#if>

	<div class="col-sm-3">
		<#if getterUtil.getBoolean(theme_settings["footer-social-links"])>
			<div class="social-links">
				<#if social_link_one_name != "" && social_link_one_url != "">
					<div class="social-icon">
						<a href="${social_link_one_url}">
							<svg class="lexicon-icon lexicon-icon-social-${social_link_one_name}">
								<use xlink:href="${images_folder}/custom/help_center_icons.svg#social-${social_link_one_name}" />
							</svg>
						</a>
					</div>
				</#if>

				<#if social_link_two_url != "" && social_link_two_name != "">
					<div class="social-icon">
						<a href="${social_link_two_url}">
							<svg class="lexicon-icon lexicon-icon-${social_link_two_name}">
								<use xlink:href="${images_folder}/custom/help_center_icons.svg#social-${social_link_two_name}" />
							</svg>
						</a>
					</div>
				</#if>

				<#if social_link_three_name != "" && social_link_three_url != "">
					<div class="social-icon">
						<a href="${social_link_three_url}">
							<svg class="lexicon-icon lexicon-icon-${social_link_three_name}">
								<use xlink:href="${images_folder}/custom/help_center_icons.svg#social-${social_link_three_name}" />
							</svg>
						</a>
					</div>
				</#if>
			</div>
		</#if>
	</div>
</div>

<div class="row">
	<div class="col-sm-12 copyright secondary-text-color small">
		&copy; ${the_year} Liferay Inc. All Rights Reserved
	</div>
</div>