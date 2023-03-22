import ClayTable from '@clayui/table';

import './MemberTableRow.scss';

import {MemberProps} from './MemberTable';

interface MemberTableRowProps {
	member: MemberProps;
	onSelectMemberChange: (value: MemberProps | undefined) => void;
}

export function MemberTableRow({member, onSelectMemberChange}: MemberTableRowProps) {
	const {
		email,
		name,
		role,
		dateCreated,
		lastLoginDate,
		userId
	} = member;

	return (
		<ClayTable.Row>
			<ClayTable.Cell>
				<div className="member-table-row-name-container" onClick={() => onSelectMemberChange(member)}>
					<img
						alt="Member Image"
						className="member-table-row-name-logo"
						// src={image}
					/>

					<span className="member-table-row-name-text">
						{name}
					</span>
				</div>
			</ClayTable.Cell>

			<ClayTable.Cell>
				<span className="member-table-row-text">{email}</span>
			</ClayTable.Cell>

			<ClayTable.Cell>
				<span className="member-table-row-text">{role}</span>
			</ClayTable.Cell>
		</ClayTable.Row>
	);
}
