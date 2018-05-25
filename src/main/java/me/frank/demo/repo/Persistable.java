package me.frank.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 王明哲
 */
public interface Persistable<T, ID> {
    /**
     * 保存到数据库
     *
     * @param repository 持久操作接口对象
     * @return 持久化实体类对象
     */
    @SuppressWarnings({"all"})
    default T saveBy(JpaRepository<T, ID> repository) {
        return repository.save((T) this);
    }
}
