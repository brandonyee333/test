import Component from 'metal-jsx';
import {Provider} from 'metal-redux';
import {noop} from 'lodash';

import Card from '../card';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import PostHeader from '../PostHeader';

const person = LoopAssets.getPerson();

const time1 = new Date().getTime() - (1000 * 60 * 60 * 8);
const time2 = new Date().getTime() - (1000 * 60 * 60 * 24 * 8);

class PostHeaderExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<Card style="width: 616px;">
					<PostHeader
						createTime={time2}
						creator={LoopConstants.currentPerson}
						id={0}
						onBookmarkClick={noop}
						{...this.otherProps()}
					/>
				</Card>
			</Provider>
		);
	}
}

class PostHeaderKit extends Component {
	render() {
		return (
			<Kit card={false} header="PostHeader">
				<ElementContainer header="shared with person, edited, location, bookmarked, show relative">
					<PostHeaderExample
						bookmarked={true}
						contentModifiedTime={time1}
						location="Diamond Bar, USA"
						sharedTo={[person]}
					/>
				</ElementContainer>

				<ElementContainer header="shared with no one">
					<PostHeaderExample />
				</ElementContainer>

				<ElementContainer header="shared with person, single line">
					<PostHeaderExample sharedTo={[person]} />
				</ElementContainer>

				<ElementContainer header="with location">
					<PostHeaderExample location="Diamond Bar, USA" />
				</ElementContainer>

				<ElementContainer header="shared with division, long text">
					<PostHeaderExample sharedTo={[LoopAssets.getDivision('Lorem ipsum dolor sit amet, an cum consul inciderint. Quo nibh eius ad. Mel te harum percipitur cotidieque, te pro tation electram.')]} />
				</ElementContainer>

				<ElementContainer header="shared with person, division, & topic on multiple lines">
					<PostHeaderExample
						sharedTo={[
							LoopAssets.getDivision('Lorem ipsum dolor sit amet'),
							person,
							LoopAssets.getTopic()
						]}
					/>
				</ElementContainer>

				<ElementContainer header="edited">
					<PostHeaderExample contentModifiedTime={time1} />
				</ElementContainer>

				<ElementContainer header="show relative date">
					<PostHeaderExample createTime={time1} />
				</ElementContainer>

				<ElementContainer header="bookmarked">
					<PostHeaderExample bookmarked={true} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default PostHeaderKit;