<div class="container-fluid container-fluid-max-xl">
	<div class="header-inner row">
		<div class="col-md-3 header-content">
			<a class="header-link" href="${site_logo_url}" title="Home">
				<svg alt="<@liferay.language key="liferay-help-center" />" class="header-logo">
					<use xlink:href="#liferay-waffle" />
				</svg>
			</a>

			<a href="${site_name_url}" title="${site_name}">
				<h2 class="site-name">
					${site_name}
				</h2>
			</a>
		</div>

		<div class="col-md-5 header-content header-menu-list secondary-text-color">
			<div class="hide list-item">
				<h5>
					<@liferay.language key="products" />
				</h5>

				<div class="header-menu" id="megaMenuProducts"></div>
			</div>

			<div class="hide list-item">
				<h5>
					<@liferay.language key="resources" />
				</h5>

				<div class="header-menu" id="megaMenuResources"></div>
			</div>

			<div class="hide list-item">
				<h5>
					<@liferay.language key="security" />
				</h5>

				<div class="header-menu" id="megaMenuSecurity"></div>
			</div>

			<div class="hide list-item">
				<h5>
					<@liferay.language key="support" />
				</h5>

				<div class="header-menu" id="megaMenuSupport"></div>
			</div>
		</div>

		<div class="col-md-4 header-content user-wrapper">
			<div class="user">
				<#if is_signed_in>
					<div class="user-personal-bar" data-toggle="dropdown">
						<@liferay.user_personal_bar />
					</div>

					<div class="dropdown-menu dropdown-menu-caret dropdown-menu-end" id="user-dropdown">
						<div id="user-menu" role="menu">
							<a class="my-activities" href="https://help.liferay.com/hc/requests"><@liferay.language key="my-activities" /></a>

							<a href="/c/portal/logout"><@liferay.language key="sign-out" /></a>
						</div>
					</div>
				<#else>
					<a class="btn btn-primary" href="${sign_in_url}">${sign_in_text}</a>
				</#if>
			</div>
		</div>
	</div>
</div>