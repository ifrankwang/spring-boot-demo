package me.frank.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 王明哲
 */
public interface PersistableGroup<T, ID> {
    /**
     * 保存到数据库
     *
     * @param repository 持久操作接口对象
     * @return 持久化实体类对象
     */
    List<T> saveAllBy(JpaRepository<T, ID> repository);
}
