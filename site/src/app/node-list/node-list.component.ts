import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { INode } from '../shared/node.interface';
import { NodeService } from '../shared/node.service';

@Component({
    selector: 'node-list',
    templateUrl: './node-list.component.html',
    styleUrls: ['./node-list.component.scss']
})
export class NodeListComponent implements OnInit {
    public node: INode | null = null;
    public nodes: INode[] | null = null;

    public constructor(
        private nodeService: NodeService,
        private route: ActivatedRoute) {
    }

    public ngOnInit(): void {
        const nodeId: string = this.getNodeId();
        const nodeObservable: Observable<INode> = nodeId ? this.nodeService.getNode(nodeId) : this.nodeService.getRootNode();
        nodeObservable.subscribe((node: INode) => {
            this.node = node;
            this.loadNodes();
        });
    }

    public createDir(name: string): void {
        if (!this.node) {
            return;
        }
        this.nodeService.createDir({
            name: name,
            parentId: this.node.id
        }).subscribe(() => {
            this.loadNodes();
        });
    }

    public goToNode(node: INode): void {
        this.node = node;
        this.loadNodes();
    }

    private loadNodes(): void {
        if (this.node == null) {
            return;
        }

        this.nodeService.getChildren(this.node.id)
            .subscribe((nodes: INode[]) => {
                this.nodes = nodes;
            });
    }


    private getNodeId(): string {
        const routeParams = this.route.snapshot.paramMap;
        return routeParams.get('nodeId') as string;
    }
}
