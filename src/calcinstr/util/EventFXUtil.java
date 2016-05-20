/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.util;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Victor
 */
public class EventFXUtil {
    private static MouseEvent mouseClickEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED,
            0,0, 0, 0, MouseButton.PRIMARY, 1,
            true, true, true, true,true, true, true, true, true, true, null);

    public static MouseEvent getMouseClickEvent(){
        return mouseClickEvent;
    }
}
