package com.Program;


import javafx.beans.property.SimpleStringProperty;

public class Student {


    private final SimpleStringProperty firstName;
    private final SimpleStringProperty secondName;
    private final SimpleStringProperty group;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty book;
    private final SimpleStringProperty author;
    private final SimpleStringProperty idBook;

    public Student(String firstName,String secondName,String group,String gender,String book,String author,String idBook) {
        this.firstName = new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.group = new SimpleStringProperty(group);
        this.gender = new SimpleStringProperty(gender);
        this.book = new SimpleStringProperty(book);
        this.author = new SimpleStringProperty(author);
        this.idBook = new SimpleStringProperty(idBook);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getGroup() {
        return group.get();
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getIdBook() {
        return idBook.get();
    }

    public SimpleStringProperty idBookProperty() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook.set(idBook);
    }

    public String getBook() {
        return book.get();
    }

    public SimpleStringProperty bookProperty() {
        return book;
    }

    public void setBook(String book) {
        this.book.set(book);
    }

    @Override
    public String toString() {
        return getFirstName() + ";" + getSecondName() + ";" + getGroup() + ";" + getGender() + ";" + getBook() + ";" + getAuthor() + ";" + getIdBook();
    }

}
