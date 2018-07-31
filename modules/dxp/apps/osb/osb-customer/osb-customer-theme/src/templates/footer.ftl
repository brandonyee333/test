<#assign footer_navigation_layout = layout_local_service.fetchLayoutByFriendlyURL(group_id, false, "/footer-navigation")! />

<div class="row">
	<div class="col-sm-3">
		<svg alt="Liferay" class="liferay-logo">
			<use xlink:href="${images_folder}/custom/help_center_icons.svg#liferayLogo" />
		</svg>
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
		<div class="social-links">
			<div class="social-icon">
				<a href="https://www.facebook.com/liferay">
					<svg class="lexicon-icon lexicon-icon-social-facebook">
						<use xlink:href="${images_folder}/custom/help_center_icons.svg#social-facebook" />
					</svg>
				</a>
			</div>

			<div class="social-icon">
				<a href="https://twitter.com/liferay">
					<svg class="lexicon-icon lexicon-icon-twitter">
						<use xlink:href="${images_folder}/custom/help_center_icons.svg#social-twitter" />
					</svg>
				</a>
			</div>

			<div class="social-icon">
				<a href="https://www.linkedin.com/company/liferay-inc-">
					<svg class="lexicon-icon lexicon-icon-linkedin">
						<use xlink:href="${images_folder}/custom/help_center_icons.svg#social-linkedin" />
					</svg>
				</a>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-12 copyright secondary-text-color small">
		&copy; ${the_year} Liferay Inc. All Rights Reserved
	</div>
</div>