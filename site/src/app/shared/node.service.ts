import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { ICreateDir } from "./create-dir.interface";
import { ICreateFile } from "./create-file.interface";
import { INode } from "./node.interface";

@Injectable()
export class NodeService {
    private readonly baseUrl = "http://localhost:9987/api/nodes";

    constructor(private http: HttpClient) {
    }

    public getRootNode(): Observable<INode> {
        return this.http.get<INode>(
            `${this.baseUrl}/root`);
    }

    public getNode(nodeId: string): Observable<INode> {
        return this.http.get<INode>(
            `${this.baseUrl}/${nodeId}`);
    }

    public getChildren(parentNodeId: string): Observable<INode[]> {
        return this.http.get<INode[]>(
            `${this.baseUrl}/${parentNodeId}/children`);
    }

    public createDir(dto: ICreateDir): Observable<string> {
        return this.http.post<string>(`${this.baseUrl}/dir`, dto);
    }

    public createFile(dto: ICreateFile): Observable<string> {
        return this.http.post<string>(`${this.baseUrl}/file`, dto);
    }

    public delete(nodeId: string): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${nodeId}/delete`);
    }
}