package com.example.birdbreeder.Model;

public interface BirdBreederConstants {
    //DATABASE
    String DB_NAME = "bird_breeder_db";
    int DB_VERSION =1 ;
    //Tables
    String BIRDS_TABLE ="birds";
    String SPECIES_TABLE ="species";
    String NOTIFICATION_TABLE="notifications";
    String EGG_TABLE ="eggs";
    String MATING_TABLE ="matings";
    //SPECIES TYPES
    int PET_BIRDS = 11 ;
    int WILD_BIRDS = 12 ;
    //BIRD STATUS
    int SICK= -1 ;
    int SOLD= 0 ;
    //BIRD STATUS/ MATING STATUS
    int IN_REST= 2 ;
    int IN_BREEDING= 1 ;
    //MATING STATUS
    int SEPARATED = -2 ;
    //BIRD GENDER
    int MALE= 110 ;
    int FEMALE= 111 ;
    //NOTIFICATION STATUS
    int VIEWED= 201 ;
    int UN_VIEWED= 200 ;
    //Bird Adapter
    boolean FOR_SALE = true ;
    boolean FOR_SHOW = false;
    //egg status
    int FAILED_INCUBATING = 20 ;
    int INCUBATED = 21 ;
    int UNFERTILIZED = 22 ;
    int HATCHED_OK = 23 ;


}
