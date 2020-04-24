import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {Map} from 'immutable';

import Avatar from '../Avatar';
import EntityLink from '../EntityLink';

class SortableListItem extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		Liferay.Loop.SPA.navigate(this.props.displayURL);
	}

	render() {
		const {
			creatorIMap,
			displayURL,
			secondaryInfo,
			selected,
			title
		} = this.props;

		const creator = creatorIMap.toJS();

		const classes = getCN(
			'sortable-list-item-container',
			{selected}
		);

		return (
			<li class={classes} onClick={this.handleClick_}>
				<a class="title" href={displayURL}>{title}</a>

				<span class="creator-info">
					<Avatar entity={creator} size="tiny" summary={true} />

					<EntityLink entity={creator} summary={true} />
				</span>

				<span class="secondary-info">{secondaryInfo}</span>
			</li>
		);
	}
}

SortableListItem.PROPS = {
	creatorIMap: Config.instanceOf(Map),
	displayURL: Config.string(),
	secondaryInfo: Config.string(),
	selected: Config.bool(),
	title: Config.string()
};

export default SortableListItem;