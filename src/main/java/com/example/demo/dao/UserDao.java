package com.example.demo.dao;

import com.example.demo.model.Device;
import com.example.demo.model.User;
import com.example.demo.repo.UserDslRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Dao 示例
 */
@Repository
@Transactional
public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);
    private final JPAQueryFactory query;
    private final QUser qUser;
    private final QDevice qDevice;
    private final QUsersDevice qUsersDevice;
    private final UserDslRepo userRepo;

    public UserDao(
            EntityManager manager,
            UserDslRepo userRepo) {
        query = new JPAQueryFactory(manager);
        qUser = QUser.user;
        qDevice = QDevice.device;
        qUsersDevice = QUsersDevice.usersDevice;
        this.userRepo = userRepo;
    }

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    public void saveUser(User user) {
        userRepo.save(user);
    }

    /**
     * 获取所有用户信息
     *
     * @return 用户信息列表
     */
    public List<User> findUsers() {
        return userRepo.findAll();
    }

    /**
     * 根据用户名称模糊查询用户信息
     *
     * @param expression 关键词
     * @return 用户信息列表
     */
    public List<User> findUsersNameLike(String expression) {
        return (List<User>) userRepo.findAll(qUser.name.contains(expression));
    }

    /**
     * 根据用户 id 获取用户信息
     *
     * @param id 用户 id
     * @return 用户信息
     */
    public User findUserById(int id) {
        return userRepo.findById(id);
    }

    /**
     * 根据用户 id 获取带有设备列表的用户信息
     *
     * @param id 用户 id
     * @return 带有设备列表的用户信息
     */
    public User findUserWithDevicesById(int id) {
        User user = userRepo.findById(id);

        log.info(user.toString());
        log.info(user.getDevices().toString());

        return user;
    }

    /**
     * 获取所有用户信息，并带有设备列表，
     * 此方法效率并不好，不建议这么使用，
     * 此方法仅供参考 QueryDsl 的使用
     *
     * @return 带有设备列表的用户信息列表
     */
    public List<User> findUserWithDevices() {
        List<User> users = query.selectFrom(qUser)
                .fetch();

        for (User user : users) {
            List<Device> devices = query.selectFrom(qDevice)
                    .leftJoin(qUsersDevice)
                    .on(qDevice.id.eq(qUsersDevice.deviceId))
                    .leftJoin(qUser)
                    .on(qUser.id.eq(qUsersDevice.userId))
                    .where(qUser.id.eq(user.getId()))
                    .fetch();
            user.setDevices(devices);
        }

        return users;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    public List<User> findUsersByName(String name) {
        return userRepo.findByName(name);
    }

    /**
     * 用户增加设备
     *
     * @param userId 用户 id
     * @param device 设备
     */
    public void addDeviceToUser(int userId, Device device) {
        User user = userRepo.findById(userId);
        user.getDevices().add(device);
    }
}
