import controller.SelectionController;
import view.UserView;

public class Main {
    public static void main(String[] args) {

        UserView view2 = new UserView();


        SelectionController controller = new SelectionController(view2);

    }
}