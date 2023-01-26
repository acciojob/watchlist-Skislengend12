package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map <String, Movie> dbForMovie = new HashMap<>();
    Map<String, Director> dbForDirector = new HashMap<>();
    Map<String, List<String>> dbForPair = new HashMap<>();

    //1 -> for adding movies
    public String addMovie(Movie movie){
        String name = movie.getName();
        dbForMovie.put(name, movie);

        return "Movie added successfully";
    }

    //2 -> for adding director
    public String addDirector(Director director){
        String name = director.getName();
        dbForDirector.put(name, director);

        return "Director added successfully";
    }

    // 3 -> for making jodi of director and movie
    public String addMovieDirectorPair(String movie, String director){
        List<String> temp;
        if(dbForMovie.containsKey(movie) && dbForDirector.containsKey(director)) {
            if (dbForPair.containsKey(director)) {
                temp = dbForPair.get(director);
            } else {
                temp = new ArrayList<>();
            }
            temp.add(movie);
            dbForPair.put(director, temp);
            return "Pair of director and movie added successfully";
        }
        return "No such movie or director is available in the current lists";
    }

    // 4 -> for finding the movie
    public Movie getMovieByName(String name){
        return dbForMovie.get(name);
    }

    // 5 -> for finding director
    public Director getDirectorByName(String name){
        return dbForDirector.get(name);
    }

    // 6 -> for finding the all the movies by director name
    public List<String> getMoviesByDirectorName(String directorName){
        return dbForPair.get(directorName);
    }

    // 7 -> for returning the list of movies
    public List<String> findAllMovies(){
        return new ArrayList<>(dbForMovie.keySet());
    }

    // 8 -> for deleting the movies by director name
    public String deleteDirectorByName(String directorName){
        List<String> movieList;
        if(dbForDirector.containsKey(directorName)){
            movieList = dbForPair.get(directorName);
            for(String movie : movieList) {
                dbForMovie.remove(movie);
            }
        }
        dbForDirector.remove(directorName);
        return "All movies directed by " + directorName + " removed successfully";
    }

    // 9 -> deleting all the directors and movies by them
    public String deleteAllDirectors(){
        for(String name : dbForDirector.keySet()){
            for(String movie : dbForPair.get(name)){
                if(dbForMovie.containsKey(movie)) {
                    dbForMovie.remove(movie);
                }
            }
            dbForPair.remove(name);
        }
        dbForDirector.clear();

        return "All directors and movies directed by them are removed successfully";
    }


}
