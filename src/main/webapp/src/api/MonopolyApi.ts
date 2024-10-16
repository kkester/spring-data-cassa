export enum HttpMethod {
  GET = "GET",
  POST = "POST",
  PUT = "PUT",
  DELETE = "DELETE"
}

export type Link = {
  href: string;
  title?: string;
  type?: string;
  $ref?: string;
  method?: HttpMethod;
}

export type SchemaProperty = {
  type: string;
  minLength?: number;
  maxLength?: number;
  title?: string;
  format?: string;
  enum?: string[];
  pattern?: string;
  readOnly?: boolean;
  properties?: SchemaPropertySet;
  items?: Schema;
  $ref?: string;
}

export type SchemaPropertySet = {
  [field: string]: SchemaProperty;
}

export type SchemaSet = {
  [schema: string]: Schema;
}

export type Schema = {
  title?: string;
  type?: string;
  $ref?: string;
  properties?: SchemaPropertySet;
  definitions?: SchemaSet;
  readOnly?: boolean;
  required?: string[];
}

export type DriveResource = {
  id: number;
  links?: {
    [name: string]: Link;
  },
  data?: {
    [name: string]: any;
  },
  schema?: Schema,
}

export type ApiError = {
  description: string;
}

export type ApiErrorSet = {
  [error: string]: ApiError;
}

export type ApiErrors = {
  code: string;
  description: string;
  errors?: ApiErrorSet;
}

export const apiHost = "http://localhost:8080";

export const getResource = async (uri: string): Promise<DriveResource> => {
  const data = await fetch(apiHost + uri, {
    method: "GET"
  });
  return data.json();
};

export const saveResource = async (uri: string, body: any): Promise<DriveResource> => {
  const data = await fetch(apiHost + uri, {
    method: "POST",
    body: body
  });
  return data.json();
};
