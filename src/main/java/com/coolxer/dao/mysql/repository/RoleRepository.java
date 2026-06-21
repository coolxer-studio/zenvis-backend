package com.coolxer.dao.mysql.repository;

import com.coolxer.dao.mysql.constant.MysqlFinalTableName;
import com.coolxer.dao.mysql.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 角色数据库操作类
 */
public interface RoleRepository extends BaseRepository<Role, Integer> {

    /**
     * 查询角色
     *
     * @param id 角色id列
     * @return 角色
     */
    Role findById(Integer id);

    /**
     * 查询角色名
     *
     * @param ids 角色id列表
     * @return 角色列表
     */
    List<Role> findByIdIn(List<Integer> ids);

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 分页结果
     */
    @Query(nativeQuery = true,
            value = "SELECT a.* FROM " + MysqlFinalTableName.T_SYS_ROLE + " a WHERE " +
                    "(:name IS NULL OR a.name like concat('%',:name,'%')) " +
                    "ORDER BY a.update_time DESC",
            countQuery = "SELECT count(*) FROM " + MysqlFinalTableName.T_SYS_ROLE + " a WHERE " +
                    "(:name IS NULL OR a.name like concat('%',:name,'%')) ")
    Page<Role> findByPage(Pageable pageable, @Param("name") String name);
}
