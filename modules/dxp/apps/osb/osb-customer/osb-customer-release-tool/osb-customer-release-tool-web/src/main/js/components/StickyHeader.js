import React from 'react';
import PropTypes from 'prop-types';

const BANNER_HEIGHT = 56;

const StickyHeader = ({ headerText, svgId }) => {
	const { namespace } = window.ReleaseToolConstants;
	const mainContent = document.getElementById(`${namespace}mainContent`);
	const stickyHeader = document.getElementById(`${namespace}stickyHeader`);

	if (mainContent && stickyHeader) {
		window.addEventListener('scroll', () => {
			const mainContentBoundingRect = mainContent.getBoundingClientRect();

			if (mainContentBoundingRect.top <= BANNER_HEIGHT) {
				stickyHeader.classList.remove('hide');
			} else {
			stickyHeader.classList.add('hide');
			}
		});
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
