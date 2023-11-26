package me.mel0102.Model.Models.User;

import me.mel0102.Model.ModelService;
import me.mel0102.Storage.StorageUtil;

import java.util.*;

public class UserService implements ModelService {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public Optional<User> find(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public void add(User user) {
        boolean exists = users.stream().anyMatch(u -> u.getId() == user.getId());

        if (exists) {
            return;
        }

        users.add(user);
    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public void load() {
        List<String> rows = StorageUtil.readTable(getTableName());

        rows.forEach(row -> {
            String[] parts = row.split(",");

            int id = Integer.parseInt(parts[0]);
            String email = parts[1];
            String name = parts[2];
            String surname = parts[3];

            users.add(new User(id, email, name, surname, new ArrayList<>()));
        });
    }

    @Override
    public void save() {
        List<String> rows = users.stream().map(User::toRow).toList();
        StorageUtil.saveToTable(rows, getTableName());
    }
}
