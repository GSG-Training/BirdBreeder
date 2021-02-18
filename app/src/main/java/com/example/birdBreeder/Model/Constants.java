package com.example.birdBreeder.Model;

public interface Constants {
    //DATABASE
    String DB_NAME = "bird_breeder_db";
    int DB_VERSION = 2;
    //Tables
    String BIRDS_TABLE = "birds";
    String SPECIES_TABLE = "species";
    String NOTIFICATION_TABLE = "notifications";
    String EGG_TABLE = "eggs";
    String MATING_TABLE = "matings";
    //*******************************************************************************************************************//
    //SPECIES TYPES
    int PET_BIRDS = 11;
    int WILD_BIRDS = 12;
    //BIRD STATUS
    int SICK = -1;
    int SOLD = 0;
    //BIRD STATUS/ MATING STATUS
    int IN_REST = 2;
    int IN_BREEDING = 1;
    //MATING STATUS
    int SEPARATED = -2;
    //BIRD GENDER in SQLite the boolean replaced by 0 and 1
    boolean MALE = true;
    boolean FEMALE = false;
    //NOTIFICATION STATUS
    int VIEWED = 201;
    int UN_VIEWED = 200;
    //Bird Adapter
    boolean FOR_SALE = true;
    boolean FOR_SHOW = false;
    //egg status
    int FAILED_INCUBATING = 20;
    int INCUBATED = 21;
    int UNFERTILIZED = 22;
    int HATCHED_OK = 23;
    int PAGE_ITEM_COUNT = 10;
    //*******************************************************************************************************************//
    //Bird Profile
    String BIRD_ID = "bird_id";
    String BIRD_ACTION = "bird_action";
    int NEW_BIRD = 1;
    int SHOW_BIRD = 2;
    //Mating Profile
    String MATING_ID = "mating_id";
    String MATING_ACTION = "mating_action";
    int NEW_MATING = 3;
    int EDIT_MATING = 4;
    //Egg Profile
    String EGG_ID = "egg_id";
    String EGG_ACTION = "egg_action";
    int NEW_EGG = 5;
    int EDIT_EGG = 6;
    int PICK_IMAGE = 410;
    String DATE_PICKER = "datePicker";
    int PICKER_REQUEST = 0 ;
    String EGG_SPECIES = "eggSpecies";
}
