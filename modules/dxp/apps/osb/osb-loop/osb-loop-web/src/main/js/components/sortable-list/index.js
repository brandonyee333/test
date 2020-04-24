import Component, {Config} from 'metal-jsx';
import {List, Map} from 'immutable';

import InfiniteScroll from '../InfiniteScroll';
import LoadingCard from '../LoadingCard';
import SortableListHeader from './SortableListHeader';
import SortableListItem from './SortableListItem';
import {formatTime} from '../../lib/util';

class SortableList extends Component {
	created() {
		this.props.onFetchItems();
	}

	render() {
		const {
			displayURL,
			hasMoreItems,
			headerPropsIMap,
			itemsIList,
			loading,
			onFetchItems
		} = this.props;

		return (
			<div class="sortable-list-container">
				<SortableListHeader {...headerPropsIMap.toJS()} />

				<InfiniteScroll hasMoreResults={hasMoreItems} onScrollEnd={onFetchItems}>
					<ul>
						{
							itemsIList.toArray().map(
								({creatorIMap, itemIMap}, i) => {
									const {
										createTime,
										entityClassPK,
										modifiedTime,
										title
									} = itemIMap.toJS();

									return (
										<SortableListItem
											creatorIMap={creatorIMap}
											displayURL={`${displayURL}/${entityClassPK}`}
											key={i}
											secondaryInfo={formatTime(modifiedTime || createTime)}
											title={title}
										/>
									);
								}
							)
						}
					</ul>

					{loading &&
						<LoadingCard />
					}
				</InfiniteScroll>
			</div>
		);
	}
}

SortableList.PROPS = {
	displayURL: Config.string(),
	hasMoreItems: Config.bool(),
	headerPropsIMap: Config.instanceOf(Map),
	itemsIList: Config.instanceOf(List),
	loading: Config.bool().value(true),
	onFetchItems: Config.func()
};

export default SortableList;