package com.qwasar.myspringportfolio.config;

import com.qwasar.myspringportfolio.models.Account;
import com.qwasar.myspringportfolio.models.Project;
import com.qwasar.myspringportfolio.repositories.AuthorityRepository;
import com.qwasar.myspringportfolio.services.AccountService;
import com.qwasar.myspringportfolio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class setData implements CommandLineRunner {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public  void run (String... args) {
        List<Project> projects = projectService.getAll();

        if (projects.size() == 0) {

            com.qwasar.myspringportfolio.models.Authority user = new com.qwasar.myspringportfolio.models.Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            com.qwasar.myspringportfolio.models.Authority admin = new com.qwasar.myspringportfolio.models.Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account accountUser = new Account();
            Account accountAdmin = new Account();

            accountUser.setUsername("theazimjon");
            accountUser.setEmail("theazimjon@gmail.com");
            accountUser.setPassword("55555");
            Set<com.qwasar.myspringportfolio.models.Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            accountUser.setAuthorities(authorities1);

            accountAdmin.setUsername("azim");
            accountAdmin.setEmail("azimjon@gmail.com");
            accountAdmin.setPassword("4444");
            Set<com.qwasar.myspringportfolio.models.Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            accountAdmin.setAuthorities(authorities2);

            accountService.save(accountUser);
            accountService.save(accountAdmin);

            Project newProject = new Project();
            newProject.setName("My Pokemon App");
            newProject.setDescription("This project explore pokemons which is in poke api. Qwasar.io Season 3 Frontend project");
            newProject.setUrl("https://my-pokemon-theazimjon.netlify.app/");
            newProject.setImgUrl("https://i.postimg.cc/7LhJxNBg/Screen-Shot-2022-08-04-at-01-41-04-1.webp");
            newProject.setAccount(accountAdmin);

            Project newProject2 = new Project();
            newProject2.setName("My Portfolio");
            newProject2.setDescription("I created this just practise");
            newProject2.setUrl("https://theazimjon.netlify.app/");
            newProject2.setImgUrl("https://theazimjon.netlify.app/images/car_in_les.jpg");
            newProject2.setAccount(accountAdmin);

            Project newProject3 = new Project();
            newProject3.setName("My tetris");
            newProject3.setDescription("Full Stack Season 2 project\n" +
                    "You can play that famous tetris game:-)");
            newProject3.setUrl("https://my-tetris-theazimjon.netlify.app/");
            newProject3.setImgUrl("https://my-tetris-theazimjon.netlify.app/images/tetrislogo.gif");
            newProject3.setAccount(accountAdmin);

            Project newProject4 = new Project();
            newProject4.setName("My weather app");
            newProject4.setDescription("I created this just practise");
            newProject4.setUrl("https://weather-theazimjon.netlify.app");
            newProject4.setImgUrl("https://weather-theazimjon.netlify.app/pexels-johannes-plenio-1102915.jpg");
            newProject4.setAccount(accountAdmin);

            projectService.save(newProject);
            projectService.save(newProject2);
            projectService.save(newProject3);
            projectService.save(newProject4);
        }
    }
}
