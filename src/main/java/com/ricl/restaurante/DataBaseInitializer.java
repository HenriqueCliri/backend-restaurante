// package com.ricl.restaurante;

// import com.ricl.restaurante.model.*;
// import com.ricl.restaurante.repository.*;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.math.BigDecimal;
// import java.util.Arrays;

// @Configuration
// public class DataBaseInitializer {

//     @Bean
//     public CommandLineRunner initDatabase(CategoryRepository categoryRepository, ProductRepository productRepository) {
//         return args -> {
//             Category mainDishes = new Category("Pratos Principais");
//             Category drinks = new Category("Bebidas");
//             Category desserts = new Category("Sobremeas");

//             categoryRepository.saveAll(Arrays.asList(mainDishes, drinks, desserts));

//             Product burger = new Product(
//                     "Hambúrguer Clássico",
//                     "Hambúrguer suculento com queijo, alface, tomate e molho especial.",
//                     new BigDecimal("29.90"),
//                     "https://images.unsplash.com/photo-1571091718767-18b5b1457add?q=80&w=1772&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                     mainDishes);

//             Product pizza = new Product(
//                     "Pizza Margherita",
//                     "Clássica pizza italiana com molho de tomate, mussarela e manjericão fresco.",
//                     new BigDecimal("45.00"),
//                     "https://images.unsplash.com/photo-1593560704563-f1a697d03290?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                     mainDishes);

//             Product soda = new Product(
//                     "Refrigerante Lata",
//                     "Lata de 350ml de refrigerante a sua escolha.",
//                     new BigDecimal("6.50"),
//                     "https://images.unsplash.com/photo-1599818826702-861f1c243886?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                     drinks);

//             Product cake = new Product(
//                     "Bolo de Chocolate",
//                     "Uma fatia generosa de bolo de chocolate com calda.",
//                     new BigDecimal("15.00"),
//                     "https://images.unsplash.com/photo-1627910901242-b040a3203998?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//                     desserts);

//             productRepository.saveAll(Arrays.asList(burger, pizza, soda, cake));
//         };
//     }
// }
