import { Schema, SchemaProperty, ApiErrorSet, Link, SchemaPropertySet } from "../api/MonopolyApi";
import { isArray, isObject, mapResourceAttribute, resolveSchema, ResourceAttribute } from "../api/ResourceFunctions";
import { FieldGroupRow } from "./FieldGroupRow";

export const mapFieldGroupRow = (
    parentKey: string,
    index: string | number,
    schema: Schema,
    schemaProperty: SchemaProperty,
    data: { [name: string]: any; },
    attributeErrors: ApiErrorSet,
    clickHandler: (link: Link) => void,
    dataChangeHandler: (name: string, value: any) => void): React.ReactNode => {

    const propSchema: Schema = resolveSchema(schema, schemaProperty);
    const schemaProperties: SchemaPropertySet = propSchema.properties ? propSchema.properties : {};

    const attributes: ResourceAttribute[] = Object.keys(schemaProperties)
        .filter(key => !isObject(data[key]) && !isArray(data[key]))
        .map(key => mapResourceAttribute(key, schema, propSchema, schemaProperties[key], data, attributeErrors, parentKey));

    const rows: React.ReactNode[] = Object.keys(schemaProperties)
        .filter(key => isObject(data[key]))
        .map((key, i) => mapFieldGroupRow(key, i, schema, schemaProperties[key], data[key],
            attributeErrors, clickHandler, dataChangeHandler));

    const arrayAttributes: ResourceAttribute[] = Object.keys(schemaProperties)
        .filter(key => isArray(data[key]))
        .map(key => mapResourceAttribute(key, schema, propSchema, schemaProperties[key], data, {}));

    return <>
        {attributes.length > 0 && <FieldGroupRow key={parentKey + 'row-' + index}
            attributes={attributes}
            name={parentKey}
            title={schemaProperty.title}
            data={data}
            clickHandler={clickHandler}
            dataChangeHandler={dataChangeHandler} />}

        {rows}

        {arrayAttributes.length > 0 &&
                <FieldGroupRow key={parentKey + 'items-row'}
                    attributes={arrayAttributes}
                    clickHandler={clickHandler}
                    dataChangeHandler={dataChangeHandler} />}
    </>
}