import React from 'react';
import PropTypes from 'prop-types';

const StickyHeader = ({ headerText, svgId }) => (
	<div className="container-fluid container-fluid-max-xl">
		<svg className="lexicon-icon lexicon-icon-product-logo">
			<use xlinkHref={svgId} />
		</svg>

		{headerText}
	</div>
);

StickyHeader.propTypes = {
	headerText: PropTypes.string.isRequired,
	svgId: PropTypes.string.isRequired
}

export default StickyHeader;