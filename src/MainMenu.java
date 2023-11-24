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
                        wrongOption();
                        break;
                }
            } catch (NumberFormatException e) {
                errorNotANumber();
            }
        }
    }

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
                        wrongOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
        return action;
    }


    private ArrayList<AMedia> searchMediaByTitle(String title) {
        ArrayList<AMedia> listOfTitles = new ArrayList<>();
        for (AMedia media : mediaLibrary) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                listOfTitles.add(media);
            }
        }
        return listOfTitles;
    }


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
                        wrongOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
        return action;
    }

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
                        wrongOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
        return action;
    }

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
                        wrongOption();
                    }
                } catch (NumberFormatException e) {
                    errorNotANumber();
                }
            }
        }
    }

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