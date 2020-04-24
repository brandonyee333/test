import Component from 'metal-jsx';
import moment from 'moment';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import AnnouncementHeader from '../AnnouncementHeader';
import Card from '../card';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';

const person = LoopAssets.getPerson();

const styles = {
	paddingBottom: '28px',
	width: '616px'
};

const contentModifiedTime = +moment().subtract(8, 'h');
const createTime = +moment().subtract(8, 'd');
const stickyTime = +moment().add(4, 'd');

class AnnouncementHeaderExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<Card style={styles}>
					<AnnouncementHeader
						{...this.otherProps()}
						createTime={createTime}
						creator={LoopConstants.currentPerson}
						id={1}
						onBookmarkClick={noop}
					/>
				</Card>
			</Provider>
		);
	}
}

class AnnouncementHeaderKit extends Component {
	render() {
		return (
			<Kit card={false} header="AnnouncementHeader">
				<ElementContainer header="shared with person, edited, location, & bookmarked">
					<AnnouncementHeaderExample
						bookmarked={true}
						contentModifiedTime={contentModifiedTime}
						location="Diamond Bar, USA"
						sharedTo={[person]}
						title="Upcoming System Maintenance"
					/>
				</ElementContainer>

				<ElementContainer header="shared with no one">
					<AnnouncementHeaderExample title="Upcoming System Maintenance" />
				</ElementContainer>

				<ElementContainer header="shared with person, single line">
					<AnnouncementHeaderExample sharedTo={[person]} title="Upcoming System Maintenance" />
				</ElementContainer>

				<ElementContainer header="shared with topic, long">
					<AnnouncementHeaderExample
						sharedTo={[LoopAssets.getTopic('Lorem ipsum dolor sit amet, an cum consul inciderint. Quo nibh eius ad. Mel te harum percipitur cotidieque, te pro tation electram.')]}
						title="Upcoming System Maintenance"
					/>
				</ElementContainer>

				<ElementContainer header="shared with topic, division, and person">
					<AnnouncementHeaderExample
						sharedTo={[
							LoopAssets.getDivision('Lorem ipsum dolor sit amet'),
							LoopAssets.getTopic(),
							person
						]}
						title="Upcoming System Maintenance"
					/>
				</ElementContainer>

				<ElementContainer header="edited">
					<AnnouncementHeaderExample contentModifiedTime={contentModifiedTime} title="Upcoming System Maintenance" />
				</ElementContainer>

				<ElementContainer header="relative date">
					<AnnouncementHeaderExample createTime={Date.now()} title="Upcoming System Maintenance" />
				</ElementContainer>

				<ElementContainer header="sticky time">
					<AnnouncementHeaderExample stickyTime={stickyTime} title="Upcoming System Maintenance" />
				</ElementContainer>

				<ElementContainer header="bookmarked">
					<AnnouncementHeaderExample bookmarked={true} title="Upcoming System Maintenance" />
				</ElementContainer>

				<ElementContainer header="Long Title">
					<AnnouncementHeaderExample title="This is an exceptionally long title that should wrap onto multiple lines." />
				</ElementContainer>

				<ElementContainer header="long title no spaces">
					<AnnouncementHeaderExample title="https://open.dsv.su.se/pluginfile.php/1483/mod_resource/content/1/KanbanAndScrumInfoQVersionFINAL.pdf" />
				</ElementContainer>
			</Kit>
		);
	}
}

export default AnnouncementHeaderKit;