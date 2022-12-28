import { NodeType } from "./node-type.enum";

export interface INode {
    readonly id: string;
    readonly name: string;
    readonly type: NodeType;
    readonly creationDateTime: Date;
}