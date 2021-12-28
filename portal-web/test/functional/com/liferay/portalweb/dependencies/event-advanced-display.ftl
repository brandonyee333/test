<#assign
	view = freeMarkerPortletPreferences.toString()!""
	EventDate_Data = getterUtil.getString(EventDate.getData())
/>

<#if view?contains("thumbnailListView")>
	<div class="figure figure-circle">
		<img alt="thumbnail" class="img-responsive" src="${EventCoverImage.getData()}">
		<div class="figcaption-full">
		<div class="flex-container" style="height: 100%;">
				<div class="flex-item-center">
					<h2>${EventHeadline.getData()}</h2>

					<p>${EventLeadText.getData()}</p>
				</div>
			</div>
		</div>
	</div>
<#elseif view?contains("carouselListView")>
	<div class="card">
		<div class="aspect-ratio aspect-ratio-16-to-9">
			<img alt="thumbnail" class="image" src="${EventCoverImage.getData()}">
		</div>

		<div class="carousel-caption">
			<h1>${EventHeadline.getData()}</h1>
<#if validator.isNotNull(EventDate_Data)>
	<#assign EventDate_DateObj = dateUtil.parseDate("yyyy-MM-dd", EventDate_Data, locale) />
				<p><span class="icon-large icon-calendar"></span> ${dateUtil.getDate(EventDate_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}</p>
</#if>
		</div>
	</div>
<#else>
	<div class="card card-horizontal">
		<div class="card-row">
			<div class="card-col-field">
				<img alt="thumbnail" src="${EventCoverImage.getData()}" style="max-width: 125px;">
			</div>

			<div class="card-col-content card-col-gutters">
				<p>${EventHeadline.getData()}</p>
			</div>
		</div>
	</div>
</#if>