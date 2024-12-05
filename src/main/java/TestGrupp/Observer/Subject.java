// src/main/java/TestGrupp/Observer/Subject.java
package TestGrupp.Observer;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}