import React from 'react';

const SidebarCTA = () => {
	const {availableProduct} = window.ReleaseToolConstants;

	return (
		<a className="additional-info" href={availableProduct.url}>
			<div className="sidebar-cta">
				<svg className="lexicon-icon-product-logo">
					<use xlinkHref={availableProduct.svgId} />
				</svg>

				<h4>{availableProduct.headerText}</h4>

				<svg className="lexicon-icon lexicon-icon-arrow-right">
					<use xlinkHref="#arrow-right" />
				</svg>
			</div>
		</a>
	);
};

export default SidebarCTA;
