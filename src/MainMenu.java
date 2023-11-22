import java.util.ArrayList;

public class MainMenu extends AMenu{
    private String[] category = {"Talk-show", "Documentary", "Crime", "Drama", "Action", "Adventure", "Drama", "Comedy", "Fantasy", "Animation", "Horror", "Sci-fi", "War", "Thriller", "Mystery", "Biography", "History", "Family", "Western", "Romance", "Sport"};
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
                        int menuOption2 = Integer.parseInt(choiceTitle);
                        ArrayList<AMedia> listOfChosenTitle = searchMediaByTitle(choiceMedia);
                        AMedia chosenTitle = listOfChosenTitle.get(menuOption2-1);
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
                            AMedia chosenMedia = mediasByCategory.get(menuOption5 - 1);
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
                        showList(watchedList);
                        break;
                    case 4:
                        textUI.displayMessage("List Of Saved Medias\n");
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
    private void playAddOrRemoveMenu(String choice, AMedia chosenMedia) {
        int menuOption = Integer.parseInt(choice);
        switch (menuOption) {
            case 1:
                textUI.displayMessage(chosenMedia.getTitle() + " is now playing");
                addToList(watchedList, chosenMedia, chosenMedia.getTitle());
                break;
            case 2:
                addToList(savedList, chosenMedia, chosenMedia.getTitle());
                break;
            case 3:
                deleteFromList(savedList, chosenMedia, chosenMedia.getTitle());
                break;
            default:
                textUI.displayMessage("Not a Menu option");
                break;
        }
    }
    private void addToList(List list, AMedia media, String chosenMedia) {
        boolean isAdded = list.addMedia(media);
        if(isAdded) {
            textUI.displayMessage(chosenMedia + " has been added to your SaveList");
        } else {
            textUI.displayMessage(chosenMedia + " is already on the list!");
        }
    }
    private void deleteFromList(List list, AMedia media, String chosenMedia) {
        boolean isRemoved = list.deleteMedia(media);
        if(isRemoved) {
            textUI.displayMessage( chosenMedia + " has been removed from the list.");
        } else {
            textUI.displayMessage( chosenMedia + " is not on the list!");
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

