import java.util.ArrayList;

public class MainMenu extends AMenu {
    private final String[] categories = {"Talk-show", "Documentary", "Crime", "Drama", "Action", "Adventure", "Drama", "Comedy", "Fantasy", "Animation", "Horror", "Sci-fi", "War", "Thriller", "Mystery", "Biography", "History", "Family", "Western", "Romance", "Sport"};
    private final ArrayList<AMedia> mediaLibrary = new ArrayList<>();
    private ArrayList<AMedia> mediasByCategory;
    private User user;
    private List listOfWatchedMedia;
    private List listOfFavorites;

    public MainMenu(User user) {
        this.user = user;
        loadUserList();
        loadLibrary();
    }

    /**
     * Displays the available actions for main menu
     */
    @Override
    public void display() {
        textUI.displayMessage("\n***Welcome " + user.getUsername() + "!***");

        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("""
                                        
                    MAIN MENU:
                    1. Search by title
                    2. Search by category
                    3. See list of watched Series And Movies
                    4. See list of favorites
                    5. Log out
                    """);

            String input = chooseMenuOption();

            try {
                int menuOption = Integer.parseInt(input);

                switch (menuOption) {
                    case 1:
                        searchByTitle();
                        break;
                    case 2:
                        searchByCategory();
                        break;
                    case 3:
                        showList(listOfWatchedMedia, "watched");
                        break;
                    case 4:
                        showList(listOfFavorites, "favorite");
                        break;
                    case 5:
                        saveToDB();
                        user = null;
                        choosingAction = false;
                        break;
                    default:
                        errorNotAnOption();
                        break;
                }
            } catch (NumberFormatException e) {
                errorNotANumber();
            }
        }
    }

    /**
     * this method creates the listOfWatchedMedia and the listOfFavorites by calling the getList() method in the User-class
     */
    private void loadUserList() {
        listOfWatchedMedia = user.getList("watched");
        listOfFavorites = user.getList("favorites");

        if (listOfWatchedMedia == null) {
            textUI.displayErrorMessage("\nCould not load your watch history.");
        }

        if (listOfFavorites == null) {
            textUI.displayErrorMessage("\nCould not load your list of favorites.");
        }
    }

    /**
     * this method search for the typed in title by calling the searchMediaByTitle(input)-method. If the list is empty no title by that name has been found
     */
    private void searchByTitle() {
        boolean searching = true;
        while (searching) {
            String input = textUI.getInput("\nSearch by title: ");
            ArrayList<AMedia> mediasContainingInput = searchMediaByTitle(input);
            if (mediasContainingInput.isEmpty()) {
                textUI.displayMessage("\n***No Movies or Series with matching title.***\n");
            } else {
                String searchResult = showSearchResults(mediasContainingInput, input);
                if (searchResult.equalsIgnoreCase(exit)) {
                    searching = false;
                }
            }
        }
    }

    /**
     * Displays all search results and asks for user input
     * The String returned determines whether to continue or exit a loop
     * @param mediasContainingInput an array list of medias matching the search word
     * @param searchWord The user search input
     * @return "", "q" or "exit"
     */
    private String showSearchResults(ArrayList<AMedia> mediasContainingInput, String searchWord) {
        String action = "";
        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("\nYOU SEARCHED FOR: " + searchWord.toUpperCase());
            showMedias(mediasContainingInput);

            String input = chooseOption();
            if (input.equalsIgnoreCase(goBack)) {
                action = goBack;
                choosingAction = false;
            } else {
                try {
                    int mediaChoice = Integer.parseInt(input);
                    if (mediaChoice > 0 && mediaChoice <= mediasContainingInput.size()) {
                        String menuAction = chosenMediaMenu(mediasContainingInput.get(mediaChoice-1));

                        if (menuAction.equalsIgnoreCase(exit)) {
                            action = exit;
                            choosingAction = false;
                        }
                    } else {
                        errorNotAnOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
        return action;
    }


    /**
     *
     * @param title the typed in title in the searchByTitle-method
     * @return a ArrayList of medias with that title
     */
    private ArrayList<AMedia> searchMediaByTitle(String title) {
        ArrayList<AMedia> listOfTitles = new ArrayList<>();
        for (AMedia media : mediaLibrary) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                listOfTitles.add(media);
            }
        }
        return listOfTitles;
    }


    /**
     *Controls the flow of the "Search by Category" menu option
     */
    private void searchByCategory() {
        boolean choosingCategory = true;
        while (choosingCategory) {
            String category = chooseCategory();

            if (category.equalsIgnoreCase(goBack)) {
                choosingCategory = false;
            } else if (!category.isEmpty()) {
                boolean choosingMedia = true;
                while (choosingMedia) {
                    String choice = allMediasInCategory(category);

                    if (choice.equalsIgnoreCase(goBack)) {
                        choosingMedia = false;
                    } else if (!choice.equalsIgnoreCase(exit)) {
                        try {
                            int mediaChoice = Integer.parseInt(choice);
                            String menuAction = chosenMediaMenu(mediasByCategory.get(mediaChoice - 1));
                            if (menuAction.equalsIgnoreCase(exit)) {
                                choosingMedia = false;
                                choosingCategory = false;
                            }
                        } catch (NumberFormatException e) {
                            errorNotANumber();
                        }
                    } else {
                        choosingMedia = false;
                    }
                }
            } else {
                choosingCategory = false;
            }
        }
    }

    /**
     * Displays all available categories and asks for user input
     * The String returned determines whether to continue or exit a loop
     * @return "", "q" or chosen category
     */
    private String chooseCategory() {
        String action = "";
        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("\nALL CATEGORIES:");
            for (int i = 1; i < categories.length + 1; i++) {
                textUI.displayMessage(i + ". " + categories[i - 1]);
            }

            String input = chooseOption();
            if (input.equalsIgnoreCase(goBack)) {
                action = goBack;
                choosingAction = false;
            } else {
                try {
                    int categoryChoice = Integer.parseInt(input);

                    if (categoryChoice > 0 && categoryChoice <= (categories.length)) {
                        action = categories[categoryChoice - 1];
                        choosingAction = false;
                    } else {
                        errorNotAnOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
        return action;
    }

    /**
     * Displays all medias in the given category
     * @param category The given category
     * @return "", "q", or chosen media represented as a String
     */
    private String allMediasInCategory(String category) {
        String action = "";
        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("\nCATEGORY: " + category.toUpperCase());
            showMedias(getAllMediasByCategory(category));

            String input = chooseOption();
            if (input.equalsIgnoreCase(goBack)) {
                action = goBack;
                choosingAction = false;
            } else {
                try {
                    int mediaChoice = Integer.parseInt(input);
                    if (mediaChoice > 0 && mediaChoice <= mediasByCategory.size()) {
                        action = String.valueOf(mediaChoice);
                        choosingAction = false;
                    } else {
                        errorNotAnOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
        return action;
    }

    /**
     * Gets an array list of all medias in the given category
     * @param category The given category
     * @return An array list of media objects
     */
    public ArrayList<AMedia> getAllMediasByCategory(String category) {
        mediasByCategory = new ArrayList<>();
        for (AMedia media : mediaLibrary) {
            String categories = media.getCategories();
            if (categories.toLowerCase().contains(category.toLowerCase())) {
                mediasByCategory.add(media);
            }
        }
        return mediasByCategory;
    }

    /**
     * Displays all media in the given list object.
     * List can be a watched or favorite list type.
     * @param list The given list object
     * @param listType The type of given list object as a String
     */
    private void showList(List list, String listType) {
        ArrayList<AMedia> allWatchedMedia = list.getAllMedias();

        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("\nALL " + listType.toUpperCase() + " MOVIES AND SERIES:");
            showMedias(allWatchedMedia);

            String input = chooseOption();

            if (input.equalsIgnoreCase(goBack)) {
                choosingAction = false;
            } else {
                try {
                    int mediaChoice = Integer.parseInt(input);

                    if (mediaChoice > 0 && mediaChoice <= allWatchedMedia.size()) {
                        String menuAction = chosenMediaMenu(allWatchedMedia.get(mediaChoice-1));

                        if (menuAction.equalsIgnoreCase(exit)) {
                            choosingAction = false;
                        }
                    } else {
                        errorNotAnOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
    }

    /**
     * Displays the media menu for the given media object.
     * The String returned determines whether to continue or exit a loop
     * @param media The given media type
     * @return "", "q" or "exit"
     */
    private String chosenMediaMenu(AMedia media) {
        String action = "";
        boolean choosingAction = true;
        while (choosingAction) {
            MediaMenu mediaMenu = new MediaMenu(media, listOfWatchedMedia, listOfFavorites);
            mediaMenu.display();
            String choice = mediaMenu.getMenuChoice();
            if (choice.equalsIgnoreCase(exit)) {
                action = exit;
                choosingAction = false;
            } else if (choice.equalsIgnoreCase(goBack)) {
                action = goBack;
                choosingAction = false;
            }
        }
        return action;
    }

    /**
     * Displays each of the media object from the given array list.
     * If given array list is empty then display that.
     * @param listOfMedias The given array list of medias
     */
    private void showMedias(ArrayList<AMedia> listOfMedias) {
        if (listOfMedias.isEmpty()) {
            textUI.displayMessage("\n***Your list is empty***");
        } else {
            int number = 1;
            for (AMedia m : listOfMedias) {
                textUI.displayMessage(number + ". (" + m.getType() + ") " + m.getTitle() + "; " + m.getReleaseYear());
                number++;
            }
        }
    }

    /**
     * Saves the users watched and favorite list to the database.
     * Display errors if unable to save.
     */
    private void saveToDB() {
        if (listOfWatchedMedia != null) {
            boolean saved = listOfWatchedMedia.saveList(user);
            if (!saved) {
                textUI.displayErrorMessage("\nCould not save all your watched Movies and Series.");
            }
        }
        if (listOfFavorites != null) {
            boolean saved = listOfFavorites.saveList(user);
            if (!saved) {
                textUI.displayErrorMessage("\nCould not save all your favorite Movies and Series.");
            }
        }
    }

    /**
     * Loads all available medias that can be watched in this streaming service
     */
    private void loadLibrary() {
        ArrayList<String> movieData = fileIO.loadAllMedias("data/100bedstefilm.txt");
        ArrayList<String> seriesData = fileIO.loadAllMedias("data/100bedsteserier.txt");

        for (String s : movieData) {
            AMedia movie = new Movie(s);
            mediaLibrary.add(movie);
        }

        for (String s : seriesData) {
            AMedia series = new Series(s);
            mediaLibrary.add(series);
        }
    }
}