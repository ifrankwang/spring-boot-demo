package com.example.demo.repo;

import com.example.demo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * 对Device表进行CRUD操作的Repo，
 * 会自动根据方法名生成查询sql，
 * 不需要implement
 */
public interface DeviceDslRepo extends JpaRepository<Device, Integer>, QuerydslPredicateExecutor<Device> {

    /**
     * 根据id获取设备信息
     *
     * @param id 设备id
     * @return 设备信息
     */
    Device findById(int id);
}
