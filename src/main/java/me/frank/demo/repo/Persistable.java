package me.frank.demo.repo;

import me.frank.demo.service.RepoAvailable;

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
    default T saveBy(RepoAvailable<T, ID> repoAvailable) {
        return repoAvailable.getRepo().save((T) this);
    }
}
