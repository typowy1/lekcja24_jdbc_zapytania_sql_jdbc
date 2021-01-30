import java.sql.*;

public class EmployeeReader {

    //  https://github.com/typowy1/lekcja24_jdbc_zapytania_sql_jdbc
    //https://javastart.pl/kurs/technologie-webowe/web-bazy-java/lekcja/web-sql-java

    public static void main(String[] args) throws SQLException {
        // try with resources - dzięki takiemu zastosowaniu nie musimy uzywać close()
        //zapytanie odczytujące
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?serverTimezone=UTC",
                        "root", "zioom1");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name, hire_date FROM employees LIMIT 10");
        ) {
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date hireDate = resultSet.getDate("hire_date");
                System.out.printf("%s %s - data zatrudnienia: %s\n", firstName, lastName, hireDate.toString());
            }
        }

        // zapytanie aktualizujące
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/employees?serverTimezone=UTC", "root", "zioom1");
                Statement statement = connection.createStatement();
        ) {
            int updatedRows = statement.executeUpdate("UPDATE employees SET first_name = 'Peter' WHERE emp_no = 10001");
            System.out.println("Liczba zaktualizowanych wierszy: " + updatedRows);
        }

//        Potencjalne problemy
//
//Przy uruchamianiu programu mogą pojawić się różne problemy, które najczęściej można rozwiązać poprzez dodanie dodatkowych parametrów do adresu URL bazy danych.
//
//Błąd The server time zone value 'CEST' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the 'serverTimezone' configuration property) to use a more specifc time zone value if you want to utilize time zone support.
//
//Do adresu URL należy dodać parametr określający strefę czasową, np. jdbc:mysql://localhost:3306/employees?serverTimezone=UTC
//
//Błąd Public Key Retrieval is not allowed.
//
//Do adresu URL należy dodać dodatkowy parametr jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true
//
//Jeżeli występuje kilka błędów jednocześnie, to adresu bazy można dodać kilka parametrów, np. jdbc:mysql://localhost:3306/employees?serverTimezone=UTC&allowPublicKeyRetrieval=true
//
//Błąd Access denied for user 'roots'@'localhost' (using password: YES)
//
//Wprowadzona nazwa użytkownika lub hasło nie są poprawne. Sprawdź, czy nie ma w nich literówki.

        // pom
        //<?xml version="1.0" encoding="UTF-8"?>
        //<project xmlns="http://maven.apache.org/POM/4.0.0"
        //         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        //         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
        //         http://maven.apache.org/xsd/maven-4.0.0.xsd">
        //    <modelVersion>4.0.0</modelVersion>
        //
        //    <groupId>pl.javastart</groupId>
        //    <artifactId>jdbc</artifactId>
        //    <version>1.0-SNAPSHOT</version>
        //
        //    <properties>
        //        <maven.compiler.source>14</maven.compiler.source>
        //        <maven.compiler.target>14</maven.compiler.target>
        //    </properties>
        //
        //    <dependencies>
        //        <dependency>
        //            <groupId>mysql</groupId>
        //            <artifactId>mysql-connector-java</artifactId>
        //            <version>8.0.21</version>
        //        </dependency>
        //    </dependencies>
        //</project>
    }
}
