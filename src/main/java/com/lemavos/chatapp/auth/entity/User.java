package com.lemavos.chatapp.auth.entity;

import com.lemavos.chatapp.auth.authservices.IdGen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;
    
    private transient String validateCode;

    public void info() {
        System.out.println("\nClient Information:");
        System.out.println("  Name: " + this.name);
        System.out.println("  Email: " + this.email);
        System.out.println("  Phone: " + this.phone);
        System.out.println("  ID: " + this.id);
        System.out.println("  Status: " + this.status);
    }

    private void createClient(
        String name,
        String email,
        String phone,
        String password
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = new IdGen().generateId();
        this.status = false;
        this.password = password;
        System.out.println("|  Client created with ID: " + this.id);
    }

    public boolean activateClient(String codeInsert) {
        if (codeInsert.equals(this.validateCode)) {
            this.status = true;
            System.out.println("|  Client activated with ID: " + this.id);
            System.out.println("+=====================================+");
            return true;
        } else {
            System.out.println("|  [!] Invalid validation code");
            System.out.println("+=====================================+");
            return false;
        }
    }
}
