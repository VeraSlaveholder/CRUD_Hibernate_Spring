package example.services;

import example.models.Users;
import example.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findOne(int id) {
        Optional<Users> foundPerson = usersRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Users users) {
        usersRepository.save(users);
    }

    @Transactional
    public void update(int id, Users updatedUsers) {
        updatedUsers.setId(id);
        usersRepository.save(updatedUsers);
    }
    @Transactional
    public void delete(int id){
        usersRepository.deleteById(id);
    }
}
