package ru.filemanager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.filemanager.domain.Node;
import ru.filemanager.domain.NodeType;
import ru.filemanager.dto.*;
import ru.filemanager.repository.NodeRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
public class NodeService {
    private final NodeRepository nodeRepository;
    private final TimeService timeService;

    public NodeDto getRootNode() {
        List<Node> nodes = nodeRepository.findByParentNodeId(null);
        Node node;
        if (nodes.size() == 0) {
            node = new Node(
                    UUID.randomUUID(),
                    "root",
                    null,
                    NodeType.DIR,
                    new Date(),
                    null,
                    new ArrayList<>());

            nodeRepository.saveAndFlush(node);
        } else {
            node = nodes.get(0);
        }

        return new NodeDto(node);
    }

    public NodeDto getNode(UUID nodeId) {
        return new NodeDto(
                nodeRepository.findById(nodeId).orElseThrow(EntityNotFoundException::new)
        );
    }

    public List<NodeDto> getChildNodes(UUID parentNodeId) {
        return nodeRepository.findByParentNodeId(parentNodeId)
                .stream()
                .map(NodeDto::new)
                .toList();
    }

    public UUID createFile(CreateFileDto fileDto) {
        Node node = createNode(fileDto, NodeType.FILE);
        node.setPath(fileDto.getPath());

        return node.getNodeId();
    }

    public UUID createDir(CreateDirDto dirDto) {
        Node node = createNode(dirDto, NodeType.DIR);

        return node.getNodeId();
    }

    private Node createNode(CreateNodeDto nodeDto, NodeType nodeType) {
        List<Node> nodes = nodeRepository.findByParent_NodeIdAndNameAndType(
                nodeDto.getParentId(),
                nodeDto.getName(),
                nodeType);
        if (nodes.size() > 0) {
            throw new IllegalStateException();
        }

        Node parentNode = nodeRepository.findById(nodeDto.getParentId())
                .orElseThrow(EntityNotFoundException::new);
        Node node = new Node();
        node.setParent(parentNode);
        node.setName(nodeDto.getName());
        node.setCreationDateTime(timeService.getDate());
        node.setType(nodeType);

        node = nodeRepository.save(node);

        return node;
    }

    public void updateNode(UUID nodeId, UpdateNodeDto nodeDto) {
        Node node = nodeRepository.findById(nodeId).orElseThrow(EntityNotFoundException::new);
        node.setName(nodeDto.getName());
    }

    public void deleteNode(UUID nodeId) {
        nodeRepository.deleteById(nodeId);
    }
}
