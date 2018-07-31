<div class="container-fluid container-fluid-max-xl">
	<div class="header-inner row">

		<div class="col-md-3 header-content">
			<a class="header-link" href="/home" title="Home">
				<svg alt="Liferay" class="header-logo">
					<use xlink:href="${images_folder}/custom/help_center_icons.svg#liferayWaffle" />
				</svg>
			</a>

			<a href="/home" title="Home">
				<h4 class="site-name">Help Center</h4>
			</a>
		</div>

		<div class="col-md-5 header-content header-menu-list secondary-text-color">
			<#list nav_items as nav_item>
				<div class="list-item">
					<h5>
						${nav_item.getName()}
					</h5>
				</div>
			</#list>
		</div>

		<div class="col-md-4 header-content user-wrapper">
			<div class="user">
				<#if is_signed_in>
					<@liferay.user_personal_bar />
				<#else>
					<a class="btn btn-primary" href="${sign_in_url}">${sign_in_text}</a>
				</#if>
			</div>
		</div>
	</div>
</div>