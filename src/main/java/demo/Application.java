package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class Application
{
	@Configuration
	@EnableResourceServer
	public static class ResourceServerConfig extends ResourceServerConfigurerAdapter
	{
		@Override
		public void configure(HttpSecurity http) throws Exception
		{
			http.authorizeRequests().anyRequest().permitAll();
		}
	}
	
	@Configuration
	// Uncomment the following order annotation for the test to pass:
//	@Order(2)
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter
	{
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			http.formLogin().loginPage("/login");
		}
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
