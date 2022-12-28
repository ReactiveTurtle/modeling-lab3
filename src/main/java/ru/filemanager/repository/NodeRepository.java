package ru.filemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.filemanager.domain.Node;
import ru.filemanager.domain.NodeType;
import ru.filemanager.dto.NodeDto;

import java.util.List;
import java.util.UUID;

@Repository
public interface NodeRepository extends JpaRepository<Node, UUID> {
    List<Node> findByParentNodeId(UUID parentNodeId);
    List<Node> findByParent_NodeIdAndNameAndType(UUID nodeId, String name, NodeType type);
}
