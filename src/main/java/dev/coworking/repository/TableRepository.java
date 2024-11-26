package dev.coworking.repository;

import dev.coworking.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT t.* 
            FROM tables t 
            LEFT JOIN workspaces w ON w.id = t.workspace_id 
            WHERE w.id = :id""")
    List<TableEntity> getListByWorkspace(@Param("id") Long id);
}
