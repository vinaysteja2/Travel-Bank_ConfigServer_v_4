package com.travelbank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.travelbank.accounts.dto.AccountsContactInfoDto;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.travelbank.accounts.controller") })
@EnableJpaRepositories("com.travelbank.accounts.repository")
@EntityScan("com.travelbank.accounts.model")*/
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "TravelBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Vinay Teja",
						email = "vinayteja@gmail.com",
						url = "https://www.travelbank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.travelbank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "TravelBank Accounts microservice REST API Documentation",
				url = "https://www.travelbank.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
