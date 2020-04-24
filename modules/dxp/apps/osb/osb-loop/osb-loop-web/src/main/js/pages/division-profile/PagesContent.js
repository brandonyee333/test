import Component, {Config} from 'metal-jsx';

import BaseLayout from '../BaseLayout';
import CreatePageCard from '../../components/CreatePageCard';
import LoopConstants from '../../lib/loop-constants';
import PageEditor from '../../components/PageEditor';
import Pages from '../../components/pages';
import {PAGES} from '../../lib/router-constants';

class PagesContent extends Component {
	created() {
		this.handleSortClick_ = this.handleSortClick_.bind(this);
	}

	handleSortClick_(sortType) {
		return () => {
			const {reverseSort_, selectedField_} = this.state;

			if (sortType === selectedField_) {
				this.state.reverseSort_ = !reverseSort_;
			}
			else {
				this.setState(
					{
						reverseSort_: false,
						selectedField_: sortType
					}
				);
			}
		};
	}

	render() {
		const {
			props: {
				displayURL,
				editing,
				hasPages,
				ownerId,
				pageId,
				pagesSubNav,
				permissionAdd
			},
			state: {reverseSort_, selectedField_}
		} = this;

		return (
			<BaseLayout.NineColumn>
				{!editing &&
					<span>
						{!hasPages &&
							<CreatePageCard displayURL={displayURL} showButton={permissionAdd} />
						}

						{hasPages &&
							<Pages
								displayURL={`${displayURL}/${PAGES}`}
								id={pageId}
								onSortClick={this.handleSortClick_}
								ownerId={ownerId}
								pagesSubNav={pagesSubNav}
								permissionAdd={permissionAdd}
								reverseSort={reverseSort_}
								selectedField={selectedField_}
							/>
						}
					</span>
				}

				{editing &&
					<PageEditor
						contentPlaceholder={Liferay.Language.get('write-content-here')}
						displayURL={`${displayURL}/${PAGES}`}
						id={pageId}
						ownerClassNameId={LoopConstants.classNameIds.divisions}
						ownerId={ownerId}
						titlePlaceholder={Liferay.Language.get('title-required')}
					/>
				}
			</BaseLayout.NineColumn>
		);
	}
}

PagesContent.PROPS = {
	displayURL: Config.string(),
	editing: Config.bool(),
	hasPages: Config.bool(),
	ownerId: Config.number(),
	pageId: Config.number(),
	pagesSubNav: Config.string(),
	permissionAdd: Config.bool()
};

PagesContent.STATE = {
	reverseSort_: Config.bool().value(false),
	selectedField_: Config.string().value('title_sortable')
};

export default PagesContent;