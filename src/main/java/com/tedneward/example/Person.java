package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
    count++;
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    count++;
    ssn = "";
  }

  public int count(){
    return count;
  }

  public int getAge() {
    return age;
  }
  
  public String getName() {
    return name;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setAge(int value) {
    if(value<0){
      throw new IllegalArgumentException();
    } else {
      int old = age;
      age = value;
    }
  }
  public void setName(String value) {
    if(value == null){
      throw new IllegalArgumentException();
    } else {
      String old = name;
      name = value;
    }
  }
  public void setSalary(double value) {
    double old = salary;
    salary = value;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public boolean equals(Object obj) {
    if (obj instanceof Person) {
      Person other = (Person) obj;
      return (this.name.equals(other.name) && this.age == other.age);
    }
    return false;
  }

  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return (p1.age-p2.age);
    }
  }

  public int compareTo(Person p) {
    if (p.salary - this.salary > 0){
      return 1;
    } else if (p.salary - this.salary < 0){
      return -1;
    } else {
      return 0;
    }
  }
  public static ArrayList<Person> getNewardFamily(){
    ArrayList fam = new ArrayList();
    Person a = new Person("Ted", 41, 250000.0);
    Person b = new Person("Charlotte", 43, 150000.0);
    Person c = new Person("Michael", 22, 10000.0);
    Person d = new Person("Matthew", 15, 0.0);
    fam.add(a);
    fam.add(b);
    fam.add(c);
    fam.add(d);
    return fam;
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
