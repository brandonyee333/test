import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import SortableListHeader from '../sortable-list/SortableListHeader';

const SORTABLE_COLUMNS = {
	creator: {
		name: 'Creator',
		value: 'userName'
	},
	secondaryInfo: {
		name: 'Date',
		value: 'modifiedDate'
	},
	title: {
		name: 'File Name',
		value: 'title'
	}
};

const styles = {
	'max-width': '100%',
	'width': '800px'
};

class SortableListHeaderExample extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_(value) {
		return () => {
			alert(value);
		};
	}

	render() {
		const {reverseSort, selectedField} = this.props;

		return (
			<SortableListHeader
				onSortClick={this.handleClick_}
				reverseSort={reverseSort}
				selectedField={selectedField}
				sortableColumns={SORTABLE_COLUMNS}

			/>
		);
	}
}

class SortableListHeaderKit extends Component {
	render() {
		return (
			<Kit header="SortableListHeader">
				<ElementContainer header="First Column Ascending" style={styles}>
					<SortableListHeaderExample reverseSort={true} selectedField="title" />
				</ElementContainer>

				<ElementContainer header="First Column Descending" style={styles}>
					<SortableListHeaderExample reverseSort={false} selectedField="title" />
				</ElementContainer>

				<ElementContainer header="Second Column Ascending" style={styles}>
					<SortableListHeaderExample reverseSort={true} selectedField="userName" />
				</ElementContainer>

				<ElementContainer header="Second Column Descending" style={styles}>
					<SortableListHeaderExample reverseSort={false} selectedField="userName" />
				</ElementContainer>

				<ElementContainer header="Third Column Ascending" style={styles}>
					<SortableListHeaderExample reverseSort={true} selectedField="modifiedDate" />
				</ElementContainer>

				<ElementContainer header="Third Column Descending" style={styles}>
					<SortableListHeaderExample reverseSort={false} selectedField="modifiedDate" />
				</ElementContainer>
			</Kit>
		);
	}
}

export default SortableListHeaderKit;