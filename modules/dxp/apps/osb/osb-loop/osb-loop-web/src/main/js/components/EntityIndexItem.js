import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Avatar from './Avatar';
import Button from './Button';
import Icon from './Icon';
import EntityLink from './EntityLink';
import FollowButton from './FollowButton';
import LoopConstants from '../lib/loop-constants';
import NoResultsDisplay from './NoResultsDisplay';
import {isCurrentPerson, isJobTitle} from '../lib/util';

class EntityIndexItem extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_(event) {
		let preventDefault = false;

		const {delegateTarget, target} = event;

		let curElement = target;

		while (delegateTarget.contains(curElement)) {
			const nodeName = curElement.nodeName.toLowerCase();

			if (nodeName === 'a' || nodeName === 'button') {
				preventDefault = true;

				break;
			}

			curElement = curElement.parentElement;
		}

		if (!preventDefault) {
			Liferay.Loop.SPA.navigate(this.props.entity.displayURL);
		}
	}

	render() {
		const {entity, secondaryButtonConfig, secondaryDetails} = this.props;

		const {
			displayURL,
			entityClassNameId,
			entityClassPK,
			name,
			status
		} = entity;

		const hoverDetail = secondaryDetails[1];
		const inactive = entity.inactive || LoopConstants.status.inactive === status;
		const initialDetail = secondaryDetails[0];

		return (
			<div class={getCN('entity-index-item-container', {inactive})} onClick={this.handleClick_}>
				{!isCurrentPerson(entityClassPK) && !isJobTitle(entity) && !inactive &&
					<FollowButton
						classNameId={entityClassNameId}
						display="mini"
						displayURL={displayURL}
						followingName={name}
						id={entityClassPK}
					/>
				}

				{secondaryButtonConfig &&
					<Button
						data-tooltip
						elementClasses="secondary-button"
						onClick={secondaryButtonConfig.onClick}
						role="secondary"
						title={secondaryButtonConfig.title}
					>
						<Icon name={secondaryButtonConfig.iconName} />
					</Button>
				}

				<Avatar entity={entity} size="large" />

				<EntityLink entity={entity} showTrigger={false} />

				<div class="details secondary-info">
					{initialDetail &&
						<div>
							{initialDetail}
						</div>
					}

					{hoverDetail &&
						<div>
							{hoverDetail}
						</div>
					}
				</div>
			</div>
		);
	}
}

EntityIndexItem.PROPS = {
	entity: Config.object().required(),
	secondaryButtonConfig: Config.shapeOf(
		{
			iconName: Config.string(),
			onClick: Config.func(),
			title: Config.string()
		}
	),
	secondaryDetails: Config.array().value([])
};

class Wrapper extends Component {
	render() {
		return <div class="entity-index-item-wrapper-container">{this.props.children}</div>;
	}
}

EntityIndexItem.NoResult = ({icon, message}) => (
	<div class="entity-index-item-container non-click">
		<NoResultsDisplay icon={icon} message={message} size="small" />
	</div>
);

EntityIndexItem.Wrapper = Wrapper;

export default EntityIndexItem;