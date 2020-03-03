package com.abzelhan.skoltech;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.spock.Testcontainers;
import spock.lang.Shared;

@ContextConfiguration(initializers = {CommonTestConfiguration.Initializer.class})
@SpringBootTest(classes = {SkoltechApplication.class})
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles({"test"})
@Testcontainers
public class CommonTestConfiguration extends Assert {

    private static final String postgreDockerImage = "postgres:10.3";

    @ClassRule
    @Shared
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(
            postgreDockerImage)
            .withDatabaseName("skoltech")
            .withStartupTimeoutSeconds(0)
            .withUsername("postgres")
            .withPassword("root");

    static class Initializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();
            TestPropertyValues.of(
                "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
