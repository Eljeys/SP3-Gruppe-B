import java.util.ArrayList;

public class MediaMenu extends AMenu{
    private final AMedia media;
    private final List listOfWatchedMedia;
    private final List listOfFavorites;
    private String menuChoice;
    public MediaMenu(AMedia media, List listOfWatchedMedia, List listOfFavorites) {
        this.media = media;
        this.listOfWatchedMedia = listOfWatchedMedia;
        this.listOfFavorites = listOfFavorites;
        this.menuChoice = "";
    }

    /**
     * Displays the available actions for the chosen AMedia object
     */
    @Override
    public void display() {
        textUI.displayMessage("\n"+media.toString() + "\n");
        textUI.displayMessage("""                      
                    1. Play
                    2. Save to Favorites
                    3. Remove from Favorites
                    4. Return to Main Menu""");

        String input = chooseOption();

        if (input.equalsIgnoreCase(goBack)) {
            setMenuChoice(goBack);
        } else {
            try {
                int choice = Integer.parseInt(input);
                if (choice == 4) {
                    setMenuChoice(exit);
                } else {
                    playAddOrRemoveMenu(choice, media);
                }
            } catch (NumberFormatException e) {
                errorNotANumber();
            }
        }
    }

    /**
     * Does something to the given media object depending on the choice
     * @param chosenMedia The chosen AMedia object
     * @param choice The chosen action as an int
     */
    private void playAddOrRemoveMenu(int choice, AMedia chosenMedia) {
        switch (choice) {
            case 1:
                playChosenMedia(chosenMedia);
                break;
            case 2:
                addToList(listOfFavorites, chosenMedia);
                waitToReturn();
                break;
            case 3:
                deleteFromList(listOfFavorites, chosenMedia);
                waitToReturn();
                break;
            default:
                errorNotAnOption();
                break;
        }
    }

    /**
     * Plays the chosen media object
     * @param chosenMedia The given AMedia object
     */
    private void playChosenMedia(AMedia chosenMedia) {
        if (chosenMedia.getType().equalsIgnoreCase("movie")) {
            textUI.displayMessage(chosenMedia.play());
            addToList(listOfWatchedMedia, chosenMedia);
            waitToReturn();
        } else if (chosenMedia.getType().equalsIgnoreCase("series")) {
            String chosenSeason = chooseSeason((Series) chosenMedia);
            if (chosenSeason.equalsIgnoreCase(exit)) {
                textUI.displayMessage(chosenMedia.play());
                addToList(listOfWatchedMedia, chosenMedia);
                waitToReturn();
            }
        }
    }

    /**
     * The user can choose a season to watch from the given Series object.
     * The String returned determines whether to continue or exit a loop
     * @param chosenSeries The given Series object
     * @return "", "q" or "exit"
     */
    private String chooseSeason(Series chosenSeries) {
        String action = "";
        ArrayList<Season> allSeasons = chosenSeries.getAllSeasons();

        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("\nALL SEASONS: ");
            int number = 1;
            for (Season season : allSeasons) {
                textUI.displayMessage(number + ". Season " + season.getSeasonNumber() + "; " + season.getNumberOfEpisodes() + " episodes.");
                number++;
            }

            String input = chooseOption();
            if (input.equalsIgnoreCase(goBack)) {
                action = goBack;
                choosingAction = false;
            } else {
                try {
                    int seasonNumber = Integer.parseInt(input);
                    if (seasonNumber > 0 && seasonNumber <= allSeasons.size()) {
                        String episodeChoice = chooseEpisode(allSeasons.get(seasonNumber - 1), chosenSeries.getTitle());
                        if (episodeChoice.equalsIgnoreCase(exit)) {
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
     * The user can choose an episode to watch from the given Season object
     * The String returned determines whether to continue or exit a loop
     * @param season The given Season object
     * @param title Title of the given Series object as a String
     * @return "", "q" or "exit"
     */
    private String chooseEpisode(Season season, String title) {
        String action = "";
        int numberOfEpisodes = season.getNumberOfEpisodes();

        boolean choosingAction = true;
        while (choosingAction) {
            textUI.displayMessage("\nSEASON " + season.getSeasonNumber() + " - ALL EPISODES:");
            for (int i = 1; i <= numberOfEpisodes; i++) {
                textUI.displayMessage(i + ". Episode");
            }

            String input = chooseOption();
            if (input.equalsIgnoreCase(goBack)) {
                action = goBack;
                choosingAction = false;
            } else {
                try {
                    int episodeNumber = Integer.parseInt(input);
                    if (episodeNumber > 0 && episodeNumber <= numberOfEpisodes) {
                        String watch = textUI.getInput("\nPlay \"" + title + "\" season " + season.getSeasonNumber() + ", episode " + episodeNumber + "? Y/N: ");

                        if (watch.equalsIgnoreCase(confirm)) {
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
     * Waits for the user to enter q
     */
    private void waitToReturn() {
        while (true) {
            String action = textUI.getInput("\nEnter (q) to go back: ");
            if (action.equalsIgnoreCase(goBack)) {
                break;
            }
        }
    }

    /**
     * Adds the given media object to the given list.
     * If list is of type Favorite then it displays if it's added or already on.
     * @param list A given List object
     * @param media A given AMedia object
     */
    private void addToList(List list, AMedia media) {
        boolean added = list.addMedia(media);
        if (!list.getListType().equalsIgnoreCase("watched")) {
            if (added) {
                textUI.displayMessage("\n" + media.getTitle() + " is now on your Favorite list!");
            } else {
                textUI.displayErrorMessage("\n" + media.getTitle() + " is already on the list!");
            }
        }
    }

    /**
     * Deletes the given media object from the given list.
     * Displays if the media object has been removed if it's on the given list.
     * @param list A given List object
     * @param media A given AMedia object
     */
    private void deleteFromList(List list, AMedia media) {
        boolean removed = list.removeMedia(media);
        if (removed) {
            textUI.displayMessage("\n"+media.getTitle() + " has been removed from the list.");
        } else {
            textUI.displayErrorMessage("\n"+media.getTitle() + " is not on the list!");
        }
    }

    /**
     * Gets the menu choice
     * The String returned determines what loop to exit
     * @return "q" or "exit"
     */
    public String getMenuChoice() {
        return menuChoice;
    }

    /**
     * Sets the menu choice
     * @param menuChoice The menu choice as a String
     */
    private void setMenuChoice(String menuChoice) {
        this.menuChoice = menuChoice;
    }
}
