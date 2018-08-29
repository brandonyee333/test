import React from 'react';
import PropTypes from 'prop-types';

import getCN from 'classnames';

const Accordion = props => (
	<div aria-orientation="vertical" className="panel-group" role="tablist">
		{props.items.map(
			(item, index) => (
				<AccordionItem key={index} body={item.body} title={item.title} />
			)
		)}
	</div>
);

Accordion.propTypes = {
	items: PropTypes.array.isRequired
};

class AccordionItem extends React.Component {
	state = {
		expanded: false
	};

	static propTypes = {
		body: PropTypes.node.isRequired,
		title: PropTypes.node.isRequired
	};

	handleClick = () => {
		this.setState({expanded: !this.state.expanded});
	};

	render() {
		const {body, title} = this.props;
		const {expanded} = this.state;

		const className = getCN(
			'btn-link panel-header panel-header-link',
			{
				'expanded': expanded
			}
		);

		return (
			<div className="panel">
				<button className={className} onClick={this.handleClick} role="tab" type="button">
					<div className="panel-title">
						{title}
					</div>

					<svg className="lexicon-icon lexicon-icon-angle-right">
						<use xlinkHref="#angle-right" />
					</svg>
				</button>

				{expanded && (
					<div role="tabpanel">
						<div className="panel-body">
							{body}
						</div>
					</div>
				)}
			</div>
		);
	}
}

export default Accordion;