package me.frank.demo.service;

import me.frank.demo.repo.Repository;

/**
 * @author 王明哲
 */
public interface RepoAvailable<T, ID> {
    /**
     * 获取持久层操作接口对象
     *
     * @return 持久层操作接口对象
     */
    Repository<T, ID> getRepo();
}
