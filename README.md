# Spring Authorization example: Spring Boot + Security + Mongodb + Thymeleaf

This project shows how to work using Spring Security in a website using your database of choice, in this example, 
we will be using MongoDB. 

### Using Spring Security with web
To Enable Spring Security in a web project, you need a class attending these 2 requirements:

* Extends WebSecurityConfigurerAdapter
* Annotated with @EnableWebSecurity - Allows security for all endpoints

### WebSecurityConfigurerAdapter
Allows to customize the authorization for your web site overriding some methods:
 
* configure(HttpSecurity http) - Configure routes and authorizations required.
```
    http
        .authorizeRequests()
          .antMatchers("/css/*", "/js/*").permitAll()
          .antMatchers("/admin/**").hasRole(ROLE_ADMIN)
          .antMatchers("/user/**").hasRole(ROLE_USER)
          .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
          .permitAll()
          .and()
        .logout()
          .permitAll()
          .and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
```
* configure(AuthenticationManagerBuilder auth) - Configure from where the users are going to be retrieved.<br/><br/>
`.userDetailsService() - Specify the class implementing UserDetailsService`
`.inMemoryAuthentication() - Specify default users access and roles`
```
    auth
        .userDetailsService(customMongoSecurityService).and()
        .inMemoryAuthentication()
          .withUser("user").password("password").roles(ROLE_USER).and()
          .withUser("admin").password("1234").roles(ROLE_ADMIN)

```
Check the WebSecurityConfig class in com/github/bhrother/oauth/configuration

### UserDetailsService
To create your custom service, you need to create a class that implements UserDetailsService. This interface
offers only one method:
```
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
```
This method is used by spring security to retrieve a object that implements UserDetails, in this case:

* org.springframework.security.core.userdetails.User

Check the class CustomMongoSecurityService in com/github/bhrother/oauth/services
### About the example
 This example is using:
 
 * Spring Boot 1.5.4.RELEASE
 * Spring Framework 4.3.9.RELEASE
 * Thymeleaf 2.1.5.RELEASE
 * Embedded MongoDB 1.3.1 (jirutka)
 * Groovy
 
### Running the Example
This project contains an Embedded gradle. 
In a terminal, navigate to the project folder and run:

`./gradlew bootRun`

On the web browser, access:

`http://localhost:8080/spring-oauth`

Try any of the combinations:

* User role: user/user or usermongo/usermongo
* Admin role: admin/1234 or adminmongo/adminmongo