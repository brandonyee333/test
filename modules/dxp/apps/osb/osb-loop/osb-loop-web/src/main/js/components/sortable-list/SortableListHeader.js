import Component, {Config} from 'metal-jsx';

import Icon from '../Icon';
import InlineButton from '../InlineButton';

class SortableListHeader extends Component {
	getSortArrow(sortColumn) {
		const {reverseSort, selectedField} = this.props;

		let retVal;

		if (selectedField === sortColumn) {
			retVal = <Icon name={reverseSort ? 'arrow-down-short' : 'arrow-up-short'} size="tiny" />;
		}

		return retVal;
	}

	render() {
		const {
			onSortClick,
			sortableColumns: {
				creator,
				secondaryInfo,
				title
			}
		} = this.props;

		return (
			<div class="sortable-list-header-container">
				<InlineButton elementClasses="title-header" onClick={onSortClick(title.value)}>
					{title.name}

					{this.getSortArrow(title.value)}

				</InlineButton>

				<InlineButton elementClasses="creator-header" onClick={onSortClick(creator.value)}>
					{creator.name}

					{this.getSortArrow(creator.value)}
				</InlineButton>

				<InlineButton elementClasses="secondary-info-header" onClick={onSortClick(secondaryInfo.value)}>
					{secondaryInfo.name}

					{this.getSortArrow(secondaryInfo.value)}
				</InlineButton>
			</div>
		);
	}
}

SortableListHeader.PROPS = {
	onSortClick: Config.func(),
	reverseSort: Config.bool().value(false),
	selectedField: Config.string(),
	sortableColumns: Config.object().required()
};

export default SortableListHeader;