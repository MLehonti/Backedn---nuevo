//
package com.example.myapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//public class SecurityConfig {
//
//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    // BCryptPasswordEncoder Bean
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // Configuración del AuthenticationManager (Spring Security 6.x)
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    // Configuración del CORS
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Permitir tu frontend
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    // Configuración del filtro de seguridad
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Desactivar CSRF (puedes activarlo si es necesario)
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/login","/api/users/register","api/users","api/users/perfil").permitAll()  // Permitir acceso a la ruta de login
//                        .anyRequest().authenticated()  // Requerir autenticación para todas las demás rutas
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configuración de sesión
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())); // Aplicar configuración de CORS
//
//        return http.build();  // Devuelve la configuración
//    }
//}





import com.example.myapp.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;  // Inyectar el filtro JWT

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        // Agregar el origen de Vercel y localhost
//        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://fronted-nuevo.vercel.app"));        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Desactivar CSRF
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/login", "/api/users/register").permitAll()  // Permitir acceso a login y registro
//                        .requestMatchers("/api/users/all").hasRole("ADMIN")  // Solo el admin puede ver todos los usuarios
//                        .requestMatchers("/api/users/perfil").authenticated()  // Cualquier usuario autenticado puede ver su perfil
//                        .requestMatchers("/api/productos/imagen/**").permitAll()  // Permitir acceso a las imágenes sin autenticación
//                        .requestMatchers("/api/carrito/**").permitAll()
//                        .requestMatchers("/uploads/**").permitAll()  // Permitir acceso a las imágenes
//                        //.requestMatchers("/api/pagos/**").permitAll()  // Permitir acceso a los pagos
//                        .requestMatchers("/api/colores/**").permitAll() //Permitir acceso a todos
//
//                        .requestMatchers("/api/categorias/**", "/api/productos/crear", "/api/categorias/asignar-productos").permitAll()
//
//                        .requestMatchers("/api/inventario/**").permitAll() //permitir el acceso a inventario
//                        .anyRequest().permitAll()  // Requerir autenticación para todas las demás rutas
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // No usar sesiones
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
//
//        // Añadir el filtro JWT antes del filtro de autenticación
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Agregar los orígenes permitidos: localhost y Vercel
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://fronted-nuevo.vercel.app"));

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Cabeceras permitidas en las solicitudes
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin"));

        // Exponer cabeceras que pueden ser leídas en las respuestas
        configuration.setExposedHeaders(List.of("Authorization", "Content-Disposition"));

        // Permitir credenciales como cookies o tokens
        configuration.setAllowCredentials(true);

        // Aplicar la configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desactivar CSRF para evitar conflictos en solicitudes POST/PUT
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/login", "/api/users/register").permitAll()  // Permitir acceso a login y registro
                        .requestMatchers("/api/users/all").hasRole("ADMIN")  // Solo ADMIN puede ver todos los usuarios
                        .requestMatchers("/api/users/perfil").authenticated()  // Cualquier usuario autenticado puede ver su perfil
                        .requestMatchers("/api/productos/imagen/**").permitAll()  // Permitir acceso a imágenes sin autenticación
                        .requestMatchers("/api/carrito/**").permitAll()  // Permitir acceso a rutas de carrito
                        .requestMatchers("/uploads/**").permitAll()  // Permitir acceso a archivos en la carpeta uploads
                        .requestMatchers("/api/colores/**").permitAll()  // Permitir acceso a colores
                        .requestMatchers("/api/categorias/**", "/api/productos/crear", "/api/categorias/asignar-productos").permitAll()  // Permitir acceso a categorías y productos
                        .requestMatchers("/api/inventario/**").permitAll()  // Permitir acceso a inventario
                        .anyRequest().authenticated()  // Requerir autenticación para todas las demás rutas
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Configurar las sesiones como STATELESS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));  // Aplicar la configuración de CORS

        // Añadir el filtro JWT antes del filtro de autenticación de usuario
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}

