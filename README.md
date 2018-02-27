# Unity - proporcional discount

This is a simple application for dividing discount.

Additioonal assumptions:
- product name should be longer than 2 signs,
- all money values (price, discount) should be grater than or equals 0 with maximum 2 values after coma, only digits allowed,
- you can define up to 5 products with prices

Project structure:
- Main package src.main.java.com.paulina.kuzmicka.discount have 3 subpaskages:
  - "domain" with classes Order, Invoice, Item, Product
  - "order" with classes responsible for processing and validating order
  - "ui" with simple user interface
- Test package src.test.java.com.paulina.kuzmicka.discount.order have two classes with test suits.

How to run application:
- clone or download from repository, build, run (DiscountAppplication class)
- Please read instructions which will be printed on your screen and follow them

Tools, libraries etc:
- Java 8
- IntelliJ IDEA 2017.2.5
- Gradle 4.2.1
- springBootVersion = '1.5.9.RELEASE'
- JUnit 4.12
- Mockito 1.10.19

Hope You enjoy :)
