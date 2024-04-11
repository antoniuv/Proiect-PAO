package proiect.domain;

public interface Filterable<T>{
    Continent[] filter(Continent[] continents, T value);
}
