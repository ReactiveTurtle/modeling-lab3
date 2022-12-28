package ru.filemanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.filemanager.dto.*;
import ru.filemanager.service.NodeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("nodes")
public class NodeController {
    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("root")
    public ResponseEntity<NodeDto> getRoot() {
        return ResponseEntity.ok(nodeService.getRootNode());
    }

    @GetMapping("{nodeId}")
    public ResponseEntity<NodeDto> getNode(@PathVariable UUID nodeId) {
        return ResponseEntity.ok(nodeService.getNode(nodeId));
    }

    @GetMapping("{nodeId}/children")
    public ResponseEntity<List<NodeDto>> getChildNodes(@PathVariable UUID nodeId) {
        return ResponseEntity.ok(nodeService.getChildNodes(nodeId));
    }

    @PostMapping("file")
    public ResponseEntity<UUID> createFile(@RequestBody CreateFileDto fileDto) {
        return ResponseEntity.ok(nodeService.createFile(fileDto));
    }

    @PostMapping("dir")
    public ResponseEntity<UUID> create(@RequestBody CreateDirDto dirDto) {
        return ResponseEntity.ok(nodeService.createDir(dirDto));
    }

    @PutMapping("{nodeId}")
    public ResponseEntity<Void> updateNode(@PathVariable UUID nodeId, @RequestBody UpdateNodeDto nodeDto) {
        nodeService.updateNode(nodeId, nodeDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{nodeId}/delete")
    public ResponseEntity<Void> deleteNode(@PathVariable UUID nodeId) {
        nodeService.deleteNode(nodeId);

        return ResponseEntity.ok().build();
    }
}
