import React from 'react';
import PropTypes from 'prop-types';

import throttle from 'lodash.throttle';

const BANNER_HEIGHT = 56;

const StickyHeader = ({ headerText, svgId }) => {
	const { namespace } = window.ReleaseToolConstants;
	const mainContent = document.getElementById(`${namespace}mainContent`);
	const stickyHeader = document.getElementById(`${namespace}stickyHeader`);

	if (mainContent && stickyHeader) {
		const throttleHeaderDisplay = throttle(() => {
			const mainContentBoundingRect = mainContent.getBoundingClientRect();

			if (mainContentBoundingRect.top <= BANNER_HEIGHT) {
				stickyHeader.classList.remove('hide');
			} else {
				stickyHeader.classList.add('hide');
			}
		}, 300);

		window.addEventListener('scroll', throttleHeaderDisplay);
	}

	return (
		<div className="container-fluid container-fluid-max-xl">
			<svg className="lexicon-icon lexicon-icon-product-logo">
				<use xlinkHref={svgId} />
			</svg>

			{headerText}
		</div>
	);
};

StickyHeader.propTypes = {
	headerText: PropTypes.string.isRequired,
	svgId: PropTypes.string.isRequired,
};

export default StickyHeader;
