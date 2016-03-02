<#assign dlFileEntryTypeModel = dataFactory.defaultDLFileEntryTypeModel>

insert into DLFileEntryType values ('${dlFileEntryTypeModel.uuid}', ${dlFileEntryTypeModel.fileEntryTypeId}, ${dlFileEntryTypeModel.groupId}, ${dlFileEntryTypeModel.companyId}, ${dlFileEntryTypeModel.userId}, '${dlFileEntryTypeModel.userName}', '${dataFactory.getDateString(dlFileEntryTypeModel.createDate)}', '${dataFactory.getDateString(dlFileEntryTypeModel.modifiedDate)}', '${dlFileEntryTypeModel.fileEntryTypeKey}', '${dlFileEntryTypeModel.name}', '${dlFileEntryTypeModel.description}', '${dataFactory.getDateString(dlFileEntryTypeModel.lastPublishDate)}');

<#assign ddmStructureModel = dataFactory.defaultDLDDMStructureModel>

<@insertDDMStructure
	_entry = ddmStructureModel
/>

<#assign ddmStructureLayoutModel = dataFactory.defaultDLDDMStructureLayoutModel>

insert into DDMStructureLayout values ('${ddmStructureLayoutModel.uuid}', ${ddmStructureLayoutModel.structureLayoutId}, ${ddmStructureLayoutModel.groupId}, ${ddmStructureLayoutModel.companyId}, ${ddmStructureLayoutModel.userId}, '${ddmStructureLayoutModel.userName}', '${dataFactory.getDateString(ddmStructureLayoutModel.createDate)}', '${dataFactory.getDateString(ddmStructureLayoutModel.modifiedDate)}', ${ddmStructureLayoutModel.structureVersionId},'${ddmStructureLayoutModel.definition}');