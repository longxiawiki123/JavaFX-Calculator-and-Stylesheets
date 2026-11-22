package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** Connects FXML button actions to the model and refreshes the single display. */
public final class CalculatorController {
    private final CalculatorModel model = new CalculatorModel();

    @FXML
    private TextField display;

    /** Creates the controller. The FXML loader supplies its display. */
    public CalculatorController() {
    }

    @FXML
    private void initialize() {
        refreshDisplay();
    }

    @FXML
    private void handleDigit(ActionEvent event) {
        Button button = (Button) event.getSource();
        model.inputDigit(Integer.parseInt(button.getUserData().toString()));
        refreshDisplay();
    }

    @FXML
    private void handleDecimal() {
        model.inputDecimalPoint();
        refreshDisplay();
    }

    @FXML
    private void handleOperation(ActionEvent event) {
        Button button = (Button) event.getSource();
        model.selectOperation(Operation.valueOf(button.getUserData().toString()));
        refreshDisplay();
    }

    @FXML
    private void handleEquals() {
        model.evaluate();
        refreshDisplay();
    }

    @FXML
    private void handleClear() {
        model.clear();
        refreshDisplay();
    }

    private void refreshDisplay() {
        display.setText(model.getDisplay());
        display.positionCaret(display.getLength());
    }
}
