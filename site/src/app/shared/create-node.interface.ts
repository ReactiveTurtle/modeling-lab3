import { NodeType } from "./node-type.enum";

export interface ICreateNode {
    readonly parentId: string;
    readonly name: string;
}