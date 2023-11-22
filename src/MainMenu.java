import java.util.ArrayList;

public class MainMenu extends AMenu{
    private String[] category = {"Talk-show", "Documentary", "Crime", "Drama", "Action", "Adventure", "Drama", "Comedy", "Fantasy", "Animation", "Horror", "Sci-fi", "War", "Thriller", "Mystery", "Biography", "History", "Family", "Western", "Romance", "Sport"};
    private ArrayList<AMedia<?>> mediaLibrary = new ArrayList<>();
    private ArrayList<AMedia<?>> listOfTitles = new ArrayList<>();

    private ArrayList<AMedia<?>> mediasByCategory;
    private User user;
    private List listOfWatchedMedia;
    private List listOfFavorites;

    public MainMenu(User user) {
        this.user = user;
        listOfWatchedMedia = user.getList("watched");
        listOfFavorites = user.getList("favorites");
        loadLibrary();
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
                        int menuOption2 = Integer.parseInt(choiceTitle);
                        ArrayList<AMedia<?>> listOfChosenTitle = searchMediaByTitle(choiceMedia);
                        AMedia<?> chosenTitle = listOfChosenTitle.get(menuOption2-1);
                        if(Integer.parseInt(choiceTitle) > 0 &&  Integer.parseInt(choiceTitle) <= listOfTitles.size()) {
                            textUI.displayMessage("What do you want to do with '" + chosenTitle.getTitle() + "'");
                            textUI.displayMessage("""
                                    
                                    1. Play media
                                    2. Save media to Favorites
                                    3. Remove media from Favorites
                                                                
                                    """);
                            String choice2 = textUI.getInput("choose an option: ");
                            try {

                                playAddOrRemoveMenu(choice2,chosenTitle);
                            } catch (NumberFormatException e) {
                                textUI.displayMessage("choose a number!");
                            }
                        }
                        break;
                    case 2:
                        for (int i = 1; i < category.length+1; i++) {
                            textUI.displayMessage(i + ". " + category[i-1]);
                        }
                        String chooseCategory = textUI.getInput("Choose a category");
                        boolean validOption = false;
                        try {
                            int menuOption4 = Integer.parseInt(chooseCategory);
                            if(menuOption4 >= 1 && menuOption4 <= (category.length)) {
                                displayMediaByCategory(searchMediaByCategory(category[menuOption4-1]), category[menuOption4-1]);
                                validOption = true;
                            } else {
                                textUI.displayMessage("Not a menu option!");
                            }
                        } catch (NumberFormatException e) {
                            textUI.displayMessage("Choose a number!");
                        }
                        if(validOption) {
                            String choiceCategory = textUI.getInput("Choose a media from the list!");
                            int menuOption5 = Integer.parseInt(choiceCategory);
                            AMedia<?> chosenMedia = mediasByCategory.get(menuOption5 - 1);
                            if (menuOption5 > 0 && menuOption5 <= mediasByCategory.size()) {
                                textUI.displayMessage("What do you want to do with '" + chosenMedia.getTitle() + "'");
                                textUI.displayMessage("""
                                                                            
                                        1. Play media
                                        2. Save media to Favorites
                                        3. Remove media from Favorites
                                                                           
                                        """);
                                String choiceMediaCategory = textUI.getInput("Choose an option");
                                try {
                                    playAddOrRemoveMenu(choiceMediaCategory,chosenMedia);
                                } catch (NumberFormatException e) {
                                    textUI.displayMessage("Choose a number!");
                                }
                            }
                        }
                        break;
                    case 3:
                        textUI.displayMessage("WatchList Of Medias\n");
                        showList(listOfWatchedMedia);
                        break;
                    case 4:
                        textUI.displayMessage("List Of Saved Medias\n");
                        showList(listOfFavorites);
                        break;
                    case 5:
                        //logout
                        saveToDB();
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

    private void saveToDB() {
        if (listOfWatchedMedia != null) {
            listOfWatchedMedia.saveList(user);
        }

        if (listOfFavorites != null) {
            listOfFavorites.saveList(user);
        }
    }

    private void loadLibrary() {
        ArrayList<String> movieData = fileIO.loadMediaData("data/100bedstefilm.txt");
        ArrayList<String> serieData = fileIO.loadMediaData( "data/100bedsteserier.txt");

        for (String s: movieData) {
            AMedia<Movie> movie = new Movie(s);
            mediaLibrary.add(movie);
        }

        for (String s: serieData) {
            AMedia<Series> series = new Series(s);
            mediaLibrary.add(series);
        }
    }

    private void playAddOrRemoveMenu(String choice, AMedia<?> chosenMedia) {
        int menuOption = Integer.parseInt(choice);
        switch (menuOption) {
            case 1:
                if (chosenMedia.getMediaType().equalsIgnoreCase("Series")) {
                    AMedia<Series> s = (Series) chosenMedia.getMediaData();
                    ArrayList<Season> season = s.getMediaData().getSeasons();
                    int count;
                    count = 0;
                    for (Season g : season) {
                        count++;
                        System.out.println(count + ". Season " + g.getSeasonNumber() + ", " + g.getNumberOfEpisodes() + " episodes");
                    }
                    String choice2 = textUI.getInput("Choose a season!");
                    try {
                        int choice3 = Integer.parseInt(choice2);
                        if (choice3 >= 1 && choice3 <= season.size()) {
                            int numberOfEpisodes = season.get(choice3-1).getNumberOfEpisodes();
                            for (int j = 1; j <= numberOfEpisodes; j++) {
                                textUI.displayMessage(j + ". Episode ");
                            }
                            String episode = textUI.getInput("choose an episode!");
                            try {
                                int choice4 = Integer.parseInt(episode);
                                if (choice4 >= 1 && choice4 <= numberOfEpisodes) {
                                    textUI.displayMessage(chosenMedia.getTitle() + ", Season " + choice3 + ", Episode " + episode + " is now playing!");
                                } else {
                                    textUI.displayMessage("input not valid");
                                }
                            } catch (NumberFormatException e) {
                                textUI.displayMessage("Choose a number!");
                            }
                        } else {
                            textUI.displayMessage("not a valid season");
                        }
                    } catch (NumberFormatException e) {
                        textUI.displayMessage("Choose a number!");
                    }
                } else {
                    textUI.displayMessage(chosenMedia.getTitle() + " is now playing");
                }
                addToList(listOfWatchedMedia, chosenMedia, chosenMedia.getTitle());
                break;
            case 2:
                addToList(listOfFavorites, chosenMedia, chosenMedia.getTitle());
                break;
            case 3:
                deleteFromList(listOfFavorites, chosenMedia, chosenMedia.getTitle());
                break;
            default:
                textUI.displayMessage("Not a Menu option");
                break;
        }
    }

    private void addToList(List list, AMedia<?> media, String chosenMedia) {
        boolean isAdded = list.addMedia(media);
        if(isAdded) {
            textUI.displayMessage(chosenMedia + " has been added to your SaveList");
        } else {
            textUI.displayMessage(chosenMedia + " is already on the list!");
        }
    }
    private void deleteFromList(List list, AMedia<?> media, String chosenMedia) {
        boolean isRemoved = list.deleteMedia(media);
        if(isRemoved) {
            textUI.displayMessage( chosenMedia + " has been removed from the list.");
        } else {
            textUI.displayMessage( chosenMedia + " is not on the list!");
        }
    }

    private void showList(List list) {
        ArrayList<AMedia<?>> medias = list.getAllMedias();
        int counter = 1;
        for (AMedia<?> m: medias) {
            textUI.displayMessage(counter + ": (" + m.getMediaType() + ") " + m.getTitle() + "; " + m.getReleaseYear());
            counter++;
        }
    }



    public ArrayList<AMedia<?>> searchMediaByCategory(String category) {
        mediasByCategory = new ArrayList<>();
        for (AMedia<?> m : mediaLibrary) {
            String categories = m.getCategories();
            if (categories.toLowerCase().contains(category.toLowerCase())) {
                mediasByCategory.add(m);
            }
        }
        return mediasByCategory;
    }

    public ArrayList<AMedia<?>> searchMediaByTitle(String title) {
        listOfTitles = new ArrayList<>();
        for(AMedia<?> m: mediaLibrary) {
            if(m.getTitle().equalsIgnoreCase(title)) {
                listOfTitles.add(m);
            }
        }
        return listOfTitles;
    }
    boolean doesTitleExistInMedia(String chooseMedia) {
        for(AMedia<?> m: mediaLibrary) {
            if(m.getTitle().equalsIgnoreCase(chooseMedia)) {
                return true;
            }
        }
        return false;
    }
    void displayMediaByCategory(ArrayList<AMedia<?>> listOfMedias, String category) {
        int count = 0;
        textUI.displayMessage("I have found "+listOfMedias.size()+" medias with "+category+" as category");
        for(AMedia<?> m: listOfMedias) {
            count++;
            textUI.displayMessage(count+"\n"+m.toString());
        }

    }

    void displayAllMedia(ArrayList<AMedia<?>> listOfMedias) {
        int count = 0;
        for(AMedia<?> m: listOfMedias) {
            count++;
            System.out.println(count+"\n"+m.toString());
        }
    }

}

