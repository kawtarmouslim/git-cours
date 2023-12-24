package dao;

import metier.User;

import java.util.List;

public interface IUserDao {
    public User insertUser(User user);
    public User selectUser(int id);
    public List< User > selectAllUsers();
    public boolean deleteUser(int id);
    public boolean updateUser(User user);
}
