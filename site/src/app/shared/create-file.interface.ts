import { ICreateNode } from "./create-node.interface";

export interface ICreateFile extends ICreateNode {
    readonly path: string;
}