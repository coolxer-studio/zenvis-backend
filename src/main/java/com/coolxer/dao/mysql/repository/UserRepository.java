package com.coolxer.dao.mysql.repository;

import com.coolxer.dao.mysql.constant.MysqlFinalTableName;
import com.coolxer.dao.mysql.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 用户管理
 */
public interface UserRepository extends BaseRepository<User, Integer> {

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象
     */
    User findByEmail(String email);

    /**
     * 根据id查询用户
     *
     * @param userId 用户id
     * @return 用户对象
     */
    User findById(Integer userId);

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 分页结果
     */
    @Query(nativeQuery = true,
            value = "SELECT a.* FROM " + MysqlFinalTableName.T_SYS_USERS + " a WHERE " +
                    "(:name IS NULL OR a.name like concat('%',:name,'%')) AND " +
                    "(:email IS NULL OR a.email like concat('%',:email,'%')) " +
                    "ORDER BY a.update_time DESC",
            countQuery = "SELECT count(*) FROM " + MysqlFinalTableName.T_SYS_USERS + " a WHERE " +
                    "(:name IS NULL OR a.name like concat('%',:name,'%')) AND " +
                    "(:email IS NULL OR a.email like concat('%',:email,'%')) ")
    Page<User> findByPage(Pageable pageable, @Param("name") String name, @Param("email") String email);

}
