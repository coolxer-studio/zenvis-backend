package com.coolxer.dao.mysql.repository;

import com.coolxer.dao.mysql.entity.OperationBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationBoardRepository extends JpaRepository<OperationBoard, Long>, JpaSpecificationExecutor<OperationBoard> {


} 