import {isArray, noop} from 'lodash';
import sub from 'string-sub';

function formatRowContent(rowContent = '', all) {
	let returnObj;

	if (isArray(rowContent)) {
		const slice = all ? rowContent.length : 3;

		const entries = rowContent.slice(0, slice);

		returnObj = entries.map(
			(contentEntry, i) => {
				contentEntry = contentEntry.length > 50 ? `${contentEntry.substring(0, 50)}...` : contentEntry;

				return <span class="row-content" key={`content-${i}`}>{contentEntry}</span>;
			}
		);
	}
	else {
		rowContent = rowContent.length > 250 ? `${rowContent.substring(0, 250)}...` : rowContent;

		returnObj = <span class="row-content">{rowContent}</span>;
	}

	return returnObj;
}

function Row({entry}) {
	const {
		disabled = '',
		file = new Map(),
		header = '',
		id,
		lastEdited,
		link,
		onClick = noop,
		reportedBy,
		reportedDate,
		rowContent,
		selected,
		smallIncidentName,
		status,
		statusCssClass,
		subHeader
	} = entry;

	const shortHeader = header.length > 50 ? `${header.substring(0, 50)}...` : header;

	return (
		<div class={`incident-row ${disabled}`}>

			<a href={link} id={id} onClick={onClick}>
				{selected &&
					<div class={`selected-${selected}`} />
				}

				{file.get('previewURL') &&
					<div class="image" style={`background-image: url(${file.get('previewURL')});`} />
				}

				<div class="row-header-container">
					<span class="row-header">{shortHeader}</span>

					<span class="row-sub-header">{subHeader}</span>

					<span class="row-reported-by">
						{reportedBy &&
							sub(Liferay.Language.get('reported-by-x-on-x'), reportedBy, reportedDate)
						}
					</span>
				</div>

				{rowContent &&
					<div class="row-body-container">
						<div class="row-body">
							{formatRowContent(rowContent, false)}
						</div>

						<div class="row-body-overflow">
							{formatRowContent(rowContent, true)}
						</div>
					</div>
				}

				<div class="row-right-container">
					<div class="row-right-header">
						<span class="row-small-incident-name">{smallIncidentName}</span>

						<span class={statusCssClass}>{status}</span>
					</div>

					<span class="row-last-edited">
						{lastEdited &&
							sub(Liferay.Language.get('last-edited-x'), lastEdited)
						}
					</span>
				</div>
			</a>
		</div>
	);
}

export default Row;