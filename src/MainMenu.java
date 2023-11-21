import java.util.ArrayList;

public class MainMenu extends AMenu{
    private ArrayList<String> movieData = new ArrayList<>();
    private ArrayList<String> serieData = new ArrayList<>();
    private ArrayList<AMedia> listOfMedias = new ArrayList<>();
    private ArrayList<AMedia> listOfSeries = new ArrayList<>();
    private ArrayList<AMedia> listOfTitles = new ArrayList<>();

    private ArrayList<AMedia> mediasByCategory;
    private User user;
    private List watchedList;
    private List savedList;

    public MainMenu(User user) {
        this.user = user;
        watchedList = user.getList("watched");
        savedList = user.getList("saved");
    }

    @Override
    void display() {
        textUI.displayMessage("\nWelcome " + user.getUsername()+"!");
        boolean chooseOption = true;
        while (chooseOption) {
            textUI.displayMessage("""

                    MAIN MENU:
                    1. Search by title
                    2. Search by category
                    3. See list of watched Series And Movies
                    4. See list of favorites
                    5. Log out
                    """);

            String choice = textUI.getInput("Choose option: ");

            try {
                int menuOption = Integer.parseInt(choice);

                switch (menuOption) {
                    case 1:
                        String choiceMedia = "";
                        boolean existInList = false;
                        while(!existInList) {
                            choiceMedia = textUI.getInput("Type in media Title: ");
                            if (doesTitleExistInMedia(choiceMedia)) {
                                existInList = true;
                            } else {
                                textUI.displayMessage("Media doesn't exist in our list");
                            }
                        }
                        displayAllMedia(searchMediaByTitle(choiceMedia));
                        String choiceTitle = textUI.getInput("Choose a media from the list!");
                        int menuOpt3 = Integer.parseInt(choiceTitle);
                        ArrayList<AMedia> listOfChosenTitle = searchMediaByTitle(choiceMedia);
                        AMedia chosenTitle = listOfChosenTitle.get(menuOpt3-1);
                        if(Integer.parseInt(choiceTitle) > 0 &&  Integer.parseInt(choiceTitle) <= listOfTitles.size()) {
                            textUI.displayMessage("What do you want to do with '" + chosenTitle.getTitle() + "'");
                            textUI.displayMessage("""
                                    Choose option:
                                    1. Play media
                                    2. Save media to Favorites
                                    3. Remove media from Favorites
                                                                
                                    """);
                            String choice2 = textUI.getInput("choose an option: ");
                            try {
                                int menuOpt1 = Integer.parseInt(choice2);
                                switch (menuOpt1) {
                                    case 1:
                                        textUI.displayMessage(chosenTitle.getTitle() + " is now playing");
                                        addToList(watchedList, chosenTitle);
                                        break;
                                    case 2:
                                        textUI.displayMessage(chosenTitle.getTitle() + " has been added to your SavedList");
                                        addToList(savedList, chosenTitle);
                                        break;
                                    case 3:
                                        textUI.displayMessage(chosenTitle.getTitle() + " has been removed from your SaveList");
                                        deleteFromList(savedList, chosenTitle);
                                        break;
                                    default:
                                        textUI.displayMessage("Not a Menu option");
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                textUI.displayMessage("choose a number!");
                            }
                        }
                        break;
                    case 2:
                        textUI.displayMessage("""
                    
                        1. Drama
                        2. Crime
                        3. War
                        4. Family
                        5. Sci-Fi
                        6. Romance
                        7. Adventure
                        8. Biography
                        9. Sport
                        10. History
                        11. Mystery
                        12. Thriller
                        13. Horror
                        14. Action
                        15. Western
                        16. Musical
                        17. Fantasy
                        18. Comedy
                        19. Music
                        """);
                        String choice1 = textUI.getInput("Choose an option");
                        try {
                            int choice1Int = Integer.parseInt(choice1);
                            switch (choice1Int) {
                                case 1:
                                    displayMediaByCategory(searchMediaByCategory("Drama"), "Drama");
                                    break;
                                case 2:
                                    displayMediaByCategory(searchMediaByCategory("Crime"), "Crime");
                                    break;
                                case 3:
                                    displayMediaByCategory(searchMediaByCategory("War"), "War");
                                    break;
                                case 4:
                                    displayMediaByCategory(searchMediaByCategory("Family"), "Family");
                                    break;
                                case 5:
                                    displayMediaByCategory(searchMediaByCategory("Sci-Fi"), "Sci-Fi");
                                    break;
                                case 6:
                                    displayMediaByCategory(searchMediaByCategory("Romance"), "Romance");
                                    break;
                                case 7:
                                    displayMediaByCategory(searchMediaByCategory("Adventure"), "Adventure");
                                    break;
                                case 8:
                                    displayMediaByCategory(searchMediaByCategory("Biography"), "Biography");
                                    break;
                                case 9:
                                    displayMediaByCategory(searchMediaByCategory("Sport"), "Sport");
                                    break;
                                case 10:
                                    displayMediaByCategory(searchMediaByCategory("History"), "History");
                                    break;
                                case 11:
                                    displayMediaByCategory(searchMediaByCategory("Mystery"), "Mystery");
                                    break;
                                case 12:
                                    displayMediaByCategory(searchMediaByCategory("Thriller"), "Thriller");
                                    break;
                                case 13:
                                    displayMediaByCategory(searchMediaByCategory("Horror"), "Horror");
                                    break;
                                case 14:
                                    displayMediaByCategory(searchMediaByCategory("Action"), "Action");
                                    break;
                                case 15:
                                    displayMediaByCategory(searchMediaByCategory("Western"), "Western");
                                    break;
                                case 16:
                                    displayMediaByCategory(searchMediaByCategory("Musical"), "Musical");
                                    break;
                                case 17:
                                    displayMediaByCategory(searchMediaByCategory("Fantasy"), "Fantasy");
                                    break;
                                case 18:
                                    displayMediaByCategory(searchMediaByCategory("Comedy"), "Comedy");
                                    break;
                                case 19:
                                    displayMediaByCategory(searchMediaByCategory("Music"), "Music");
                                    break;
                                default:
                                    textUI.displayMessage("Not a menu option!");
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            textUI.displayMessage("Choose a number!");
                        }
                        String choiceCategory = textUI.getInput("Choose a media from the list!");
                        int menuOpt4 = Integer.parseInt(choiceCategory);
                        AMedia chosenMedia = mediasByCategory.get(menuOpt4-1);
                        if(Integer.parseInt(choiceCategory) > 0 &&  Integer.parseInt(choiceCategory) <= mediasByCategory.size()) {
                            textUI.displayMessage("What do you want to do with '"+chosenMedia.getTitle()+"'");
                            textUI.displayMessage("""
                                                                        
                                    1. Play media
                                    2. Save media to Favorites
                                    3. Remove media from Favorites
                                                                       
                                    """);
                            String choiceMediaCategory = textUI.getInput("Choose option");
                            try {
                                int menuOpt2 = Integer.parseInt(choiceMediaCategory);
                                switch (menuOpt2) {
                                    case 1:
                                        textUI.displayMessage(chosenMedia.getTitle()+" is now playing");
                                        addToList(watchedList, chosenMedia);
                                        break;
                                    case 2:
                                        textUI.displayMessage(chosenMedia.getTitle()+" has been added to your SavedList");
                                        addToList(savedList, chosenMedia);
                                        break;
                                    case 3:
                                        textUI.displayMessage(chosenMedia.getTitle()+" has been removed from your SaveList");
                                        deleteFromList(savedList, chosenMedia);
                                        break;
                                    default:
                                        textUI.displayMessage("Not a Menu option");
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                textUI.displayMessage("Choose a number!");
                            }
                        }
                        break;
                    case 3:
                        // see list of watched Series And Movies
                        textUI.displayMessage("WatchList Of Medias\n");
                        showList(watchedList);
                        break;
                    case 4:
                        textUI.displayMessage("List Of Saved Medias\n");
                        //displayAllMedia(List.savedList);
                        //See list of favorites
                        showList(savedList);
                        break;
                    case 5:
                        //logout
                        user = null;
                        chooseOption = false;
                        break;
                    default:
                        textUI.displayMessage("Not a Menu option!");
                        break;
                }
            } catch (NumberFormatException e) {
                textUI.displayMessage("Choose a number!");
            }
        }
    }

    private void addToList(List list, AMedia media) {
        boolean isAdded = list.addMedia(media);
        if(isAdded) {
            textUI.displayMessage("Added to the list.");
        } else {
            textUI.displayMessage("Already on the list!");
        }
    }
    private void deleteFromList(List list, AMedia media) {
        boolean isRemoved = list.deleteMedia(media);
        if(isRemoved) {
            textUI.displayMessage("Removed from the list.");
        } else {
            textUI.displayMessage("Not on the list!");
        }
    }

    private void showList(List list) {
        ArrayList<AMedia> medias = list.getMediaList();

        int counter = 1;
        for (AMedia m: medias) {
            textUI.displayMessage(counter + ": (" + m.getMediaType() + ") " + m.getTitle() + "; " + m.getReleaseYear());
            counter++;
        }
    }

    ArrayList<AMedia> getListOfMedias() {
        movieData = fileIO.readMediaData("data/100bedstefilm.txt");
        serieData = fileIO.readMediaData( "data/100bedsteserier.txt");

        listOfMedias = fileIO.getListOfMovies(movieData);
        listOfSeries = fileIO.getListOfSeries(serieData);
        listOfMedias.addAll(listOfSeries);
        return listOfMedias;
    }


    public ArrayList<AMedia> searchMediaByCategory(String category) {
        ArrayList<AMedia> medias = getListOfMedias();
        mediasByCategory = new ArrayList<>();
        for (AMedia m : medias) {
            String categories = m.getCategories();
            if (categories.toLowerCase().contains(category.toLowerCase())) {
                mediasByCategory.add(m);
            }
        }
        return mediasByCategory;
    }

    public ArrayList<AMedia> searchMediaByTitle(String title) {
        ArrayList<AMedia> medias = getListOfMedias();
        listOfTitles = new ArrayList<>();
        for(AMedia m: medias) {
            if(m.getTitle().equalsIgnoreCase(title)) {
                listOfTitles.add(m);
            }
        }
        return listOfTitles;
    }
    boolean doesTitleExistInMedia(String chooseMedia) {
        ArrayList<AMedia> listOfMedias = getListOfMedias();
        for(AMedia m: listOfMedias) {
            if(m.getTitle().equalsIgnoreCase(chooseMedia)) {
                return true;
            }
        }
        return false;
    }
    void displayMediaByCategory(ArrayList<AMedia> listOfMedias, String category) {
        int count = 0;
        textUI.displayMessage("I have found "+listOfMedias.size()+" medias with "+category+" as category");
        for(AMedia m: listOfMedias) {
            count++;
            textUI.displayMessage(count+"\n"+m.toString());
        }

    }

    void displayAllMedia(ArrayList<AMedia> listOfMedias) {
        int count = 0;
        for(AMedia m: listOfMedias) {
            count++;
            System.out.println(count+"\n"+m.toString());
        }
    }

}

