package dev.coworking.repository;

import dev.coworking.entity.WorkspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT w.* 
            FROM workspaces w 
            LEFT JOIN managers m ON m.id = w.manager_id 
            WHERE m.id = :id""")
    List<WorkspaceEntity> getListByManagerId(@Param("id") Long id);

}
