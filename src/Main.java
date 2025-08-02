import controller.SelectionController;
import controller.UserController;
import view.UserView;

public class Main {
    public static void main(String[] args) {
        // Following naming conventions and renaming variable to camelCase
        UserView userView = new UserView();
        SelectionController controller = new SelectionController(userView);
    }
}
