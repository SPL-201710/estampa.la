package users;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import authorization.AuthorizationFilter;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

@Configuration
@EnableJpaRepositories
public class UsersWebConfig extends WebMvcConfigurerAdapter  {

		@Bean
    AuthorizationFilter getSessionManager() {
        return new AuthorizationFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionManager())
        .addPathPatterns("/**")
        .excludePathPatterns("/users", "/users/login", "/users/sociallogin", "/users/logout", "/users/validatetoken");
    }

	  @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver());
    }
}
