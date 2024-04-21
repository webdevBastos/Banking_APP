package com.appli_banking.bankingAPP.repositories;

import com.appli_banking.bankingAPP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByFirstnameContaining(String firstname);

    List<User> findAllByAccount_Iban(String iban);
    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> sBynIban(String iban);
    @Query(value = "select * from utilisateurs u inner join compte a on u.id = a.id_utilisateur where a.iban = :iban ", nativeQuery = true)
    List<User> chercherIban(String iban);


    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);
    List<User> findAll();
    @Query("from User u where u.lastname = 'haithem' and u.firstname = :fn")
    List<User> searchByFirstName(String fn);
    //la meme chose que :
    List<User> findAllByFirstname(String firstname);
    //Optional pour indiquer s'il existe sinon null
    Optional<User> findByEmail(String email);
}
