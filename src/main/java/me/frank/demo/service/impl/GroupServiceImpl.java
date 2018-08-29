package me.frank.demo.service.impl;

import me.frank.demo.entity.Group;
import me.frank.demo.repo.GroupRepo;
import me.frank.demo.repo.Repository;
import me.frank.demo.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author 王明哲
 */
@Service
public class GroupServiceImpl implements GroupService {
    private static final long COMMON_USER_GROUP_ID = 2;
    private final GroupRepo repo;

    public GroupServiceImpl(GroupRepo repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Group> findCommonUserGroup() {
        return repo.findById(COMMON_USER_GROUP_ID);
    }

    @Override
    public Repository<Group, Long> getRepo() {
        return repo;
    }
}
