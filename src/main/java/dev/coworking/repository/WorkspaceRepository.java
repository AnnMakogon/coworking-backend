package dev.coworking.repository;

import dev.coworking.entity.WorkspaceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {

    @Query(nativeQuery = true, value = """
            SELECT w.id, w.name, w.address, null AS description, null AS latitude, null AS longitude, null AS manager_id, null AS schedule
            FROM workspaces w 
            LEFT JOIN managers m ON m.id = w.manager_id 
            WHERE m.credential_id = :id""")
    Page<WorkspaceEntity> getListByManagerId(@Param("id") Long id, Pageable page);

    @Modifying
    @Query(nativeQuery = true, value = """
            UPDATE workspaces 
            SET latitude = :latitude, longitude = :longitude, name = :name, address = :address
            WHERE id = :id
            """)
    void updateWorkspace(@Param("address") String address,
                         @Param("latitude") Double latitude,
                         @Param("longitude") Double longitude,
                         @Param("name") String name,
                         @Param("id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = """
            DELETE FROM workspaces
            WHERE id = :id
            """)
    void myDeleteById(@Param("id") Long id);

}
