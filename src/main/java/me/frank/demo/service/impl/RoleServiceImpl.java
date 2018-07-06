package me.frank.demo.service.impl;

import me.frank.demo.entity.Role;
import me.frank.demo.repo.RoleRepo;
import me.frank.demo.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static me.frank.demo.exception.ServiceException.INTERNAL_SERVICE_ERROR;

/**
 * @author 王明哲
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    private static final long DEFAULT_ROLE_ID = 2;
    private final RoleRepo repo;

    public RoleServiceImpl(RoleRepo repo) {
        this.repo = repo;
    }

    @Override
    public Role findDefaultRole() {
        return repo.findById(DEFAULT_ROLE_ID).orElseThrow(() -> INTERNAL_SERVICE_ERROR);
    }
}
