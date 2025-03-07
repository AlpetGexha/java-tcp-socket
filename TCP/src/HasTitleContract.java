public interface HasTitleContract {
    String getTitle();
    String getDescription();

    default void showTitleAndDescription() {
        System.out.println("Title: " + getTitle());
        System.out.println("Description: " + getDescription());
    }
}