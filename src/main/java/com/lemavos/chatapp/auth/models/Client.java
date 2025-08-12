package com.lemavos.chatapp.auth.models;

import com.lemavos.chatapp.auth.services.IdGen;

public class Client {

    private String id;
    private boolean status;
    private String name;
    private String email;
    private String phone;
    private String password;
    private transient String validateCode;

    public Client(String name, String email, String phone, String password) {
        createClient(name, email, phone, password);
    }

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

    // criar uma ui pra isso
    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public boolean getStatus() {
        return status;
    }

    // Setters
    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
